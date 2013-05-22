/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */

package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class SessionOverView extends VerticalLayout implements View {
	private int posX;
	private int posY;
	private Session session;
	private Patient patient;
	private Label headerTitle;
	private TestDataSource testenvironment;

	public SessionOverView() {
		session = testenvironment.getSession();
		patient = session.getPatient();
		patient = testenvironment.getPatient();
		posX = 0;

		setSizeFull();
		buildPatientSearch();
		showPatients();
	}

	public void showPatients() {
		// loop trough the next 3 patients
		for (int i = 0; i < 3; i++) {
			PatientOverview patientOverview = new PatientOverview(posX, 200,
					patient, session);
			addComponent(patientOverview);
			//SessionView test = new SessionView(session, patient);
			//addComponent(test);
		}
	}

	public void buildPatientSearch() {

		PatientSearchView patientSearchView = new PatientSearchView(patient);

		addComponent(patientSearchView);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}