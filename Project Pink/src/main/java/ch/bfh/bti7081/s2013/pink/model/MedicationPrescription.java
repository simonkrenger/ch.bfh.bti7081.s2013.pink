package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Class to represent a medication prescription. The prescription is basically
 * the combination of a dose and a medication. In addition, the prescription
 * holds the doctor responsible for prescribing the medication.
 * 
 * @author chris
 * 
 */
@Entity
public class MedicationPrescription implements Serializable, NoteHolder {
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

    private Date startDate;

    private Date endDate;

	@OneToOne(cascade = { CascadeType.PERSIST })
	private Dose dose;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Doctor prescriber;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
	 *            {@link Doctor} that makes the prescription
     * @param startDate
     *            Start date of the prescription.
     * @param endDate
     *            End date of the prescription.
	 */
	public MedicationPrescription(String reason, Medication medicine,
			Doctor prescriber, Date startDate, Date endDate) {
		this.reason = reason;
		this.medicine = medicine;
		this.dose = dose;
		this.prescriber = prescriber;
        this.startDate = startDate;
        this.endDate = endDate;
	}

	@SuppressWarnings("unused")
	private MedicationPrescription() {
		// needed for Hibernate
	}

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date date)
    {
        this.startDate = date;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date date)
    {
        this.endDate = date;
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
