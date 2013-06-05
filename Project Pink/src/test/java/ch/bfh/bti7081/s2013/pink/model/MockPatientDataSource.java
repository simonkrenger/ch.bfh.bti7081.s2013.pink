package ch.bfh.bti7081.s2013.pink.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sc0238 Date: 03.06.13 Time: 18:54 To change
 * this template use File | Settings | File Templates.
 */
public class MockPatientDataSource implements IPatientDataSource {
	private Patient lastSavedPatient;

	/**
	 * Gets the last saved Patient.
	 * 
	 * @return last saved Patient.
	 */
	public Patient getLastSavedPatient() {
		return lastSavedPatient;
	}

	/**
	 * Clears the last saved patient.
	 */
	public void clearLastSavedPatient() {
		lastSavedPatient = null;
	}

	/**
	 * Gets a patient by lastName and firstName.
	 * 
	 * @param name
	 *            of the patient.
	 * @param firstName
	 *            of the patient.
	 * @return List of patients.
	 */
	@Override
	public List<Patient> findPatients(String name, String firstName) {
		return new ArrayList<Patient>();
	}

	/**
	 * Saves a patient.
	 * 
	 * @param patient
	 *            to be saved.
	 * @return flag if save was successful.
	 */
	@Override
	public boolean savePatient(Patient patient) {
		lastSavedPatient = patient;
		return true;
	}
}
