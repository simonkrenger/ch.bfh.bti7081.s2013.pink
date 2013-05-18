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

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class SessionOverView extends VerticalLayout implements View {
	private int posX;
	private int posY;
	private Session session;
	private Patient patient;
	private TestDataSource testenvironment;

	public SessionOverView() {
		session = testenvironment.getSession();
		patient = session.getPatient();
		patient = testenvironment.getPatient();

		setSizeFull();
		showPatients();
	}

	public void showPatients() {
		// loop trough the next 3 patients
		for (int i = 0; i < 3; i++) {
			PatientOverview patientOverview = new PatientOverview(posX, 20,
					patient, session);
			addComponent(patientOverview);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}