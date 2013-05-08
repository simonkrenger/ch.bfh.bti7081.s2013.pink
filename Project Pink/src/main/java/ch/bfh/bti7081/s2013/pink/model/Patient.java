package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Patient extends Person {
	@OneToMany
	private List<Allergy> allergies = new LinkedList<Allergy>();
	@OneToMany
	private List<Warning> warnings = new LinkedList<Warning>();
	@OneToMany
	private List<MedicationPrescription> prescriptions = new LinkedList<MedicationPrescription>();
	@OneToMany
	private List<Note> notes = new LinkedList<Note>();

	public Patient(String firstName, String name) {
		super(firstName, name);
	}

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void addAllergy(Allergy allergy) {
		allergies.add(allergy);
	}
}
