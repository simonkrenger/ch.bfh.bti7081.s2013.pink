package ch.bfh.bti7081.s2013.pink.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ch.bfh.bti7081.s2013.pink.model.Allergy.Severity;
import ch.bfh.bti7081.s2013.pink.model.Dose.Period;

public class TestDataSource {
	private static int noteNumber;

	private Random random = new Random();
	private final SessionFactory sessionFactory;

	public TestDataSource() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public void clearTableAndCreateTestData() {
		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();

		// FIXME: Delete existing data
		for (Object o : session.createCriteria(MedicationPrescription.class)
				.list())
			session.delete(o);
		for (Object o : session.createCriteria(Patient.class).list())
			session.delete(o);
		for (Object o : session.createCriteria(Allergy.class).list())
			session.delete(o);
		for (Object o : session.createCriteria(Doctor.class).list())
			session.delete(o);
		for (Object o : session.createCriteria(Medicine.class).list())
			session.delete(o);
		for (Object o : session.createCriteria(Ingredient.class).list())
			session.delete(o);
		for (Object o : session.createCriteria(Session.class).list())
			session.delete(o);

		// Create Ingredients
		List<Ingredient> ingredients = new LinkedList<Ingredient>();
		for (String name : new String[] { "cetrizini dihydrochloridum",
				"toremifen", "torasemid", "aciclovir", "spirapril",
				"estradiolvalerat", "dienogest", "diazepam" }) {
			ingredients.add((Ingredient) session.merge(new Ingredient(name)));
		}

		// Create Doctors
		List<Doctor> doctors = new LinkedList<Doctor>();
		doctors.add((Doctor) session.merge(createDoctor("Marco", "Berger",
				"Kognitive Psychologie", "Pädagogische Psychologie")));
		doctors.add((Doctor) session.merge(createDoctor("Franziska", "Corradi",
				"Neuropsychologie", "Mathematische Psychologie")));
		doctors.add((Doctor) session.merge(createDoctor("Simon", "Krenger",
				"Diagnostik", "Evaluation")));
		doctors.add((Doctor) session.merge(createDoctor("Christian", "Meyer",
				"Entwicklungspsychologie", "Klinische Psychologie",
				"Diagnostik")));
		doctors.add((Doctor) session.merge(createDoctor("Christoph", "Seiler",
				"Klinische Psychologie", "Kommunikationspsychologie")));

		// Create Patients
		String[] names = new String[] { "Haas", "Adler", "Walter", "Koch",
				"Mangold", "Sauber", "Pohl", "Nause", "Unold", "Osterwalder",
				"Stadelmann", "Dietz", "Hartmann", "Hess", "Spiess",
				"Bachmann", "Baum", "Meyer", "Lehmann", "Pulizer", "Meier",
				"Eberhardt", "Fischer", "Müller", "Wirth", "Kästner",
				"Rindlisbacher", "Reinhard", "Wolf", "Kramer", "Eiholzer",
				"Sager", "Gehringer", "von Wartburg", "Loosli", "Wagner",
				"Geisler", "Salzmann", "Jähn", "Seidel", "Seifert", "Trub",
				"Bauer", "Becker", "Jufer", "Eigenwillig", "Gutknecht",
				"Niederberger", "Schmid", "Zimmermann", "Fröhlich" };
		String[] firstNames = new String[] { "Elias", "Léa", "Ben", "Emma",
				"Lena", "Alina", "Leon", "Robin", "Matteo", "Lia", "Charlotte",
				"Levin", "Elena", "Emilie", "Noah", "Sara", "Olivia", "Lara",
				"Alice", "Lukas", "Emma", "Jan", "Luca", "Nico", "Nina", "Eva",
				"Clara", "Jonas", "Alicia", "Leandro", "Lara", "Sofia", "Gian",
				"Dario", "Anna", "Sara", "David", "Camille", "Zoé", "Laura",
				"Sophia", "Lionel", "Jade", "Elin", "Samuel", "Lucie", "Jana",
				"Chloé", "Mia", "Julian", "Julie", "Chiara", "Lea", "Elisa",
				"Tim", "Sophie", "Lina", "Julia", "Leonie", "Anaïs" };
		String[] warnings = new String[] {
				"Ist an Dienstagen leicht gewalttätig.", "Nicht füttern!",
				"Meint er sei Programmierer.", "\"; -- SELECT * FROM Notes;",
				"Findet Dilbert lustig.", "Mag Visual Basic." };
		List<Patient> patients = new LinkedList<Patient>();
		for (int i = 0; i < names.length; i++) {
			Patient p = new Patient(firstNames[i], names[i]);
			// Several allergies are possible, so there are two chances
			for (int j = 0; j < 2; j++) {
				if (random.nextBoolean() && random.nextBoolean()) {
					Allergy a = new Allergy(getRandom(ingredients),
							getRandom(Severity.values()));
					a = (Allergy) session.merge(a);
					p.addAllergy(a);
				}
			}
			if (random.nextBoolean() && random.nextBoolean())
				p.addWarning((Warning) session.merge(new Warning(
						getRandom(warnings))));
			patients.add((Patient) session.merge(p));
		}

		// Create Medicines
		String[] mediNames = new String[] { "Jenataria", "Qlatol", "Eatcin",
				"Winzym", "Oliphrom", "Cephrol", "Valium" };
		for (String name : mediNames) {
			Medicine m = new Medicine(name);
			m.addIngredient(getRandom(ingredients));
			if (random.nextBoolean() && random.nextBoolean())
				m.addIngredient(getRandom(ingredients));

			session.persist(m);
		}

		// Create Sessions
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.MINUTE, 0);
		cal.add(Calendar.HOUR, 1);
		for (Object o : session.createCriteria(Patient.class).list()) {
			if (random.nextBoolean() || random.nextBoolean())
				continue;
			Patient p = (Patient) o;
			Session s = new Session(p, getRandom(doctors));
			// TODO: better dates
			s.setTimeStart(cal.getTime());
			cal.add(Calendar.HOUR, 1);
			s.setTimeEnd(cal.getTime());
			if (random.nextBoolean() && random.nextBoolean()
					&& random.nextBoolean())
				s.addNote(new Note("Your lucky number is "
						+ random.nextInt(100)));
			session.persist(s);
		}

		session.getTransaction().commit();
		session.close();
	}

	private <T> T getRandom(T[] list) {
		return list[random.nextInt(list.length)];
	}

	private <T> T getRandom(List<T> list) {
		return list.get(random.nextInt(list.size()));
	}

	private Doctor createDoctor(String firstName, String name,
			String... specializations) {
		Doctor result = new Doctor(firstName, name);
		for (String specialization : specializations)
			result.addSpecialization(specialization);
		return result;
	}

	public static Note getNote() {
		return new Note("Note #" + noteNumber++);
	}

	public static Doctor getDoctor() {
		Doctor dr = new Doctor("Gregory", "House", "house.png");
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
		Patient p = new Patient("Pink", "Panter", "mascot.png");
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
		Date startTime = new Date();
		Date endTime = new Date();
		s.setTimeStart(startTime);
		s.setTimeEnd(endTime);
		s.addNote(getNote());
		return s;
	}
}
