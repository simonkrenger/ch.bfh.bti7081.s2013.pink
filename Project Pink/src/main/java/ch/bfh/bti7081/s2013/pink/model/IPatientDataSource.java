package ch.bfh.bti7081.s2013.pink.model;

import java.util.List;

/**
 * Definition for a dataSource handling patients.
 * 
 * @author Christoph Seiler (christoph.seiler@gmail.com) Date: 22.05.13 Time:
 *         19:32
 */
public interface IPatientDataSource {

	/**
	 * Gets a patient by lastName and firstName.
	 * 
	 * @param name
	 *            of the patient.
	 * @param firstName
	 *            of the patient.
	 * @return List of patients.
	 */
	public List<Patient> findPatients(String name, String firstName);

	/**
	 * Saves a patient.
	 * 
	 * @param patient
	 *            to be saved.
	 * @return flag if save was successful.
	 */
	public boolean savePatient(Patient patient);

	public <T> T saveOrUpdate(T entity);
}
