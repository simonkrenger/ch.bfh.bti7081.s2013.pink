/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ch.bfh.bti7081.s2013.pink;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.bfh.bti7081.s2013.pink.model.Doctor;
import ch.bfh.bti7081.s2013.pink.model.Note;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {
	private Window window;

	@Deprecated
	private int noteNumber = 0;

	@Override
	public void init() {
		window = new Window("My Vaadin Application");
		setMainWindow(window);
		Button button = new Button("Click Me");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				window.addComponent(new Label("Thank you for clicking"));
			}
		});
		window.addComponent(button);
		button = new Button("Don't Click Me");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Iterator<Component> iterator = window.getComponentIterator();
				Component remove = null;
				while (iterator.hasNext()) {
					Component comp = iterator.next();
					if (comp instanceof Label)
						remove = comp;
				}
				if (remove != null)
					window.removeComponent(remove);
			}
		});
		window.addComponent(button);
		final SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		button = new Button("Add note");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(new Note("Note #" + noteNumber++));
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);
		button = new Button("Add doctor");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Doctor dr = new Doctor("Gregory", "House");
				dr.addSpecialization("diagnostics");
				dr.addSpecialization("sociopath");
				session.persist(dr);
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);
		button = new Button("Get note");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<Note> result = session.createQuery("from Note").list();
				for (Note n : result) {
					window.addComponent(new Label(n.getTimestamp() + ": "
							+ n.getText()));
				}
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);
		button = new Button("Get doctors");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<Doctor> result = session.createQuery("from Doctor").list();
				for (Doctor n : result) {
					window.addComponent(new Label(n.toString()));
				}
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);
	}
}
