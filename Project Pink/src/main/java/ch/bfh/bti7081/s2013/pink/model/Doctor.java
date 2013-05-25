package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class Doctor extends Person {
	private static final long serialVersionUID = -1992284814080337920L;

	@ElementCollection
	@CollectionTable(name = "Specializations", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "specialization")
	private List<String> specializations = new LinkedList<String>();

	public Doctor(String firstName, String name, String imageUrl) {
		super(firstName, name, imageUrl);
	}

	protected Doctor() {
		// needed for Hibernate
	}

	public void addSpecialization(String specialization) {
		specializations.add(specialization);
	}

	public List<String> getSpecializations() {
		return specializations;
	}

	@Override
	public String toString() {
		return "Dr. med. " + super.toString() + ": " + specializations.size();
	}
}
