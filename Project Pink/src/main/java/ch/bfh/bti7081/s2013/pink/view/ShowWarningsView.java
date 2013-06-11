/**
 * Class that shows the the warnings, if there are any, from a patient
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class ShowWarningsView extends NavigationView {
	private static final long serialVersionUID = -6257562003847635377L;

	private Layout warningLayout;
	private Layout layout;
	private Patient patient;
	private List<Warning> warnings = new LinkedList<Warning>();

	public ShowWarningsView(Patient patient) {
		this.patient = patient;
		warnings = patient.getWarnings();
		setCaption("Warnings");
		layout = new VerticalLayout();
		warningLayout = new VerticalLayout();
		Iterator<Warning> iterator = warnings.iterator();
		while (iterator.hasNext()) {
			warningLayout.addComponent(new WarningView(iterator.next()));
		}
		layout.addComponent(warningLayout);
		setContent(layout);
	}

	private class WarningView extends Label {
		private static final long serialVersionUID = -6439006060532901266L;

		public WarningView(Warning warning) {
			setStyleName("p-warning");
			setValue(warning.getText());
		}
	}
}
