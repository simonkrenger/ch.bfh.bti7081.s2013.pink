package ch.bfh.bti7081.s2013.pink.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Dose {
	private int amount;
	private Date period;
	private List<Note> notes = new LinkedList<Note>();
}
