package ch.bfh.bti7081.s2013.pink.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Session {
	private Patient patient;
	private Doctor doctors;

	private Date timeStart;
	private Date timeEnd;

	private SessionState sessionState = SessionState.INVALID;

	private List<Note> notes = new LinkedList<Note>();

	public SessionState getSessionState() {
		return sessionState;
	}

	public void changeState(SessionState type) {
		if (isTranslationPossible(type)) {
			sessionState = type;
		}
	}

	public boolean isTranslationPossible(SessionState type) {
		return getSessionState().getPossibleNextStateType().contains(type);
	}

	public boolean isEditable() {
		return getSessionState().isEditable();
	}
}
