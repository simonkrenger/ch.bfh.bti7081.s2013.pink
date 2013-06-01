package ch.bfh.bti7081.s2013.pink.model;

import java.util.Arrays;
import java.util.Collection;

/**
 * Class to represent the state of the session.
 * 
 * @author sc0238
 * @since 2013-05-09 10:49
 */
public enum SessionState {
	/**
	 * Invalid State.
	 */
	INVALID("Invalid", null, false),

	/**
	 * Session is being planned.
	 */
	PLANNED("Planned", "Session has been scheduled.", true),

	/**
	 * Session has started and is in progress.
	 */
	STARTED("Started", "Session is being conducted.", true),

	/**
	 * Session has been aborted.
	 */
	ABORTED("Aborted", "Session has been prematurely be closed.", true),

	/**
	 * Session has been successfully finished.
	 */
	FINISHED("Finished", "Session has been conducted successfully", true),

	/**
	 * Session has been cancelled.
	 */
	CANCELLED("Cancelled", "Session has been cancelled by Doctor or Patient",
			true),

	/**
	 * Session has been archived.
	 */
	ARCHIVED("Archived", "Session has been archived for storage", false),

	/**
	 * Session has been reopened for re-editing.
	 */
	REOPENED("Reopened", "Session been reopened for editing", true);

	private String name;
	private String description;
	private boolean editable;

	/**
	 * Constructor for SessionState
	 * 
	 * @param name
	 *            Name of the state
	 * @param description
	 *            Description of the state
	 * @param editable
	 *            Definition if the state is editable, basically if one is
	 *            allowed to add a note to the session
	 */
	private SessionState(String name, String description, boolean editable) {
		this.name = name;
		this.description = description;
		this.editable = editable;
	}

	/**
	 * Gets the current state type.
	 * 
	 * @return SessionStateType to identify.
	 */
	public SessionState getStateType() {
		return this;
	}

	/**
	 * Gets the next possible session states.
	 * 
	 * @return List of SessionStateTypes.
	 */
	public Collection<SessionState> getPossibleNextStateType() {
		switch (this) {
		case PLANNED:
			return Arrays.asList(STARTED, CANCELLED, PLANNED);

		case STARTED:
			return Arrays.asList(FINISHED, ABORTED);

		case ABORTED:
			return Arrays.asList(PLANNED, ARCHIVED, STARTED);

		case FINISHED:
			return Arrays.asList(ARCHIVED, STARTED);

		case CANCELLED:
		case REOPENED:
			return Arrays.asList(ARCHIVED);

		case ARCHIVED:
			return Arrays.asList(REOPENED);

		case INVALID:
		default:
			return Arrays.asList();
		}
	}

	/**
	 * Gets the next default session state.
	 * 
	 * @return SessionState suggestion.
	 */
	public SessionState getDefaultNextState() {
		switch (this) {
		case PLANNED:
			return STARTED;

		case STARTED:
			return FINISHED;

		case ABORTED:
			return PLANNED;

		case FINISHED:
		case CANCELLED:
		case REOPENED:
			return ARCHIVED;

		case ARCHIVED:
			return REOPENED;

		case INVALID:
		default:
			return null;
		}
	}

	/**
	 * Gets the DisplayName of the Session.
	 * 
	 * @return Name of the Session Type.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the Description of the Session.
	 * 
	 * @return Description of the State.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets a Flag if the Session is editable.
	 * 
	 * @return Flag is Session allows editing.
	 */
	public boolean isEditable() {
		return editable;
	}
}
