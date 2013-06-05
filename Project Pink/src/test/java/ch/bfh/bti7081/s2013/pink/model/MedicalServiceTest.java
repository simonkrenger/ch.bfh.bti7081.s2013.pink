package ch.bfh.bti7081.s2013.pink.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.bti7081.s2013.pink.medication.IMedicalService;
import ch.bfh.bti7081.s2013.pink.medication.LocalMedicalService;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 02.06.13
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public class MedicalServiceTest
{
    static IMedicalService medicalService;
    static MockPatientDataSource mockPatientDataSource;

    static Patient patient;
    static Ingredient nuts;

    static Allergy nutAllergy;

    static Medication mars;
    static Medication snickers;

    @BeforeClass
    public static void setup()
    {
        mockPatientDataSource = new MockPatientDataSource();
        medicalService = new LocalMedicalService(mockPatientDataSource);

        patient = new Patient("Hubert", "Hindenburg", ".jpg");
        nuts = new Ingredient("Nuts");

        nutAllergy = new Allergy(nuts, Allergy.Severity.LETHAL);
        patient.addAllergy(nutAllergy);

        mars = new Medication("MARS (TM)");
        snickers = new Medication("SNICKER (TM)");
        snickers.addIngredient(nuts);

		Context.getCurrent().setDoctor(
				new Doctor("Gregory", "House", "/images/mascot.png"));
    }

    @Before
    public void beforeTest()
    {
        mockPatientDataSource.clearLastSavedPatient();
    }

    @Test
    public void TestAllergyConflict()
    {
        boolean result = medicalService.prescribeMedicament(
                                    patient,
                                    snickers,
                                    new Dose(1, 1, Dose.Period.DAY),
                                    new Date(),
                                    new Date());

		assertFalse(result);
		assertNull(mockPatientDataSource.getLastSavedPatient());
    }

    @Test
    public void TestNoAllergyConflict()
    {
        boolean result = medicalService.prescribeMedicament(
                patient,
                mars,
                new Dose(1, 1, Dose.Period.DAY),
                new Date(),
                new Date());

		assertTrue(result);
		assertFalse(mockPatientDataSource.getLastSavedPatient()
				.getPrescriptions().isEmpty());
    }

    @Test
    public void TestUnsafePrescription()
    {
        boolean result = medicalService.prescribeUnsafeMedicament(
                patient,
                snickers,
                new Dose(1, 1, Dose.Period.DAY),
                "Because Mister T says!",
                new Date(),
                new Date());

		assertTrue(result);
		assertFalse(mockPatientDataSource.getLastSavedPatient()
				.getPrescriptions().isEmpty());
    }

    @Test
    public void TestUnsafePrescriptionFail()
    {
        boolean result = medicalService.prescribeUnsafeMedicament(
                patient,
                snickers,
                new Dose(1, 1, Dose.Period.DAY),
                "",
                new Date(),
                new Date());

		assertFalse(result);
		assertNull(mockPatientDataSource.getLastSavedPatient());
    }
}
