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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
