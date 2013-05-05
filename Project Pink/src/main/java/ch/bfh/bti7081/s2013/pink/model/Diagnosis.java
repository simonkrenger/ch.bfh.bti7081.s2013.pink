package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class Diagnosis {
	private String name;
	private String description;
	private List<String> symptoms = new LinkedList<String>();

	private List<Doctor> responsibleMDs = new LinkedList<Doctor>();
	private Patient patient;

	private List<Treatment> treatments = new LinkedList<Treatment>();

	private List<Note> notes = new LinkedList<Note>();
}
