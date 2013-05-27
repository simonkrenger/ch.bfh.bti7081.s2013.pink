package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
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
public class PatientDetailView extends NavigationView {
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

		// TODO: add buttons, warnings
		if (patient.getAllergies().size() > 0) {
			VerticalComponentGroup allergies = new VerticalComponentGroup(
					"Allergies");
			allergies.setWidth("100%");
			for (Allergy a : patient.getAllergies())
				// TODO: show prettier allergy warning
				allergies.addComponent(new Label(a.toString()));
			layout.addComponent(allergies);
		}

		for (Note n : patient.getNotes()) {
			Label note = new Label(n.getText());
			note.setCaption("Date: " + n.getTimestamp());
			layout.addComponent(note);
		}
		setContent(layout);
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		for (Warning warning : patient.getWarnings())
			Notification.show(warning.getText(), Type.WARNING_MESSAGE);
	}
}
