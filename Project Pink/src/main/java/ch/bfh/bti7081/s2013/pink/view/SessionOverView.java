package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * View class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class SessionOverView extends NavigationView {
	public SessionOverView() {
		setCaption("Upcoming");

		// TODO: buildPatientSearch();
		VerticalLayout layout = new VerticalLayout();
		// loop trough the next 3 patients
		int i = 0;
		for (Session session : HibernateDataSource.getInstance().findAll(
				Session.class)) {
			// if (i++ > 3)
			// break;
			PatientOverview patientOverview = new PatientOverview(session);
			layout.addComponent(patientOverview);
		}
		setContent(layout);

		// Toolbar
		Button test = new Button(null, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				new TestDataSource().clearTableAndCreateTestData();
			}
		});
		test.setIcon(new ClassResource("/images/mascot.png"));
		Toolbar toolbar = new Toolbar();
		toolbar.addComponent(test);
		setToolbar(toolbar);
	}
}