package ch.bfh.bti7081.s2013.pink.view;

import java.util.LinkedList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.VerticalLayout;

/**
 * View class that shows the upcoming sessions with the patients
 * 
 * @author Marco Berger <lostchall@gmail.com>
 */
public class SessionOverView extends NavigationView {
	private static final long serialVersionUID = 371245835932429111L;

	private SessionList sessionList;
	private VerticalLayout layout;

	public SessionOverView() {
		setCaption("Upcoming");

		layout = new VerticalLayout();
		layout.setMargin(true);

		sessionList = new SessionList(new LinkedList<Session>());

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
	}

	@Override
	protected void onBecomingVisible() {
		super.onBecomingVisible();
		layout.removeComponent(sessionList);
		sessionList = new SessionList(HibernateDataSource.getInstance()
				.findAll(Session.class));
		layout.addComponent(sessionList);
	}
}