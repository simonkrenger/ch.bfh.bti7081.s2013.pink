package ch.bfh.bti7081.s2013.pink.view;

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
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
	private Button warningsButton;
	private AbstractComponentContainer prescriptions;

	/**
	 * Patient whose details are displayed
	 */
	private Patient patient = null;

	/**
	 * Constructor for the view class. Takes the patient to be displayed as an
	 * argument.
	 * 
	 * @param p
	 *            The patient whose information is displayed in the detail view.
	 */
	public PatientDetailView(final Patient p) {
		setSizeFull();
		this.patient = df.reload(p);

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		setCaption(p.getFirstName() + " " + p.getName());

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%");
		horizontalLayout.setSpacing(true);

		Image patientPortrait = new Image(null, new ClassResource(
				p.getImageUrl()));
		horizontalLayout.addComponent(patientPortrait);

		VerticalComponentGroup basicInfo = new VerticalComponentGroup();
		basicInfo.setWidth("100%");

		Label firstName = new Label("<b>First name:</b> " + p.getFirstName(),
				ContentMode.HTML);
		basicInfo.addComponent(firstName);

		Label lastName = new Label("<b>Last name:</b> " + p.getName(),
				ContentMode.HTML);

		basicInfo.addComponent(lastName);

		horizontalLayout.addComponent(basicInfo);
		horizontalLayout.setExpandRatio(basicInfo, 0.7f);
		layout.addComponent(horizontalLayout);

		// Only display allergies if there are any
		if (p.getAllergies().size() > 0) {
			VerticalComponentGroup allergies = new VerticalComponentGroup(
					"Allergies");
			allergies.setWidth("100%");
			for (Allergy a : p.getAllergies())
				// TODO: show prettier allergy warning
				allergies.addComponent(new Label(a.toString()));
			layout.addComponent(allergies);
		}

		prescriptions = new VerticalComponentGroup("Prescriptions");
		prescriptions.setWidth("100%");
		layout.addComponent(prescriptions);
		updatePrescriptions();

		setContent(layout);

		// Create Toolbar
		final Toolbar toolbar = new Toolbar();

		// Notes
		notesButton = new Button();
		notesButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7701758595488212194L;

			@Override
			public void buttonClick(ClickEvent event) {
				NotesPopover notesView = new NotesPopover(p);
				// MyVaadinUI.getNavigationManager().navigateTo(notesView);
				notesView.showRelativeTo(notesButton);
			}
		});
		int size = p.getNotes().size();
		IndicatorImageSource image = new IndicatorImageSource(
				"/images/note.png", size);
		notesButton.setIcon(image.getResource());
		toolbar.addComponent(notesButton);

		// Warnings
		warningsButton = new Button();
		warningsButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -332160668612432938L;

			@Override
			public void buttonClick(ClickEvent event) {
				WarningPopover popover = new WarningPopover(p);

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
		size = p.getWarnings().size();
		image = new IndicatorImageSource("/images/warning.png", size);
		warningsButton.setIcon(image.getResource());
		toolbar.addComponent(warningsButton);

		setToolbar(toolbar);
	}

	private void updatePrescriptions() {
		if (!patient.getPrescriptions().isEmpty()) {
			for (MedicationPrescription p : patient.getPrescriptions()) {
				Label prescr = new Label();
				prescr.setCaption(p.getMedicine().getName());
				prescr.setValue(String.valueOf(p.getDose()));
				prescriptions.addComponent(prescr);
			}
		} else {
			prescriptions.setVisible(false);
		}
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		patient = df.reload(patient);

		// Update data that might have changed:
		int size;
		IndicatorImageSource image;

		// Warnings
		size = patient.getWarnings().size();
		if (size == 1)
			Notification.show("The patient has a warning!",
					Type.WARNING_MESSAGE);
		else if (size > 1)
			Notification.show("The patient has " + size + " warnings!",
					Type.WARNING_MESSAGE);
		image = new IndicatorImageSource("/images/warning.png", size);
		warningsButton.setIcon(image.getResource());

		// Notes
		size = patient.getNotes().size();
		image = new IndicatorImageSource("/images/note.png", size);
		notesButton.setIcon(image.getResource());

		// Prescriptions
		updatePrescriptions();
	}
}
