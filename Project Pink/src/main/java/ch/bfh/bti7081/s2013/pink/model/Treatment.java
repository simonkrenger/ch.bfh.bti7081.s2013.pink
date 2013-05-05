package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class Treatment {
	private String name;

	private Patient patient;
	private Doctor responsibleMD;
	private List<Doctor> attendingMDs = new LinkedList<Doctor>();

	private List<Diagnosis> basedOnDiagnosis = new LinkedList<Diagnosis>();

	private List<Note> notes = new LinkedList<Note>();
}
