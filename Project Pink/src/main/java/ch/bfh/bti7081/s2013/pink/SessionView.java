package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

/**
 * View class for Sessions
 * 
 * @author Marco Berger
 * 
 */
@SuppressWarnings("serial")
public class SessionView extends NavigationView implements View {
	private Label headerTitle;
	private TestDataSource testenvironment;

	private Patient patient;
	private Session session;
	private Label patientSession;
	private Label closeSession;
	private Label medication;
	private Label compliance;

	public SessionView(Session session, Patient patient) {
		this.session = session;
		this.patient = session.getPatient();

		buildSessionWindow();
		showPatients();
	}

	public void showPatients() {
		// TODO: Show patients
		// loop trough the next 3 patients
		for (int i = 0; i < 3; i++) {
			// PatientOverview patientOverview = new PatientOverview(session);
			// addComponent(patientOverview);
			// SessionView test = new SessionView(session, patient);
			// addComponent(test);
		}
	}

	public void buildSessionWindow() {

		SessionWindow sessionWindow = new SessionWindow(session, patient);

		setContent(sessionWindow);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}
