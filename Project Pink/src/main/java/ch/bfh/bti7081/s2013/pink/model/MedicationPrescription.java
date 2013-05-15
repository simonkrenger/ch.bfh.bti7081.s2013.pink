package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MedicationPrescription {
	@Id
	@GeneratedValue
	private Long id;

	private String reason;
	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Medicine medicine;

	@OneToOne(cascade = { CascadeType.PERSIST })
	private Dose dose;
	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Doctor prescriber;
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Note> notes = new LinkedList<Note>();

	public MedicationPrescription(String reason, Medicine medicine, Dose dose,
			Doctor prescriber) {
		this.reason = reason;
		this.medicine = medicine;
		this.dose = dose;
		this.prescriber = prescriber;
	}

	@SuppressWarnings("unused")
	private MedicationPrescription() {
		// needed for Hibernate
	}

	public String getReason() {
		return reason;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public Dose getDose() {
		return dose;
	}

	public Doctor getPrescriber() {
		return prescriber;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void addNote(Note note) {
		notes.add(note);
	}
}
