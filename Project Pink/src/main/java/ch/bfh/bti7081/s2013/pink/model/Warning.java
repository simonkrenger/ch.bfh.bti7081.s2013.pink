package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class Warning {
	private String text;
	private List<Note> updates = new LinkedList<Note>();

	public void addUpdate(Note text) {
		updates.add(text);
	}
}
