package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
@SuppressWarnings("serial")
public class SessionOverView extends VerticalLayout implements View {
	private int posX;
    SessionList sessionList;

	public SessionOverView() {
		posX = 0;

		setSizeFull();

        final PatientSearchView searchView = new PatientSearchView();
        searchView.AddSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                removeComponent(sessionList);
               sessionList = new SessionList(HibernateDataSource
                       .getInstance()
                       .getSessionsByName(searchView.getSearchValue()));

                addComponent(sessionList);
            }
        });

        addComponent(searchView);

        sessionList = new SessionList(getNextSessions());
        addComponent(sessionList);


        // Testing Area
		Button test = new Button("Clear DB and create test data",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						new TestDataSource().clearTableAndCreateTestData();
					}
				});
		addComponent(test);

        final Session dummySession = HibernateDataSource.getInstance().findAll(Session.class).get(0);
        addComponent(new Button("Medical Prescription Dummy", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                // Open Search
                MedicalPrescriptionView mpv = new MedicalPrescriptionView(dummySession);
                getUI().getNavigator().addView("medicalPrescription", mpv);
                getUI().getNavigator().navigateTo("medicalPrescription");
            }
        }));
	}

    private List<Session> getNextSessions()
    {
        List<Session> results = new ArrayList<Session>();
        int i = 0;
        for (Session session : HibernateDataSource.getInstance().findAll(Session.class)) {
            if (i++ > 3)
                break;

            results.add(session);
        }

        return results;
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// I'm not sure yet why this has to be here, it's called whenever the
		// view is opened.
	}
}