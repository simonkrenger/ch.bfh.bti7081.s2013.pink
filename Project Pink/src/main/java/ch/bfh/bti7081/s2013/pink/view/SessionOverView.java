package ch.bfh.bti7081.s2013.pink.view;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bfh.bti7081.s2013.pink.MyVaadinUI;
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
public class SessionOverView extends NavigationView {
	private static final long serialVersionUID = 371245835932429111L;

	SessionList sessionList;

	public SessionOverView() {
		setCaption("Upcoming");

		final VerticalLayout layout = new VerticalLayout();

		SessionList sessionList = new SessionList(HibernateDataSource
				.getInstance().findAll(Session.class));

		final PatientSearchView searchView = new PatientSearchView();
		searchView.addSearchListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				layout.removeComponent(layout.getComponent(1));
				layout.addComponent(new SessionList(HibernateDataSource
						.getInstance().getSessionsByName(
								searchView.getSearchValue())));
			}
		});

		layout.addComponent(searchView);
		layout.addComponent(sessionList);
		setContent(layout);

		// Toolbar
		Button test = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = -261072762324902276L;

			@Override
			public void buttonClick(ClickEvent event) {
				new TestDataSource().clearTableAndCreateTestData();
			}
		});
		test.setIcon(new ClassResource("/images/mascot.png"));
		Toolbar toolbar = new Toolbar();

		List<Session> sessions = HibernateDataSource.getInstance().findAll(
				Session.class);
		if (sessions.size() == 0) {
			// allow creating test data
			toolbar.addComponent(test);
		} else {
			final Session dummySession = sessions.get(0);
			Button prescriptionDummy = new Button("Medical Prescription Dummy",
					new Button.ClickListener() {
						private static final long serialVersionUID = 5381750326390188015L;

						@Override
						public void buttonClick(ClickEvent event) {
							// Open Search
							MedicalPrescriptionView mpv = new MedicalPrescriptionView(
									dummySession);
							MyVaadinUI.getNavigationManager().navigateTo(mpv);
						}
					});
			prescriptionDummy.setIcon(new ClassResource("/images/mascot.png"));
			toolbar.addComponent(prescriptionDummy);
		}
		setToolbar(toolbar);
	}
}