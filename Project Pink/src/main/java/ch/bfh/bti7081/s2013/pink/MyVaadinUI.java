package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.view.SessionOverView;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * This is the applications 'main' class. Serves as the index page of the Vaadin
 * site.
 * 
 * @author Christian Meyer
 */
@Theme("mobiletheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	private NavigationManager navigationManager;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("The Cool Project Pink");

		SessionOverView view = new SessionOverView();
		navigationManager = new NavigationManager(view);
		setContent(navigationManager);
	}

	public static NavigationManager getNavigationManager() {
		return ((MyVaadinUI) UI.getCurrent()).navigationManager;
	}
}
