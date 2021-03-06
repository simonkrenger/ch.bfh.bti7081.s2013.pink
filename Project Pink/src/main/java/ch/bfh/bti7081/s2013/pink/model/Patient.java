package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Class that represents a patient.
 * 
 * @author chris
 * 
 */
@Entity
public class Patient extends Person implements NoteHolder {
	private static final long serialVersionUID = 6206530937924052846L;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Allergy> allergies = new LinkedList<Allergy>();

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Warning> warnings = new LinkedList<Warning>();

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<MedicationPrescription> prescriptions = new LinkedList<MedicationPrescription>();

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Note> notes = new LinkedList<Note>();

	public Patient(String firstName, String name, String imageUrl) {
		super(firstName, name, imageUrl);
	}

	@SuppressWarnings("unused")
	private Patient() {
		// needed for Hibernate
	}

	public List<Allergy> getAllergies() {
		return allergies;
	}

	/**
	 * Returns TRUE if a patient has associated {@link Warning}s.
	 * 
	 * @return TRUE if the patient has warnings
	 */
	public boolean hasWarnings() {
		return this.warnings.size() > 0;
	}

	public void addAllergy(Allergy allergy) {
		allergies.add(allergy);
	}

	public List<Warning> getWarnings() {
		return warnings;
	}

	public void addWarning(Warning warning) {
		warnings.add(warning);
	}

	public List<MedicationPrescription> getPrescriptions() {
		return prescriptions;
	}

	public void addPrescription(MedicationPrescription prescription) {
		prescriptions.add(prescription);
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		return "Patient [name=" + getFirstName() + " " + getName()
				+ ", allergies=" + allergies + ", warnings=" + warnings
				+ ", prescriptions=" + prescriptions + ", notes=" + notes + "]";
	}

}
