package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Patient extends Person {
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Allergy> allergies = new LinkedList<Allergy>();

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Warning> warnings = new LinkedList<Warning>();

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<MedicationPrescription> prescriptions = new LinkedList<MedicationPrescription>();

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

	public Patient(String firstName, String name) {
		super(firstName, name);
	}

	@SuppressWarnings("unused")
	private Patient() {
		// needed for Hibernate
	}

	public List<Allergy> getAllergies() {
		return allergies;
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
}
