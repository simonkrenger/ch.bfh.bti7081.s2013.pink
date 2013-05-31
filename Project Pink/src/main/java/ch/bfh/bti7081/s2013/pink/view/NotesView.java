package ch.bfh.bti7081.s2013.pink.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.NoteHolder;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class NotesView extends NavigationView {
	private static final long serialVersionUID = -6257562003847635377L;

	private HibernateDataSource ds = HibernateDataSource.getInstance();

	/**
	 * Can be a person, session, ... anything that has notes
	 */
	private NoteHolder noteHolder;
	private TextArea noteText;
	private Layout notes;

	public NotesView(NoteHolder holder) {
		noteHolder = holder;

		setCaption("Notes");

		Layout layout = new VerticalLayout();
		notes = new VerticalLayout();

		noteText = new TextArea();
		noteText.setWidth("100%");
		layout.addComponent(noteText);
		layout.addComponent(new Button("Add Note", new Button.ClickListener() {
			private static final long serialVersionUID = 2366787821082710668L;

			@Override
			public void buttonClick(ClickEvent event) {
				Note note = ds.saveOrUpdate(new Note(noteText.getValue()));
				noteHolder.addNote(note);
				ds.saveOrUpdate(noteHolder);
				notes.removeAllComponents();
				updateNotes();
			}
		}));
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

	private class NoteView extends Label {
		private static final long serialVersionUID = -6439006060532901266L;

		public NoteView(Note note) {
			setStyleName("p-note");
			setCaption(df.format(note.getTimestamp()));
			setValue(note.getText());
		}
	}
}
