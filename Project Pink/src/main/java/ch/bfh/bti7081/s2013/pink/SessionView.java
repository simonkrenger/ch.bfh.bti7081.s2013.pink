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
public class SessionView extends VerticalLayout implements View {
	private Label headerTitle;
	private TestDataSource testenvironment;

	private Patient patient;
	private Session session;
	private Label patientSession;
	private Label closeSession;
	private Label medication;
	private Label compliance;

	public SessionView(Session session, Patient patient) {
		this.session = testenvironment.getSession();
		this.patient = session.getPatient();
		patient = testenvironment.getPatient();

		setSizeFull();
		buildSessionWindow();
	}

	public void buildSessionWindow() {

		SessionWindow sessionWindow = new SessionWindow(session, patient);

		addComponent(sessionWindow);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}
