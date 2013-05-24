package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * Class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class SessionOverView extends NavigationView implements View {
	private int posX;

	public SessionOverView() {
		posX = 0;

		buildPatientSearch();
		showPatients();

		Button test = new Button("Clear DB and create test data",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						new TestDataSource().clearTableAndCreateTestData();
					}
				});
		// addComponent(test);
	}

	public void showPatients() {
		VerticalLayout l = new VerticalLayout();
		// loop trough the next 3 patients
		int i = 0;
		for (Session session : HibernateDataSource.getInstance().findAll(
				Session.class)) {
			if (i++ > 3)
				break;
			PatientOverview patientOverview = new PatientOverview(posX, 200,
					session.getPatient(), session);
			l.addComponent(patientOverview);
		}
		setContent(l);
	}

	public void buildPatientSearch() {
		setToolbar(new PatientSearchView());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}