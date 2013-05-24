/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * Please don't hesitate to remove or radically change this class, it's just
 * here so we can do some minor tests.
 * 
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class PatientDetailView extends VerticalLayout implements View {
	private Patient patient;

	public PatientDetailView(Patient patient) {
		setSizeFull();
		this.patient = patient;

		addComponent(new Label(patient.getFirstName() + " " + patient.getName()));
		// TODO: add buttons, warnings
		if (patient.getAllergies().size() > 0)
			; // TODO: show allergy warning

		for (Note note : patient.getNotes()) {
			addComponent(new Label("Date: " + note.getTimestamp()));
			addComponent(new Label(note.getText()));
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (Warning warning : patient.getWarnings())
			Notification.show(warning.getText(), Type.WARNING_MESSAGE);
	}
}
