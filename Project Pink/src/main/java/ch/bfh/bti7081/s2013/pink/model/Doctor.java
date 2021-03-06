package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

/**
 * Class to represent a doctor. A doctor has a list of specializations. Note
 * that as a special "feature", our application assumes all doctors have the
 * title "Dr. med.". This will have to be fixed in future releases.
 * 
 * @author chris
 * 
 */
@Entity
public class Doctor extends Person {
	private static final long serialVersionUID = -1992284814080337920L;

	@ElementCollection
	@CollectionTable(name = "Specializations", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "specialization")
	private List<String> specializations = new LinkedList<String>();

	/**
	 * Doctors title
	 */
	private String title = "Dr. med.";

	/**
	 * Constructor for the class
	 * 
	 * @param firstName
	 *            First name of the doctor
	 * @param name
	 *            Last name of the doctor
	 * @param imageUrl
	 *            URL to an image of the doctor
	 */
	public Doctor(String firstName, String name, String imageUrl) {
		super(firstName, name, imageUrl);
	}

	protected Doctor() {
		// needed for Hibernate
	}

	/**
	 * Adds a specialization to the doctor
	 * 
	 * @param specialization
	 *            The specialization the be added
	 */
	public void addSpecialization(String specialization) {
		specializations.add(specialization);
	}

	/**
	 * Gets a list of specializations of this doctor
	 * 
	 * @return A list of specializations as Strings
	 */
	public List<String> getSpecializations() {
		return specializations;
	}

	/**
	 * Returns the name of the doctor with the number of his / her
	 * specializations. Note that all doctors have the title "Dr. med." by
	 * default. This is to be fixed in a future release.
	 */
	@Override
	public String toString() {
		return title + " " + super.toString() + ": " + specializations.size();
	}
}
