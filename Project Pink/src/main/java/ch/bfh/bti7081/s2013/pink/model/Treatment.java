package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Class to represent a treatment. A treatment consists of a name, both a
 * {@link Patient} and a responsible {@link Doctor} as well as other doctors. A
 * treatment is often the result of a {@link Diagnosis}.
 * 
 * @author chris
 * 
 */
@Entity
public class Treatment implements Serializable, NoteHolder {
	private static final long serialVersionUID = -1047887571011750666L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Name of the treatment
	 */
	private String name;

	/**
	 * Patient affected by the treatment
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;

	/**
	 * Doctor that is responsible for this treatment
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Doctor responsibleMD;

	/**
	 * Doctors involved in this treatment
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Doctor> attendingMDs = new LinkedList<Doctor>();

	/**
	 * Diagnosis on which this treatment is based
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Diagnosis> basedOnDiagnosis = new LinkedList<Diagnosis>();

	/**
	 * Notes for this treatment
	 */
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

	/**
	 * Constructor for the class, requires a name for a treatment, a
	 * {@link Patient} and a responsible {@link Doctor}
	 * 
	 * @param name
	 *            Name of the treatment
	 * @param patient
	 *            Patient affected by this treatment
	 * @param responsibleMD
	 *            Doctor that is responsible for this treatment
	 */
	public Treatment(String name, Patient patient, Doctor responsibleMD) {
		this.name = name;
		this.patient = patient;
		this.responsibleMD = responsibleMD;
	}

	@SuppressWarnings("unused")
	private Treatment() {
		// needed for Hibernate
	}

	public String getName() {
		return name;
	}

	public Patient getPatient() {
		return patient;
	}

	public Doctor getResponsibleMD() {
		return responsibleMD;
	}

	public List<Doctor> getAttendingMDs() {
		return attendingMDs;
	}

	public void addAttendingMD(Doctor doctor) {
		attendingMDs.add(doctor);
	}

	public List<Diagnosis> getDiagnoses() {
		return basedOnDiagnosis;
	}

	public void addDiagnosis(Diagnosis diagnosis) {
		basedOnDiagnosis.add(diagnosis);

	}

	public List<Note> getNotes() {
		return notes;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", name=" + name + ", patient="
				+ patient + ", responsibleMD=" + responsibleMD
				+ ", attendingMDs=" + attendingMDs + ", basedOnDiagnosis="
				+ basedOnDiagnosis + ", notes=" + notes + "]";
	}
}
