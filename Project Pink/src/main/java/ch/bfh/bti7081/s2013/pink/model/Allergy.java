package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Allergy {
	@Id
	@GeneratedValue
	private Long id;

	private Ingredient trigger;
	private Severity severity;
	private List<Note> notes = new LinkedList<Note>();

	public static enum Severity {

	}
}
