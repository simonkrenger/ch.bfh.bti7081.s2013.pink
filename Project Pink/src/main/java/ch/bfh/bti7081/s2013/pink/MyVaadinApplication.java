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

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.Application;
import com.vaadin.ui.Window;


/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {
	private Window window;
	private int posX;
	private int posY;
	private Session session;
	private Patient patient;
	private TestDataSource testenvironment;

	@Override
	public void init() {
		session = testenvironment.getSession();
		patient = session.getPatient();
		patient = testenvironment.getPatient();
		window = new Window("Patient overview");
		setMainWindow(window);
		window.getContent().setSizeFull();

		/*
		 * Button button = new Button("Click Me."); button.addListener(new
		 * Button.ClickListener() { public void buttonClick(ClickEvent event) {
		 * window.addComponent(new Label("Dhank you for clicking")); } });
		 * window.addComponent(button); button = new Button("Don't Click Me");
		 * button.addListener(new Button.ClickListener() { public void
		 * buttonClick(ClickEvent event) { Iterator<Component> iterator =
		 * window.getComponentIterator(); Component remove = null; while
		 * (iterator.hasNext()) { Component comp = iterator.next(); if (comp
		 * instanceof Label) remove = comp; } if (remove != null)
		 * window.removeComponent(remove); } });
		 * 
		 * window.addComponent(button);
		 */
		showPatients();
	}

	public void showPatients() {
		// loop trough the next 3 patients
		for (int i = 0; i < 3; i++) {
			PatientOverview patientOverview = new PatientOverview(posX, 20,
					patient, session);
			window.addComponent(patientOverview);
		}
	}
}