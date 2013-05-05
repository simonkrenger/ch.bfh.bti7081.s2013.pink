package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class MedicationPrescription {
	private String reason;
	private Medicine medicine;
	private Dose dose;
	private Doctor prescriber;
	private List<Note> notes = new LinkedList<Note>();
}
