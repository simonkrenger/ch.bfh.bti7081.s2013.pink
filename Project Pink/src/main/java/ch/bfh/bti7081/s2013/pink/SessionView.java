package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.addon.touchkit.ui.NavigationView;

/**
 * View class for Sessions
 * 
 * @author Marco Berger
 * 
 */
@SuppressWarnings("serial")
public class SessionView extends NavigationView {
	public SessionView(Session session, Patient patient) {
		// TODO: I think this could be merged with SessionWindow
		setCaption(patient.getFirstName() + "'s Session");
		setContent(new SessionWindow(session, patient));
	}
}
