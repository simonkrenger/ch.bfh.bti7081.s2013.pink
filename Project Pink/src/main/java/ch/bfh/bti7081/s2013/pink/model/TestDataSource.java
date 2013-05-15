package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.Allergy.Severity;
import ch.bfh.bti7081.s2013.pink.model.Dose.Period;

public class TestDataSource {
	private static int noteNumber;

	public List<Patient> findPatient(String name) {
		List<Patient> patients = new LinkedList<Patient>();
		// TODO
		return patients;
	}

	public static Note getNote() {
		return new Note("Note #" + noteNumber++);
	}

	public static Doctor getDoctor() {
		Doctor dr = new Doctor("Gregory", "House");
		dr.addSpecialization("diagnostics");
		dr.addSpecialization("sociopath");
		return dr;
	}

	public static Ingredient getIngredient() {
		return new Ingredient("Hay");
	}

	public static Allergy getAllergy() {
		return new Allergy(getIngredient(), Severity.SEVERE);
	}

	public static Medicine getMedicine() {
		Ingredient i = new Ingredient("Placebium");

		Medicine m = new Medicine("Placebo");
		m.addIngredient(i);
		m.addEffect("effect");
		m.addEffect("another effect");
		m.addSideeffect("tough luck");
		return m;
	}

	public static MedicationPrescription getPrescription() {
		return new MedicationPrescription("Just for fun", getMedicine(),
				getDose(), getDoctor());
	}

	public static Dose getDose() {
		return new Dose(5, 2, Period.DAY);
	}

	public static Patient getPatient() {
		Patient p = new Patient("Christian", "Meyer");
		p.addAllergy(new Allergy(new Ingredient("Bullshit"),
				Allergy.Severity.BENIGN));
		p.addAllergy(getAllergy());
		p.addPrescription(getPrescription());
		p.addNote(new Note("Thinks he is the Doctor."));
		Warning w = new Warning("Highly docile");
		w.addUpdate(new Note("Might've been wrong"));
		w.addUpdate(new Note("Or not. Confused."));
		p.addWarning(w);
		return p;
	}

	public static Treatment getTreatment() {
		Treatment t = new Treatment("Drilling a hole in patient's head",
				getPatient(), getDoctor());
		t.addAttendingMD(t.getResponsibleMD());
		t.addNote(getNote());
		return t;
	}

	public static Diagnosis getDiagnosis() {
		Treatment t = getTreatment();
		Diagnosis d = new Diagnosis("Schizophrenia", "Some description",
				t.getPatient(), t.getResponsibleMD());
		d.addTreatment(t);
		t.addDiagnosis(d);
		d.addSymptom("Some symptom");
		return d;
	}

	public static Session getSession() {
		Session s = new Session(getPatient(), getDoctor());
		s.addNote(getNote());
		return s;
	}
}
