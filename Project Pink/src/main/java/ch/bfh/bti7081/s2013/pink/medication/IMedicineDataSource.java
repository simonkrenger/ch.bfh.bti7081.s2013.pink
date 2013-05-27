package ch.bfh.bti7081.s2013.pink.medication;

import ch.bfh.bti7081.s2013.pink.model.Medication;

import java.util.List;

/**
 * Definition of a DataSource for retrieving Medicine.
 *
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 * Date: 22.05.13
 * Time: 18:48
 */
public interface IMedicineDataSource {

    /**
     * Get all medicine in database.
     *
     * @return complete list of all medicine.
     */
    List<Medication> GetAllMedicine();

    /**
     * Gets all medicine with a specific text in the name.
     *
     * @param text which has to be present in the name.
     * @return List of medicine matching the text.
     */
    List<Medication> GetMedicineContainingText(String text);
}
