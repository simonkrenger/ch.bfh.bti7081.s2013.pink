package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Dose {
	@Id
	@GeneratedValue
	private Long id;

	private int amount;
	private int multiplier;
	private Period period;
	@OneToMany
	private List<Note> notes = new LinkedList<Note>();

	public Dose(int amout, int multiplier, Period period) {
		this.amount = amout;
		this.multiplier = multiplier;
		this.period = period;
	}

	@SuppressWarnings("unused")
	private Dose() {
		// needed for Hibernate
	}

	public int getAmount() {
		return amount;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public Period getPeriod() {
		return period;
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public List<Note> getNotes() {
		return notes;
	}

	public static enum Period {
		HOUR, DAY, WEEK, MONTH, YEAR;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Take ");
		sb.append(amount).append(" units, ");
		if (multiplier == 1)
			sb.append(" once a ");
		else
			sb.append(multiplier).append(" times a ");
		sb.append(period);
		return sb.toString();
	}
}
