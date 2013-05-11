package ch.bfh.bti7081.s2013.pink.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient {
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
