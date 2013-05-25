package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient implements Serializable {
	private static final long serialVersionUID = -8496894400951874310L;

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
