package ch.bfh.bti7081.s2013.pink.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.NoteHolder;
import ch.bfh.bti7081.s2013.pink.view.WarningPopover.UpdateListener;

import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class NotesPopover extends Popover {
	private static final long serialVersionUID = -6257562003847635377L;

	private HibernateDataSource ds = HibernateDataSource.getInstance();

	private boolean addingEnabled = true;
	private boolean editable = false;
	private Note editingNote;

	private List<UpdateListener> listeners = new LinkedList<UpdateListener>();

	/**
	 * Can be a person, session, ... anything that has notes
	 */
	private NoteHolder noteHolder;
	private TextArea noteText;
	private Layout notes;

	private Button closeBtn;

	private Button addBtn;

	private Button saveBtn;

	public NotesPopover(NoteHolder holder) {
		setWidth("350px");
		noteHolder = holder;

		setCaption("Notes");

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		notes = new VerticalLayout();

		noteText = new TextArea();
		noteText.setWidth("100%");
		noteText.setVisible(false);
		layout.addComponent(noteText);

		HorizontalButtonGroup buttons = new HorizontalButtonGroup();
		buttons.setWidth("100%");
		layout.addComponent(buttons);

		addBtn = new Button("Add Note");
		addBtn.setWidth("50%");
		if (addingEnabled)
			buttons.addComponent(addBtn);

		saveBtn = new Button("Save Note");
		if (addingEnabled)
			saveBtn.setWidth("50%");
		else
			saveBtn.setWidth("100%");
		saveBtn.setVisible(false);
		buttons.addComponent(saveBtn);

		closeBtn = new Button("Close");
		closeBtn.setWidth("50%");
		buttons.addComponent(closeBtn);

		closeBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 2366787821082710668L;

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		addBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -5450856216156209254L;

			@Override
			public void buttonClick(ClickEvent event) {
				noteText.setVisible(true);
				noteText.focus();
				addBtn.setVisible(false);
				saveBtn.setVisible(true);
			}
		});
		saveBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 2366787821082710668L;

			@Override
			public void buttonClick(ClickEvent event) {
				noteText.setVisible(false);
				addBtn.setVisible(true);
				saveBtn.setVisible(false);

				if (editingNote != null) {
					editingNote.updateText(noteText.getValue());
					ds.saveOrUpdate(editingNote);
					editingNote = null;
					noteHolder = ds.reload(noteHolder);
				} else {
					Note note = ds.saveOrUpdate(new Note(noteText.getValue()));
					noteHolder.addNote(note);
					noteHolder = ds.saveOrUpdate(noteHolder);
				}
				noteText.setValue("");
				notes.removeAllComponents();
				updateNotes();
				for (UpdateListener listener : listeners)
					listener.update(notes.getComponentCount());
			}
		});
		updateNotes();
		layout.addComponent(notes);
		setContent(layout);
	}

	private void updateNotes() {
		for (Note n : noteHolder.getNotes()) {
			notes.addComponent(new NoteView(n));
		}
	}

	public void setAddingEnabled(boolean enabled) {
		addingEnabled = enabled;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		notes.removeAllComponents();
		updateNotes();
	}

	private DateFormat df = new SimpleDateFormat();

	private class NoteView extends CustomComponent {
		private static final long serialVersionUID = -6439006060532901266L;

		public NoteView(final Note note) {
			setStyleName("p-note");
			Label label = new Label();
			label.setCaption(df.format(note.getTimestamp()));
			label.setValue(note.getText());
			VerticalLayout layout = new VerticalLayout();
			layout.addComponent(label);

			Button removeButton = new Button();
			removeButton.setWidth("20px");
			removeButton.setHeight("20px");
			removeButton.setStyleName(BaseTheme.BUTTON_LINK);
			removeButton.addStyleName("deleteButton");
			removeButton.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 5650763451236883119L;

				@Override
				public void buttonClick(ClickEvent event) {
					noteHolder.getNotes().remove(note);
					noteHolder = ds.saveOrUpdate(noteHolder);
					ds.remove(note);
					notes.removeAllComponents();
					updateNotes();
				}
			});

			Button editButton = new Button();
			editButton.setWidth("20px");
			editButton.setHeight("20px");
			editButton.setStyleName(BaseTheme.BUTTON_LINK);
			editButton.addStyleName("editButton");
			editButton.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 5650763451236883119L;

				@Override
				public void buttonClick(ClickEvent event) {
					// Don't overwrite text if there's a note in the making
					if (noteText.isVisible()
							&& noteText.getValue().length() > 0)
						return;

					editingNote = note;
					noteText.setValue(note.getText());
					noteText.setVisible(true);
					noteText.focus();
					saveBtn.setVisible(true);
					addBtn.setVisible(false);
				}
			});

			HorizontalLayout hl = new HorizontalLayout();
			hl.setSpacing(true);
			hl.setStyleName("noteButtons");
			if (editable)
				hl.addComponent(editButton);
			hl.addComponent(removeButton);

			layout.addComponent(hl);

			setCompositionRoot(layout);
		}
	}

	public void addUpdateListener(UpdateListener listener) {
		listeners.add(listener);
	}
}
