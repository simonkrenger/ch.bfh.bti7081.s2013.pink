/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.SessionState;
import ch.bfh.bti7081.s2013.pink.view.MedicalPrescriptionView;
import ch.bfh.bti7081.s2013.pink.view.SessionNotesView;
import ch.bfh.bti7081.s2013.pink.view.SessionOverView;
import ch.bfh.bti7081.s2013.pink.view.ShowWarningsView;

import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SessionWindow extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	private Label title;
	private TextArea tf;
	private Patient patient;
	private Session session;
	private String noteValue;
	private HibernateDataSource ds = HibernateDataSource.getInstance();

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SessionWindow(Session session, Patient patient) {
		this.session = session;
		this.patient = patient;

		buildMainLayout();
		VerticalComponentGroup box = new VerticalComponentGroup();
		box.addComponent(mainLayout);
		setCompositionRoot(box);
	}

	@AutoGenerated
	private Layout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100%");

		VerticalLayout text = new VerticalLayout();
		Layout stateButtons = new HorizontalLayout();
		Layout mediNoteButtons = new HorizontalLayout();
		Layout warningBackButtons = new HorizontalLayout();

		// set patient picture
		if (patient.getImageUrl() != null)
			mainLayout.addComponent(new Image(null, new ClassResource(patient
					.getImageUrl())));

		// Patient information
		title = new Label();
		title.setValue("Session of " + patient.getFirstName() + " "
				+ patient.getName());
		text.addComponent(title);

		// Session start time
		title = new Label();
		title.setValue("Start time: " + session.getTimeStart().toString());
		text.addComponent(title);

		// Session end time
		title = new Label();
		title.setValue("End time: " + session.getTimeEnd().toString());
		text.addComponent(title);

		// add start session button
		Button startSessionButton = new Button("Start session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.STARTED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.FINISHED)
				|| (session.getSessionState() == SessionState.ABORTED)) {
			stateButtons.addComponent(startSessionButton);
		}

		// add finish session button
		Button finishSessionButton = new Button("Finish session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.FINISHED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.STARTED) {
			stateButtons.addComponent(finishSessionButton);
		}
		// add abort session button
		Button abortSessionButton = new Button("Abort session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ABORTED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.STARTED)) {
			stateButtons.addComponent(abortSessionButton);
		}
		// add reopen session button
		Button reopenSessionButton = new Button("Reopen session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.REOPENED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.ARCHIVED) {
			stateButtons.addComponent(reopenSessionButton);
		}
		// add cancel session button
		Button cancelSessionButton = new Button("Cancel session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.CANCELLED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.PLANNED) {
			stateButtons.addComponent(cancelSessionButton);
		}
		// add archive session button
		Button archiveSessionButton = new Button("Archive session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ARCHIVED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.FINISHED)
				|| (session.getSessionState() == SessionState.REOPENED)
				|| (session.getSessionState() == SessionState.CANCELLED)) {
			stateButtons.addComponent(archiveSessionButton);
		}
		// add planned session button
		Button plannedSessionButton = new Button("Archive session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.PLANNED);
						session = ds.saveOrUpdate(session);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.ABORTED) {
			stateButtons.addComponent(plannedSessionButton);
		}

		// add add medication button
		Button addMedicationButton = new Button("Add medication",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// Open Search
						MedicalPrescriptionView view = new MedicalPrescriptionView(
								session);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (((session.getSessionState() == SessionState.STARTED) || (session
				.getSessionState() == SessionState.REOPENED))) {
			addMedicationButton.setEnabled(false);
		}
		mediNoteButtons.addComponent(addMedicationButton);

		// add edit notes button
		Button editNotesButton = new Button("Edit notes",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// Open Search
						SessionNotesView view = new SessionNotesView(session);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		// set edit notes button to disabled if there are no notes
		if (session.getNotes().isEmpty()) {
			addMedicationButton.setEnabled(false);
		}
		mediNoteButtons.addComponent(editNotesButton);

		Button warnings = new Button("Show warnings",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						ShowWarningsView view = new ShowWarningsView(patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		warnings.setIcon(new ThemeResource("img/warning.png"));
		warningBackButtons.addComponent(warnings);
		// back to home button
		Button backButton = new Button("Back to overview",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						SessionOverView view = new SessionOverView();
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (((session.getSessionState() == SessionState.STARTED) || (session
				.getSessionState() == SessionState.REOPENED))) {
			backButton.setEnabled(false);
		}
		warningBackButtons.addComponent(backButton);

		mainLayout.addComponent(text);
		mainLayout.addComponent(stateButtons);
		mainLayout.addComponent(mediNoteButtons);
		mainLayout.addComponent(warningBackButtons);
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
		if (!((session.getSessionState() == SessionState.STARTED) || (session
				.getSessionState() == SessionState.REOPENED))) {
			tf.setEnabled(false);
		}

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
		return mainLayout;
	}

}