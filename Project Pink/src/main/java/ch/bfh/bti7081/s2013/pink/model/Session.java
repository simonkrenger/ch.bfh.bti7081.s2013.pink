package ch.bfh.bti7081.s2013.pink.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Session {
	private Patient patient;
	private Doctor doctors;

	private Date timeStart;
	private Date timeEnd;

	private Type type;

	private List<Note> notes = new LinkedList<Note>();

	public static enum Type {
		// TODO
	}
}
