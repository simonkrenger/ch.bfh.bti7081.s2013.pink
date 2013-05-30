package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.MedicalPrescriptionView;
import ch.bfh.bti7081.s2013.pink.MyVaadinUI;
import ch.bfh.bti7081.s2013.pink.PatientSearchView;
import ch.bfh.bti7081.s2013.pink.SessionList;
import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * View class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class SessionOverView extends NavigationView {

    SessionList sessionList;
	public SessionOverView() {
		setCaption("Upcoming");

		// TODO: buildPatientSearch();
		final VerticalLayout layout = new VerticalLayout();

        final PatientSearchView searchView = new PatientSearchView();
        searchView.AddSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                layout.removeComponent(sessionList);
                sessionList = new SessionList(HibernateDataSource
                        .getInstance()
                        .getSessionsByName(searchView.getSearchValue()));

                layout.addComponent(sessionList);
            }
        });

        layout.addComponent(searchView);

        sessionList = new SessionList(getNextSessions());
        layout.addComponent(sessionList);




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

        final Session dummySession = HibernateDataSource.getInstance().findAll(Session.class).get(0);
        toolbar.addComponent(new Button("Medical Prescription Dummy", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                // Open Search
                MedicalPrescriptionView mpv = new MedicalPrescriptionView(dummySession);
                MyVaadinUI.getNavigationManager().navigateTo(mpv);
            }
        }));

		toolbar.addComponent(test);
		setToolbar(toolbar);
	}

    private List<Session> getNextSessions()
    {
        List<Session> results = new ArrayList<Session>();
        for (Session session : HibernateDataSource.getInstance().findAll(Session.class)) {
            results.add(session);
        }

        return results;
    }
}