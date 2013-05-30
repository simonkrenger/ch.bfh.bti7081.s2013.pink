/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.SessionState;
import ch.bfh.bti7081.s2013.pink.view.PatientDetailView;
import ch.bfh.bti7081.s2013.pink.view.SessionOverView;

import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
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

	// private List<Warning> warnings;

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
		Layout buttons = new HorizontalLayout();

		// Set BG
		// Resource res = new ThemeResource("img/patient_bg.png");

		// Display the patient image
		// Image bgImage = new Image(null, res);
		// mainLayout.addComponent(bgImage);

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
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.FINISHED)
				|| (session.getSessionState() == SessionState.ABORTED)) {
			buttons.addComponent(startSessionButton);
		}

		// add finish session button
		Button finishSessionButton = new Button("Finish session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.FINISHED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.STARTED) {
			buttons.addComponent(finishSessionButton);
		}
		// add abort session button
		Button abortSessionButton = new Button("Abort session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ABORTED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.STARTED)) {
			buttons.addComponent(abortSessionButton);
		}
		// add reopen session button
		Button reopenSessionButton = new Button("Reopen session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.REOPENED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.ARCHIVED) {
			buttons.addComponent(reopenSessionButton);
		}
		// add cancel session button
		Button cancelSessionButton = new Button("Cancel session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.CANCELLED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.PLANNED) {
			buttons.addComponent(cancelSessionButton);
		}
		// add archive session button
		Button archiveSessionButton = new Button("Archive session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.ARCHIVED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if ((session.getSessionState() == SessionState.FINISHED)
				|| (session.getSessionState() == SessionState.REOPENED)
				|| (session.getSessionState() == SessionState.CANCELLED)) {
			buttons.addComponent(archiveSessionButton);
		}
		// add planned session button
		Button plannedSessionButton = new Button("Archive session",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						session.changeState(SessionState.PLANNED);
						SessionView view = new SessionView(session, patient);
						MyVaadinUI.getNavigationManager().navigateTo(view);
					}
				});
		if (session.getSessionState() == SessionState.ABORTED) {
			buttons.addComponent(plannedSessionButton);
		}

		// add add medication button
		Button addMedicationButton = new Button("Add medication",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// Open Search
						PatientDetailView detailedPatientView = new PatientDetailView(
								patient);
						MyVaadinUI.getNavigationManager().navigateTo(
								detailedPatientView);
					}
				});
		if (!((session.getSessionState() == SessionState.STARTED) || (session
				.getSessionState() == SessionState.REOPENED))) {
			addMedicationButton.setEnabled(false);
		}
		buttons.addComponent(addMedicationButton);

		// Create an opener extension
		BrowserWindowOpener opener = new BrowserWindowOpener(
				ShowWarningView.class);
		opener.setFeatures("height=200,width=300,resizable");

		// Attach it to a button
		Button warnings = new Button("Show warnings");
		warnings.setIcon(new ThemeResource("img/warning.png"));
		opener.extend(warnings);
		buttons.addComponent(warnings);
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
		buttons.addComponent(backButton);

		mainLayout.addComponent(text);
		mainLayout.addComponent(buttons);
		// Create title field
		tf = new TextArea("Session notes");
		tf.setWidth("550px");
		tf.setHeight("320px");
		// only enable the note field if the session is started
		if (!((session.getSessionState() == SessionState.STARTED) || (session
				.getSessionState() == SessionState.REOPENED))) {
			tf.setEnabled(false);
		}
		// tf.setValue(session.getNotes().get(0).getText()); // TODO: Which note
		// is
															// the session note?
		mainLayout.addComponent(tf);
		// add add Note button
		Button addNote = new Button("Add note", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Open Search
				// Note note = new Note(tf.getValue());
				// session.addNote(note);
				PatientDetailView detailedPatientView = new PatientDetailView(
						patient);
				MyVaadinUI.getNavigationManager().navigateTo(
						detailedPatientView);
			}
		});
		mainLayout.addComponent(addNote);
		return mainLayout;
	}

}