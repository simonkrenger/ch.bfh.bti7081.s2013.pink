/**
 * Class that shows the notes of a session and they can be edited
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
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

public class SessionNotesView extends NavigationView {
	private static final long serialVersionUID = -6257562003847635377L;

	private HibernateDataSource ds = HibernateDataSource.getInstance();

	/**
	 * Can be a person, session, ... anything that has notes
	 */
	private NoteHolder noteHolder;
	private TextArea noteText;
	private Layout notes;
	private Layout layout;
	public String[] text;
	private Note[] editableNote;
	private int count;
	private int noteId;

	public SessionNotesView(NoteHolder holder) {
		noteHolder = holder;
		editableNote = new Note[50];
		text = new String[50];
		setCaption("Notes");
		layout = new VerticalLayout();
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
				notes.removeAllComponents();
				updateNotes();
			}
		}));
		layout.addComponent(new Button("Save", new Button.ClickListener() {
			private static final long serialVersionUID = 2366787821082710668L;

			@Override
			public void buttonClick(ClickEvent event) {
				editableNote[noteId].updateText(noteText.getValue());
				editableNote[noteId] = ds.saveOrUpdate(editableNote[noteId]);
				notes.removeAllComponents();
				updateNotes();
			}
		}));
		updateNotes();
		layout.addComponent(notes);
		setContent(layout);
	}

	private void updateNotes() {
		for (final Note n : noteHolder.getNotes()) {
			text[count] = n.getText();
			editableNote[count] = n;
			notes.addComponent(new NoteView(n));
			notes.addComponent(new Button("Edit Note",
					new Button.ClickListener() {
						private int noteCount = count;
						private static final long serialVersionUID = 2366787821082710668L;

						@Override
						public void buttonClick(ClickEvent event) {
							noteId = noteCount;
							noteText.setValue(text[noteCount]);
							notes.removeAllComponents();
							updateNotes();

						}
					}));
			count++;
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

