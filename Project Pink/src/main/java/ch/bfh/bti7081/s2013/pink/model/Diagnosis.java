package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Diagnosis implements Serializable {
	private static final long serialVersionUID = 7849922413540771230L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;

	@ElementCollection
	@CollectionTable(name = "Symptoms", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "symptom")
	private List<String> symptoms = new LinkedList<String>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Doctor> responsibleMDs = new LinkedList<Doctor>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Treatment> treatments = new LinkedList<Treatment>();

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> notes = new LinkedList<Note>();

	public Diagnosis(String name, String description, Patient patient,
			Doctor doctor) {
		this.name = name;
		this.description = description;
		this.patient = patient;
		this.responsibleMDs.add(doctor);
	}

	@SuppressWarnings("unused")
	private Diagnosis() {
		// needed for Hibernate
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void addSymptom(String symptom) {
		symptoms.add(symptom);
	}

	public List<Doctor> getResponsibleMDs() {
		return responsibleMDs;
	}

	public void addResponsibleMD(Doctor doctor) {
		responsibleMDs.add(doctor);
	}

	public Patient getPatient() {
		return patient;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void addTreatment(Treatment treatment) {
		treatments.add(treatment);
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}
}
