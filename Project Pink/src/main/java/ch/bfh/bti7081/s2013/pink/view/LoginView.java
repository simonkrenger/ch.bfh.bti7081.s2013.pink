package ch.bfh.bti7081.s2013.pink.view;

import java.util.List;

import ch.bfh.bti7081.s2013.pink.MyVaadinUI;
import ch.bfh.bti7081.s2013.pink.model.Context;
import ch.bfh.bti7081.s2013.pink.model.Doctor;
import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.TestDataSource;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class LoginView extends NavigationView {
	private static final long serialVersionUID = -3598328604425343468L;

	public LoginView() {
		setCaption("Login");

		List<Doctor> doctors = HibernateDataSource.getInstance().findAll(
				Doctor.class);

		VerticalComponentGroup group = new VerticalComponentGroup();
		for (final Doctor doctor : doctors) {
			NavigationButton button = new NavigationButton();
			button.setCaption(doctor.getFirstName() + " " + doctor.getName());
			button.addClickListener(new NavigationButtonClickListener() {
				private static final long serialVersionUID = -8437760343468698966L;

				@Override
				public void buttonClick(NavigationButtonClickEvent event) {
					Context.getCurrent().setDoctor(doctor);
					SessionOverView sessionOverview = new SessionOverView();
					MyVaadinUI.getNavigationManager().navigateTo(
							sessionOverview);
				}
			});
			button.setIcon(new ClassResource(doctor.getImageUrl()));
			group.addComponent(button);
		}
		setContent(group);

		// Toolbar
		if (doctors.size() == 0) {
			// allow creating test data
			Button test = new Button(null, new Button.ClickListener() {
				private static final long serialVersionUID = -261072762324902276L;

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
}
