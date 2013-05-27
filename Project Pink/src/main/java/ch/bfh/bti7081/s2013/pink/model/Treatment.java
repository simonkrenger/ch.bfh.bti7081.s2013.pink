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
 * <code>Patient</code> and a responsible <code>Doctor</code> as well as other
 * doctors. A treatment is often the result of a <code>Diagnosis</code>.
 * 
 * @author chris
 * 
 */
@Entity
public class Treatment implements Serializable {
	private static final long serialVersionUID = -1047887571011750666L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Doctor responsibleMD;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Doctor> attendingMDs = new LinkedList<Doctor>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Diagnosis> basedOnDiagnosis = new LinkedList<Diagnosis>();

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

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
