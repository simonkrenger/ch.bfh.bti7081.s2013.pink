package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.MedicationPrescription;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * View class to show detailed information about a patient.
 * 
 * @author Christian Meyer
 * @author Simon Krenger
 */
@SuppressWarnings("serial")
public class PatientDetailView extends NavigationView {

	/**
	 * Patient whose details are displayed
	 */
	private Patient patient = null;

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

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%");

		Image patientPortrait = new Image(null, new ClassResource(
				patient.getImageUrl()));
		horizontalLayout.addComponent(patientPortrait);

		VerticalComponentGroup basicInfo = new VerticalComponentGroup(
				"Basic information");


		Label firstName = new Label("<b>First name:</b> "
				+ patient.getFirstName(), ContentMode.HTML);
		basicInfo.addComponent(firstName);

		Label lastName = new Label("<b>Last name:</b> " + patient.getName(),
				ContentMode.HTML);

		basicInfo.addComponent(lastName);


		horizontalLayout.addComponent(basicInfo);
		layout.addComponent(horizontalLayout);

		// Only display allergies if there are any
		if (patient.getAllergies().size() > 0) {
			VerticalComponentGroup allergies = new VerticalComponentGroup(
					"Allergies");
			allergies.setWidth("100%");
			for (Allergy a : patient.getAllergies())
				// TODO: show prettier allergy warning
				allergies.addComponent(new Label(a.toString()));
			layout.addComponent(allergies);
		}

		VerticalComponentGroup notes = new VerticalComponentGroup("Notes");
		for (Note n : patient.getNotes()) {
			Label note = new Label(n.getText());
			note.setCaption("Date: " + n.getTimestamp());
			notes.addComponent(note);
		}
		layout.addComponent(notes);

		VerticalComponentGroup prescriptions = new VerticalComponentGroup(
				"Prescriptions");
		for (MedicationPrescription p : patient.getPrescriptions()) {
			Label prescr = new Label(p.getDose() + " " + p.getMedicine());
			prescr.setCaption("Prescription");
			prescriptions.addComponent(prescr);
		}
		layout.addComponent(prescriptions);

		setContent(layout);
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		for (Warning warning : patient.getWarnings()) {
			Notification.show(warning.getText(), Type.ERROR_MESSAGE);
		}
	}
}