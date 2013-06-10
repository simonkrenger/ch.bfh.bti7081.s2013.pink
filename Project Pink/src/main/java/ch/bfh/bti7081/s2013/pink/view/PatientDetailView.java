package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.MyVaadinUI;
import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.MedicationPrescription;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.view.WarningPopover.UpdateListener;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * View class to show detailed information about a patient.
 * 
 * @author Christian Meyer
 * @author Simon Krenger
 */
public class PatientDetailView extends NavigationView {
	private static final long serialVersionUID = 740064506465249030L;

	private HibernateDataSource df = HibernateDataSource.getInstance();

	private Button notesButton;

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
	public PatientDetailView(final Patient patient) {
		setSizeFull();
		this.patient = patient;

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		setCaption(patient.getFirstName() + " " + patient.getName());

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%");
		horizontalLayout.setSpacing(true);

		Image patientPortrait = new Image(null, new ClassResource(
				patient.getImageUrl()));
		horizontalLayout.addComponent(patientPortrait);

		VerticalComponentGroup basicInfo = new VerticalComponentGroup();
		basicInfo.setWidth("100%");

		Label firstName = new Label("<b>First name:</b> "
				+ patient.getFirstName(), ContentMode.HTML);
		basicInfo.addComponent(firstName);

		Label lastName = new Label("<b>Last name:</b> " + patient.getName(),
				ContentMode.HTML);

		basicInfo.addComponent(lastName);

		horizontalLayout.addComponent(basicInfo);
		horizontalLayout.setExpandRatio(basicInfo, 0.7f);
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

		if (!patient.getPrescriptions().isEmpty()) {
			VerticalComponentGroup prescriptions = new VerticalComponentGroup(
					"Prescriptions");
			prescriptions.setWidth("100%");
			for (MedicationPrescription p : patient.getPrescriptions()) {
				Label prescr = new Label();
				prescr.setCaption(p.getMedicine().getName());
				prescr.setValue(String.valueOf(p.getDose()));
				prescriptions.addComponent(prescr);
			}
			layout.addComponent(prescriptions);
		}

		setContent(layout);

		// Create Toolbar
		final Toolbar toolbar = new Toolbar();

		// Notes
		notesButton = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 7701758595488212194L;

			@Override
			public void buttonClick(ClickEvent event) {
				NotesView notesView = new NotesView(patient);
				MyVaadinUI.getNavigationManager().navigateTo(notesView);
			}
		});
		int size = patient.getNotes().size();
		IndicatorImageSource image = new IndicatorImageSource(
				"/images/note.png", size);
		notesButton.setIcon(image.getResource());
		toolbar.addComponent(notesButton);

		// Warnings
		final Button warningsButton = new Button();
		warningsButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -332160668612432938L;

			@Override
			public void buttonClick(ClickEvent event) {
				WarningPopover popover = new WarningPopover(patient);

				// Show it relative to the navigation bar of
				// the current NavigationView.
				popover.showRelativeTo(warningsButton);
				popover.addUpdateListener(new UpdateListener() {
					@Override
					public void update(int size) {
						IndicatorImageSource image = new IndicatorImageSource(
								"/images/warning.png", size);
						warningsButton.setIcon(image.getResource());
						PatientDetailView.this.patient = df
								.reload(PatientDetailView.this.patient);
					}
				});
			}
		});
		size = patient.getWarnings().size();
		image = new IndicatorImageSource("/images/warning.png", size);
		warningsButton.setIcon(image.getResource());
		toolbar.addComponent(warningsButton);

		setToolbar(toolbar);
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		patient = df.reload(patient);
		// for (Warning warning : patient.getWarnings()) {
		// Notification.show(warning.getText(), Type.WARNING_MESSAGE);
		// }

		int size = patient.getNotes().size();
		IndicatorImageSource image = new IndicatorImageSource(
				"/images/note.png", size);
		notesButton.setIcon(image.getResource());
	}
}
