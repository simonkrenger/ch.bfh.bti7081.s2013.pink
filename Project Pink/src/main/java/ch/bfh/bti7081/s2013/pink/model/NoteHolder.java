package ch.bfh.bti7081.s2013.pink.model;

import java.util.List;

public interface NoteHolder {
	void addNote(Note note);

	List<Note> getNotes();
}
