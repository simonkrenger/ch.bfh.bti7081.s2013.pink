package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Class to represent a session with a {@link Doctor} and a {@link Patient}
 * 
 * @author Christian Meyer <chrigu.meyer@gmail.com>
 */
@Entity
public class Session implements Serializable, NoteHolder {
	private static final long serialVersionUID = 4037114189034652676L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Doctor doctor;

	private Date timeStart;
	private Date timeEnd;

	private SessionState sessionState = SessionState.INVALID;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

	public Session(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
		this.sessionState = SessionState.PLANNED;
	}

	@SuppressWarnings("unused")
	private Session() {
		// needed for Hibernate
	}

	public SessionState getSessionState() {
		return sessionState;
	}

	public void changeState(SessionState type) {
		if (isTranslationPossible(type)) {
			sessionState = type;
		}
	}

	/**
	 * Checks if the transition between the current state and a new state is
	 * possible.
	 * 
	 * @param type
	 *            The type of the new state
	 * @return Returns TRUE if the transition is possible, else the method
	 *         returns FALSE.
	 */
	public boolean isTranslationPossible(SessionState type) {
		return sessionState.getPossibleNextStateType().contains(type);
	}

	/**
	 * Method to check if a session is editable
	 * 
	 * @return Returns TRUE if the session is editable
	 */
	public boolean isEditable() {
		return sessionState.isEditable();
	}

	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setTimeStart(Date startTime) {
		this.timeStart = startTime;
	}

	public void setTimeEnd(Date endTime) {
		this.timeEnd = endTime;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void addNote(Note note) {
		if (isEditable()) {
			notes.add(note);
		} else {
			throw new IllegalStateException(
					"Failed to add note, current state is not editable.");
		}
	}

	public List<Note> getNotes() {
		return notes;
	}
}
