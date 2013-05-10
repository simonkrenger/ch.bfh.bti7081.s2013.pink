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

	private Type type;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

	public Session(Patient patient, Doctor doctor, Type type) {
		this.patient = patient;
		this.doctor = doctor;
		this.type = type;
	}

	@SuppressWarnings("unused")
	private Session() {
		// needed for Hibernate
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

	public Type getType() {
		return type;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}

	public static enum Type {
		// TODO
	}
}
