package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Model for an allergy. An allergy is triggered by an {@link Ingredient} and
 * has a certain {@link Severity}. Notes can be added to an allergy.
 * 
 * @author chris
 * 
 */
@Entity
public class Allergy implements Serializable, NoteHolder {
	private static final long serialVersionUID = -7947437825782406243L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * The ingredient that triggers the allergy
	 */
	@ManyToOne
	private Ingredient trigger;

	/**
	 * Serverity of the allergy, see the Severity enumeration in this class.
	 */
	private Severity severity;

	/**
	 * Notes for a certain allergy
	 */
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Note> notes = new LinkedList<Note>();

	/**
	 * Constructor for the class, takes the ingredient which triggers the
	 * allergy and the severity as an argument.
	 * 
	 * @param trigger
	 *            Ingredient that triggers the allergy
	 * @param severity
	 *            Severity of the allergy in combination with this ingredient.
	 */
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

	/**
	 * Allergy severity, ranging from benign to lethal.
	 * 
	 * @author chris
	 * 
	 */
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
