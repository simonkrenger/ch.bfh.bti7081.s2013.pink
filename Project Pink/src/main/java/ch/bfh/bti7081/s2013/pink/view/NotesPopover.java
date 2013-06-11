package ch.bfh.bti7081.s2013.pink.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.NoteHolder;

import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class NotesPopover extends Popover {
	private static final long serialVersionUID = -6257562003847635377L;

	private HibernateDataSource ds = HibernateDataSource.getInstance();

	/**
	 * Can be a person, session, ... anything that has notes
	 */
	private NoteHolder noteHolder;
	private TextArea noteText;
	private Layout notes;

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

		final Button addBtn = new Button("Add Note");
		addBtn.setWidth("50%");
		buttons.addComponent(addBtn);

		final Button saveBtn = new Button("Save Note");
		saveBtn.setWidth("50%");
		saveBtn.setVisible(false);
		buttons.addComponent(saveBtn);

		final Button closeBtn = new Button("Close");
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

				Note note = ds.saveOrUpdate(new Note(noteText.getValue()));
				noteHolder.addNote(note);
				noteHolder = ds.saveOrUpdate(noteHolder);
				noteText.setValue("");
				notes.removeAllComponents();
				updateNotes();
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
			layout.addComponent(removeButton);
			setCompositionRoot(layout);
		}
	}
}
