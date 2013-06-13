/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import ch.bfh.bti7081.s2013.pink.MyVaadinUI;
import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.SessionState;
import ch.bfh.bti7081.s2013.pink.view.WarningPopover.UpdateListener;

import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SessionView extends NavigationView {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	private TextArea tf;
	private Patient patient;
	private Session session;
	private String noteValue;
	private HibernateDataSource ds = HibernateDataSource.getInstance();

	private DateFormat df = new SimpleDateFormat();
	private HorizontalButtonGroup startSessionHBG = new HorizontalButtonGroup();
	private HorizontalButtonGroup finishSessionHBG = new HorizontalButtonGroup();
	private HorizontalButtonGroup abortSessionHBG = new HorizontalButtonGroup();
	private HorizontalButtonGroup reopenSessionHBG = new HorizontalButtonGroup();
	private HorizontalButtonGroup cancelSessionHBG = new HorizontalButtonGroup();
	private HorizontalButtonGroup archiveSessionHBG = new HorizontalButtonGroup();
	private Button addMedicationButton;
	private Button notesButton;
	private Button warningsButton;
	private Label sessionStateText;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SessionView(Session session, Patient patient) {
		this.session = session;
		this.patient = patient;

		buildMainLayout();

		setCaption(patient.getFirstName() + "'s Session");
		setContent(mainLayout);
		setToolbar(createToolbar());
		updateData();
	}

	private Toolbar createToolbar() {
		Toolbar toolbar = new Toolbar();

		notesButton = new Button();
		notesButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7701758595488212194L;

			@Override
			public void buttonClick(ClickEvent event) {
				NotesPopover notesView = new NotesPopover(session);
				notesView.setAddingEnabled(session.getSessionState()
						.isEditable());
				notesView.setEditable(session.getSessionState().isEditable());
				notesView.showRelativeTo(notesButton);
				notesView.addUpdateListener(new UpdateListener() {
					@Override
					public void update(int size) {
						IndicatorImageSource image = new IndicatorImageSource(
								"/images/note.png", size);
						notesButton.setIcon(image.getResource());
						session = ds.reload(session);
					}
				});
			}
		});
		int size = session.getNotes().size();
		IndicatorImageSource image = new IndicatorImageSource(
				"/images/note.png", size);
		notesButton.setIcon(image.getResource());
		toolbar.addComponent(notesButton);

		warningsButton = new Button();
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
						patient = ds.reload(patient);
					}
				});
			}
		});
		size = patient.getWarnings().size();
		image = new IndicatorImageSource("/images/warning.png", size);
		warningsButton.setIcon(image.getResource());
		toolbar.addComponent(warningsButton);

		return toolbar;
	}

	private AbstractComponentContainer buildInfo() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		HorizontalLayout imageAndText = new HorizontalLayout();
		layout.addComponent(imageAndText);
		imageAndText.setWidth("100%");
		imageAndText.setSpacing(true);

		VerticalComponentGroup times = new VerticalComponentGroup();
		layout.addComponent(times);
		times.setWidth("100%");

		// set patient picture
		if (patient.getImageUrl() != null) {
			Image image = new Image(null, new ClassResource(
					patient.getImageUrl()));
			imageAndText.addComponent(image);
		}

		VerticalLayout vl = new VerticalLayout();
		imageAndText.addComponent(vl);
		imageAndText.setExpandRatio(vl, 0.7f);

		// TODO: Show Session State!

		// Patient information
		sessionStateText = new Label();
		sessionStateText.setValue(session.getSessionState().toString());
		vl.addComponent(sessionStateText);

		vl.addComponent(new Label(patient.getFirstName() + " "
				+ patient.getName()));

		// Buttons
		buildButtons(vl);

		// Session start time
		Label sessionStart = new Label();
		sessionStart.setCaption("Start");
		sessionStart.setValue(df.format(session.getTimeStart()));
		times.addComponent(sessionStart);

		// Session end time
		Label titleEnd = new Label();
		titleEnd.setCaption("End");
		titleEnd.setValue(df.format(session.getTimeEnd()));
		times.addComponent(titleEnd);
		return layout;
	}

	private void buildButtons(AbstractComponentContainer group) {
		// add start session button
		Button startSessionButton = new Button("Start Session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.STARTED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		startSessionButton.setWidth("100%");
		startSessionHBG.addComponent(startSessionButton);
		startSessionHBG.setWidth("100%");
		group.addComponent(startSessionHBG);

		// add finish session button
		Button finishSessionButton = new Button("Finish session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.FINISHED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		finishSessionButton.setWidth("100%");
		finishSessionHBG.addComponent(finishSessionButton);
		finishSessionHBG.setWidth("100%");
		group.addComponent(finishSessionHBG);

		// add abort session button
		Button abortSessionButton = new Button("Abort session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ABORTED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		abortSessionButton.setWidth("100%");
		abortSessionHBG.addComponent(abortSessionButton);
		abortSessionHBG.setWidth("100%");
		group.addComponent(abortSessionHBG);

		// add reopen session button
		Button reopenSessionButton = new Button("Reopen session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.REOPENED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		reopenSessionButton.setWidth("100%");
		reopenSessionHBG.addComponent(reopenSessionButton);
		reopenSessionHBG.setWidth("100%");
		group.addComponent(reopenSessionHBG);

		// add cancel session button
		Button cancelSessionButton = new Button("Cancel session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.CANCELLED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		cancelSessionButton.setWidth("100%");
		cancelSessionHBG.addComponent(cancelSessionButton);
		cancelSessionHBG.setWidth("100%");
		group.addComponent(cancelSessionHBG);

		// add archive session button
		Button archiveSessionButton = new Button("Archive session",
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ARCHIVED);
						session = ds.saveOrUpdate(session);
						updateData();
					}
				});
		archiveSessionButton.setWidth("100%");
		archiveSessionHBG.addComponent(archiveSessionButton);
		archiveSessionHBG.setWidth("100%");
		group.addComponent(archiveSessionHBG);

		// And now for something completely different:
		// add add medication button
		// TODO: Should this be in the toolbar?

		HorizontalButtonGroup mediNoteHBG = new HorizontalButtonGroup();
		mediNoteHBG.setWidth("100%");
		group.addComponent(mediNoteHBG);

		addMedicationButton = new Button("Add medication",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// Open Search
						MedicalPrescriptionView view = new MedicalPrescriptionView(
								session);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		addMedicationButton.setWidth("100%");
		addMedicationButton.setEnabled(session.getSessionState().isEditable());
		mediNoteHBG.addComponent(addMedicationButton);
	}

	private void updateData() {
		session = ds.reload(session);
		patient = ds.reload(session.getPatient());

		Collection<SessionState> states = session.getSessionState()
				.getPossibleNextStateType();

		startSessionHBG.setVisible(states.contains(SessionState.STARTED));
		finishSessionHBG.setVisible(states.contains(SessionState.FINISHED));
		abortSessionHBG.setVisible(states.contains(SessionState.ABORTED));
		reopenSessionHBG.setVisible(states.contains(SessionState.REOPENED));
		cancelSessionHBG.setVisible(states.contains(SessionState.CANCELLED));
		archiveSessionHBG.setVisible(states.contains(SessionState.ARCHIVED));

		addMedicationButton.setEnabled(session.getSessionState().isEditable());

		tf.setEnabled(session.getSessionState().isEditable());

		int size = patient.getWarnings().size();
		IndicatorImageSource image = new IndicatorImageSource(
				"/images/warning.png", size);
		warningsButton.setIcon(image.getResource());
		patient = ds.reload(patient);

		size = session.getNotes().size();
		image = new IndicatorImageSource("/images/note.png", size);
		notesButton.setIcon(image.getResource());

		sessionStateText.setValue(session.getSessionState().toString());
	}

	private void buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");

		// back to home button

		mainLayout.addComponent(buildInfo());
		// Create title field
		tf = new TextArea("Session notes");
		tf.setWidth("100%");
		tf.setHeight("320px");
		tf.setTextChangeTimeout(300);
		tf.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.TIMEOUT);
		tf.addTextChangeListener(new FieldEvents.TextChangeListener() {
			public void textChange(FieldEvents.TextChangeEvent event) {
				noteValue = event.getText();
				// patientSearch();
			}
		});

		// only enable the note field if the session is started
		tf.setEnabled(session.getSessionState().isEditable());

		mainLayout.addComponent(tf);
		// add add Note button
		Button addNote = new Button("Add note", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Note note = ds.saveOrUpdate(new Note(tf.getValue()));
				session.addNote(note);
				session = ds.saveOrUpdate(session);
				tf.setValue("");
			}
		});
		mainLayout.addComponent(addNote);
	}
}