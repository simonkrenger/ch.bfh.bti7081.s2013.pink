/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * Please don't hesitate to remove or radically change this class, it's just
 * here so we can do some minor tests.
 * 
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class PatientDetailView extends VerticalLayout implements View {

	public PatientDetailView(Patient patient) {
		setSizeFull();

		addComponent(new Label(patient.getFirstName() + " " + patient.getName()));
		// TODO: add buttons, warnings
		if (patient.getAllergies().size() > 0)
			; // TODO: add allergy warning
		for (Note note : patient.getNotes()) {
			addComponent(new Label("Date: " + note.getTimestamp()));
			addComponent(new Label(note.getText()));
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Animal Farm");
	}
}
