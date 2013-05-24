package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 * 
 * @author Christian Meyer
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	Navigator navigator;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("The Cool Project Pink");

		navigator = new Navigator(this, this);

		navigator.addView("", SessionView.class);
		navigator.addView(MAINVIEW,
				new PatientDetailView(TestDataSource.getPatient()));
	}
}
