package ch.bfh.bti7081.s2013.pink.medication;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.Context;
import ch.bfh.bti7081.s2013.pink.model.DataBasePatientDataSource;
import ch.bfh.bti7081.s2013.pink.model.Dose;
import ch.bfh.bti7081.s2013.pink.model.IPatientDataSource;
import ch.bfh.bti7081.s2013.pink.model.Medication;
import ch.bfh.bti7081.s2013.pink.model.MedicationPrescription;
import ch.bfh.bti7081.s2013.pink.model.Patient;

/**
 * Local implementation of the medical service, accessing local databases as dataSources.
 *
 * @author Christoph Seiler (Christoph Seiler)
 * Date: 22.05.13
 * Time: 18:40
 */
public class LocalMedicalService implements IMedicalService
{
    private IPatientDataSource patientDataSource;

    /**
     * Gets the medicine data source.
     *
     * @return medicine data source.
     */
    private IMedicineDataSource getMedicineDataSource()
    {
        return new DataBaseMedicineDataSource();
    }

    /**
     * Gets the dataSource for patients.
     *
     * @return patient data source.
     */
    private IPatientDataSource getPatientDataSource()
    {
        return patientDataSource;
    }

    /**
     * Creates a new instance of LocalMedicalService.
     */
    public LocalMedicalService()
    {
        patientDataSource = new DataBasePatientDataSource();
    }

    /**
     * Creates a new instance of LocalMedicalService.
     *
     * @param patientDataSource to be used.
     */
    public LocalMedicalService(IPatientDataSource patientDataSource)
    {
        this.patientDataSource = patientDataSource;
    }

    /**
     * Searches for medicines by a given text part.
     *
     * @param text in the medicament name.
     * @return List of medicines
     */
    public List<Medication> searchForMedicaments(String text)
    {
        if (text == null)
        {
            return getMedicineDataSource().getAllMedicine();
        }
        else
        {
            return getMedicineDataSource().getMedicineContainingText(text);
        }
    }

    /**
     * Prescribes a medicament to a patient
     *
     * @param patient    who will get the medicament.
     * @param medicament to be prescribed.
     * @param dose       Dose of the medication.
     * @param startDate  Start date of the prescription.
     * @param endDate    End date of the prescription.
     * @return flag if prescription has been made.
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     */
    @Override
    public boolean prescribeMedicament(Patient patient, Medication medicament, Dose dose, Date startDate, Date endDate) {
        return prescribeMedicament(patient, medicament, dose, "", startDate, endDate);
    }

    /**
     * Checks if a medicament conflicts with an allergy.
     *
     * @param medicine medicine to be tested.
     * @param allergy allergy to be tested.
     * @return flag if it conflicted.
     */
    private boolean doesMedicamentConflictWithAllergy(Medication medicine, Allergy allergy)
    {
        return medicine.getIngredients().contains(allergy.getTrigger());
    }

    /**
	 * Adds a new prescription to a Patient.
	 * 
	 * @author Christoph Seiler (christoph.seiler@gmail.com)
	 * @param patient
	 *            for whom the prescription is.
	 * @param medicine
	 *            to be prescribed.
	 * @param dose
	 *            of the medicine.
	 * @param reason
	 *            for the prescription, if given and or necessary.
     * @param startDate
     *            Start date of the prescription.
     * @param endDate
     *            End date of the prescription.
	 * @return flag if saving was successful.
	 */
    private boolean savePrescription(
            Patient patient,
            Medication medicine,
            Dose dose,
            String reason,
            Date startDate,
            Date endDate)
    {
        patient.addPrescription(
                    new MedicationPrescription(
                            reason,
                            medicine,
                            dose,
                            Context.getCurrent().getDoctor(),
                            startDate,
                            endDate));
        return getPatientDataSource().savePatient(patient);
    }

    /**
     * Prescribes a medicament to a patient
     *
     * @param patient    who will get the medicament.
     * @param medicament to be prescribed.
     * @param dose
     *            Dose of the medication.
     * @param reason
     *            justification of the prescription.
     * @param startDate
     *            Start date of the prescription.
     * @param endDate
     *            End date of the prescription.
     * @return flag if prescription has been made.
     */
    public boolean prescribeMedicament(
            Patient patient,
            Medication medicament,
            Dose dose,
            String reason,
            Date startDate,
            Date endDate)
    {
        for (Allergy allergy : patient.getAllergies())
        {
             if (doesMedicamentConflictWithAllergy(medicament, allergy))
             {
                 return false;
             }
        }

        return savePrescription(patient, medicament, dose, reason, startDate, endDate);
    }

    /**
	 * Prescribe a unsafe medicament with a reason why it is prescribed anyway.
	 * 
	 * @author Christoph Seiler (christoph.seiler@gmail.com)
	 * @param patient
	 *            patient who gets the medicament.
	 * @param medicament
	 *            medicament to be prescribed.
     * @param dose
     *            Dose of the medication.
	 * @param reason
	 *            justification of the prescription.
     * @param startDate
     *            Start date of the prescription.
     * @param endDate
     *            End date of the prescription.
	 * @return flag if prescription has been made.
	 */
    public boolean prescribeUnsafeMedicament(
            Patient patient,
            Medication medicament,
            Dose dose,
            String reason,
            Date startDate,
            Date endDate) {
        if (reason == null || reason.isEmpty())
        {
            return false;
        }

        return savePrescription(patient, medicament, dose, reason, startDate, endDate);
    }
}
