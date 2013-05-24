package ch.bfh.bti7081.s2013.pink.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;

	private String name;
	private String firstName;
	private String imageUrl;

	public Person(String firstName, String name, String imageUrl) {
		this.firstName = firstName;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	protected Person() {
		// Needed for Hibernate
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String toString() {
		return firstName + " " + name;
	}
}
