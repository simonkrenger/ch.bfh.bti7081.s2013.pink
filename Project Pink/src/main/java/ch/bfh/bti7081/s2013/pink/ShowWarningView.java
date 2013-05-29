package ch.bfh.bti7081.s2013.pink;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ShowWarningView extends UI {
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("Warnings");

		VerticalLayout content = new VerticalLayout();
		Label label = new Label("");
		label.setSizeUndefined();
		content.addComponent(label);
		content.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		content.setSizeFull();
		setContent(content);
	}
}