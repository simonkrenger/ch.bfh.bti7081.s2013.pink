package ch.bfh.bti7081.s2013.pink;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 * 
 * @author Christian Meyer
 */
@Theme("mobiletheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	private NavigationManager navigationManager;
	// private Navigator navigator;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("The Cool Project Pink");

		// navigator = new Navigator(this, this);
		//
		// navigator.addView("", SessionOverView.class);
		// navigator.addView(MAINVIEW,
		// new PatientDetailView(TestDataSource.getPatient()));

		navigationManager = new NavigationManager(new SessionOverView());
		setContent(navigationManager);
		//
		// TabBarView mainView = new TabBarView();
		// setContent(mainView);
		// mainView.addTab(new SessionOverView(), "Next Sessions");
		// mainView.addTab(new Label("Dummy"), "Dummy");
	}

	public static NavigationManager getNavigationManager() {
		return ((MyVaadinUI) UI.getCurrent()).navigationManager;
	}
}
