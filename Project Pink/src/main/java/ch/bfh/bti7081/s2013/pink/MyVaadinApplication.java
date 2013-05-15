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

import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.Allergy.Severity;
import ch.bfh.bti7081.s2013.pink.model.Doctor;
import ch.bfh.bti7081.s2013.pink.model.Dose;
import ch.bfh.bti7081.s2013.pink.model.Dose.Period;
import ch.bfh.bti7081.s2013.pink.model.Ingredient;
import ch.bfh.bti7081.s2013.pink.model.Medicine;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Please don't hesitate to remove or radically change this class, it's just
 * here so we can do some minor tests.
 * 
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

		// Let's save some entities!
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

		button = new Button("Add allergy");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Ingredient i = new Ingredient("Hay");
				session.persist(i);
				Allergy a = new Allergy(i, Severity.SEVERE);
				session.persist(a);
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add medicine");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Ingredient i = new Ingredient("Placebium");
				session.persist(i);
				Medicine m = new Medicine("Placebo");
				m.addIngredient(i);
				m.addEffect("effect");
				m.addEffect("another effect");
				m.addSideeffect("tough luck");
				session.persist(m);
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add prescription");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(TestDataSource.getPrescription());
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add patient");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(TestDataSource.getPatient());
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add dose");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(new Dose(5, 2, Period.DAY));
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add diagnosis");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(TestDataSource.getDiagnosis());
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		button = new Button("Add session");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.persist(TestDataSource.getSession());
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);

		final TextField query = new TextField();
		query.setValue("from Session");
		window.addComponent(query);

		button = new Button("Run query");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<Object> result = session.createQuery(
						query.getValue().toString()).list();
				for (Object n : result) {
					window.addComponent(new Label(n.toString()));
				}
				session.getTransaction().commit();
				session.close();
			}
		});
		window.addComponent(button);
	}
}
