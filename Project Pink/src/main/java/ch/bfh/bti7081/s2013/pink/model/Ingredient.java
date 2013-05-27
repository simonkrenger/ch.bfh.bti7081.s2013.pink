package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Ingredient class to represent a single ingredient of a medication. This class
 * is used in combination with allergies and medications.
 * 
 * @author chris
 * 
 */
@Entity
public class Ingredient implements Serializable {
	private static final long serialVersionUID = -8496894400951874310L;

	/**
	 * Name of the ingredient
	 */
	@Id
	private String name;

	public Ingredient(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private Ingredient() {
		// needed for Hibernate
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
