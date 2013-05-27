package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Class to represent a medication prescription. The prescription is basically
 * the combination of a dose and a medication. In addition, the prescription
 * holds the doctor responsible for prescribing the medication.
 * 
 * @author chris
 * 
 */
@Entity
public class MedicationPrescription implements Serializable {
	private static final long serialVersionUID = -5008856064969172587L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Reason for the prescription
	 */
	private String reason;
	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Medication medicine;

	@OneToOne(cascade = { CascadeType.PERSIST })
	private Dose dose;
	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Doctor prescriber;
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Note> notes = new LinkedList<Note>();

	/**
	 * Constructor for the class
	 * 
	 * @param reason
	 *            Reason for this prescription.
	 * @param medicine
	 *            Medication that is prescribed
	 * @param dose
	 *            Dosage of the medication
	 * @param prescriber
	 *            <code>Doctor</code> that makes the prescription
	 */
	public MedicationPrescription(String reason, Medication medicine, Dose dose,
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

	public Medication getMedicine() {
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
