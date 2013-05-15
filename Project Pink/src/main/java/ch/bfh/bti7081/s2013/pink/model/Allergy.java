package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Allergy {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Ingredient trigger;
	private Severity severity;

	@OneToMany
	private List<Note> notes = new LinkedList<Note>();

	public Allergy(Ingredient trigger, Severity severity) {
		this.trigger = trigger;
		this.severity = severity;
	}

	@SuppressWarnings("unused")
	private Allergy() {
		// needed for Hibernate
	}

	public Ingredient getTrigger() {
		return trigger;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}

	public static enum Severity {
		BENIGN, SEVERE, LETHAL;
	}

	@Override
	public String toString() {
		switch (severity) {
		case BENIGN:
			return "Benign allergy to " + trigger;
		case SEVERE:
			return "Severe allergy to " + trigger;
		case LETHAL:
			return "Lethal  allergy to " + trigger;
		default:
			return "Impossible allergy to " + trigger;
		}
	}
}
