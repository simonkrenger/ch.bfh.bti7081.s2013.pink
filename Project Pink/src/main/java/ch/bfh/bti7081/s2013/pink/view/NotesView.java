package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.model.NoteHolder;

import com.vaadin.addon.touchkit.ui.NavigationView;

public class NotesView extends NavigationView {
	private static final long serialVersionUID = -6257562003847635377L;

	/**
	 * Can be a person, session, ... anything that has notes
	 */
	private NoteHolder noteHolder;

	public NotesView(NoteHolder holder) {
		noteHolder = holder;
	}
}
