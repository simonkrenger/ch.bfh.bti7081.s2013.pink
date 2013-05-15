package ch.bfh.bti7081.s2013.pink.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Session {
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

	public boolean isTranslationPossible(SessionState type) {
		return sessionState.getPossibleNextStateType().contains(type);
	}

	public boolean isEditable() {
		return sessionState.isEditable();
	}

	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}
}
