package ch.bfh.bti7081.s2013.pink.view;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Warning;

import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class WarningPopover extends Popover {
	private static final long serialVersionUID = -7641024230423576696L;

	private HibernateDataSource ds = HibernateDataSource.getInstance();

	private Patient patient;
	private List<UpdateListener> listeners = new LinkedList<UpdateListener>();

	public WarningPopover(Patient p) {
		setWidth("350px");
		this.patient = p;

		VerticalLayout verticalLayout = new VerticalLayout();

		final TextArea warningTxt = new TextArea();
		final Button saveBtn = new Button("Save Warning");
		final Button addBtn = new Button("Add Warning");
		final VerticalLayout warningContainer = new VerticalLayout();

		warningTxt.setWidth("100%");
		warningTxt.setHeight("4em");
		warningTxt.setVisible(false);
		verticalLayout.addComponent(warningTxt);

		saveBtn.setWidth("100%");
		saveBtn.setVisible(false);
		saveBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				warningTxt.setVisible(false);
				saveBtn.setVisible(false);
				addBtn.setVisible(true);

				Warning warning = new Warning(warningTxt.getValue());
				warning = ds.saveOrUpdate(warning);
				patient = ds.reload(patient);
				patient.addWarning(warning);
				patient = ds.saveOrUpdate(patient);

				warningContainer.removeAllComponents();
				updateWarnings(warningContainer);

				int size = patient.getWarnings().size();
				for (UpdateListener listener : listeners)
					listener.update(size);
			}
		});
		verticalLayout.addComponent(saveBtn);

		addBtn.setWidth("100%");
		addBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6222381946564353326L;

			@Override
			public void buttonClick(ClickEvent event) {
				warningTxt.setVisible(true);
				warningTxt.focus();
				saveBtn.setVisible(true);
				addBtn.setVisible(false);
			}
		});
		verticalLayout.addComponent(addBtn);

		updateWarnings(warningContainer);
		verticalLayout.addComponent(warningContainer);
		setContent(verticalLayout);
	}

	private void updateWarnings(ComponentContainer warningContainer) {
		for (Warning w : patient.getWarnings()) {
			VerticalComponentGroup group = new VerticalComponentGroup();
			group.addComponent(new Label(w.getText()));
			warningContainer.addComponent(group);
		}
	}

	public void addUpdateListener(UpdateListener listener) {
		listeners.add(listener);
	}

	public static interface UpdateListener {
		public void update(int size);
	}
}