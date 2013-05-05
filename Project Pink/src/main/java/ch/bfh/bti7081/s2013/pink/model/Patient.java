package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class Patient extends Person {
	private List<Allergy> allergies = new LinkedList<Allergy>();
	private List<Warning> warnings = new LinkedList<Warning>();
	private List<MedicationPrescription> prescriptions = new LinkedList<MedicationPrescription>();
	private List<Note> notes = new LinkedList<Note>();

	public List<Allergy> getAllergies() {
		return allergies;
	}

	public void addAllergy(Allergy allergy) {
		allergies.add(allergy);
	}
}
