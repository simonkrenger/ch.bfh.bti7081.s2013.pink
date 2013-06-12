package ch.bfh.bti7081.s2013.pink.model;

/**
 * DataSource for retrieving or saving patients.
 * 
 * @author Christoph Seiler (christoph.seiler@gmail.com) Date: 22.05.13 Time:
 *         19:29 To change this template use File | Settings | File Templates.
 */
public class DataBasePatientDataSource extends HibernateDataSource implements
		IPatientDataSource {
	/**
	 * Saves a patient to the database.
	 * 
	 * @param patient
	 *            to be saved.
	 * @return flag if saving was successful.
	 */
	public boolean savePatient(Patient patient) {
		return saveOrUpdate(patient).getId() != null;
	}
}
