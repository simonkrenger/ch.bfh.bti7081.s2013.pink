package ch.bfh.bti7081.s2013.pink.medication;

import ch.bfh.bti7081.s2013.pink.model.Allergy;
import ch.bfh.bti7081.s2013.pink.model.Dose;
import ch.bfh.bti7081.s2013.pink.model.Medication;
import ch.bfh.bti7081.s2013.pink.model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Medical Service to handle the medicine prescription and suggestions.
 *
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 */
public interface IMedicalService
{
    /**
     * Searches for medicines by a given text part.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @param text in the medicament name.
     * @return List of medicines
     */
    List<Medication> searchForMedicaments(String text);

    /**
     * Prescribes a medicament to a patient
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @param patient    who will get the medicament.
     * @param medicament to be prescribed.
     * @return flag if prescription has been made.
     */
    public boolean prescribeMedicament(Patient patient, Medication medicament, Dose dose);

    /**
     * Prescribe a unsafe medicament with a reason why it has prescribed anyway.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @param patient    patient who gets the medicament.
     * @param medicament medicament to be prescribed.
     * @param reason     justification of the prescription.
     * @return flag if prescription has been made.
     */
    public boolean prescribeUnsafeMedicament(Patient patient, Medication medicament, Dose dose, String reason);
}
