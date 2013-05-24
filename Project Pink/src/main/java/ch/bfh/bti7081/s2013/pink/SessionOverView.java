package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class SessionOverView extends VerticalLayout implements View {
	private int posX;
	private int posY;

	public SessionOverView() {
		// new TestDataSource().clearTableAndCreateTestData();
		posX = 0;

		setSizeFull();
		buildPatientSearch();
		showPatients();
	}

	public void showPatients() {
		// loop trough the next 3 patients
		int i = 0;
		for (Session session : HibernateDataSource.getInstance().findAll(
				Session.class)) {
			if (i++ > 3)
				break;
			PatientOverview patientOverview = new PatientOverview(posX, 200,
					session.getPatient(), session);
			addComponent(patientOverview);
			// SessionView test = new SessionView(session, patient);
			// addComponent(test);
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