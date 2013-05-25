package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Class to represent a person with a last name and a first name.
 * 
 * @author chris
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {
	private static final long serialVersionUID = -6921283421758979820L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;

	/**
	 * Last name of the person
	 */
	private String name;

	/**
	 * First name of the person
	 */
	private String firstName;

	/**
	 * Constructor for the class.
	 * 
	 * @param firstName
	 *            First name of the person
	 * @param name
	 *            Last name of the person
	 */
	public Person(String firstName, String name) {
		this.firstName = firstName;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	protected Person() {
		// Needed for Hibernate
	}

	/**
	 * Gets the last name of the person
	 * 
	 * @return The last name of the person as a String
	 */
	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return firstName + " " + name;
	}
}
