package ch.bfh.bti7081.s2013.pink;

import javax.servlet.ServletException;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;

public class PinkServlet extends TouchKitServlet {
	private static final long serialVersionUID = -2363354115903188114L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		TouchKitSettings s = getTouchKitSettings();

		s.getApplicationIcons().addApplicationIcon(
				"VAADIN/themes/pinktheme/icon.png");
		s.getWebAppSettings().setStartupImage(
				"VAADIN/themes/pinktheme/startup.png");
		s.getWebAppSettings().setWebAppCapable(true);
	}
}
