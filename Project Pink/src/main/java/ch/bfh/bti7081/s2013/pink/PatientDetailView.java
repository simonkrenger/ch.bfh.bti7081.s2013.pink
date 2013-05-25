package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * View class to show detailed information about a patient.
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class PatientDetailView extends NavigationView implements View {
	private Patient patient;

	/**
	 * Constructor for the view class. Takes the patient to be displayed as an
	 * argument.
	 * 
	 * @param patient
	 *            The patient whose information is displayed in the detail view.
	 */
	public PatientDetailView(Patient patient) {
		setSizeFull();
		this.patient = patient;

		Layout layout = new VerticalLayout();

		setCaption(patient.getFirstName() + " " + patient.getName());
		layout.addComponent(new Label(patient.getFirstName() + " "
				+ patient.getName()));
		// TODO: add buttons, warnings
		if (patient.getAllergies().size() > 0)
			; // TODO: show allergy warning

		for (Note note : patient.getNotes()) {
			layout.addComponent(new Label("Date: " + note.getTimestamp()));
			layout.addComponent(new Label(note.getText()));
		}
		setContent(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		for (Warning warning : patient.getWarnings())
			Notification.show(warning.getText(), Type.WARNING_MESSAGE);
	}
}
