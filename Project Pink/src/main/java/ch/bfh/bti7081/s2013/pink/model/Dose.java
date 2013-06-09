package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Class to represent a medication dose. The medication does consists of an
 * amount, a multiplier and a period.
 * 
 * @author chris
 * 
 */
@Entity
public class Dose implements Serializable, NoteHolder {
	private static final long serialVersionUID = 9091291159459047509L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Dosage amount
	 */
	private int amount;

	/**
	 * Definition of "How many times per period"
	 */
	private int multiplier;

	/**
	 * Period, to be used in combination with multiplier
	 */
	private Period period;

	/**
	 * Notes for this dose
	 */
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Note> notes = new LinkedList<Note>();

	/**
	 * Constructor for the class.
	 * 
	 * @param amount
	 *            Dosage (amount of pills)
	 * @param multiplier
	 *            Multiplier to define how many times per period a medication
	 *            has to be taken
	 * @param period
	 *            Period for the dosage, see enumeration Period in this class
	 */
	public Dose(int amount, int multiplier, Period period) {
		this.amount = amount;
		this.multiplier = multiplier;
		this.period = period;
	}

	@SuppressWarnings("unused")
	private Dose() {
		// needed for Hibernate
	}

	/**
	 * Returns the dosage amount (e.g. "# of pills")
	 * 
	 * @return The dosage amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Returns the dosage multiplier. The multiplier defines
	 * "how many times per period" the <code>amount</code> has to be taken.
	 * 
	 * @return How many times per period does a drug have to be taken
	 */
	public int getMultiplier() {
		return multiplier;
	}

	public void setPeriod(Period p) {
		this.period = p;
	}

	public void setAmount(int a) {
		this.amount = a;
	}

	public void setMultiplier(int m) {
		this.multiplier = m;
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

	/**
	 * Period enumeration, allowed periods for a medication.
	 * 
	 * @author chris
	 * 
	 */
	public static enum Period {
		HOUR, DAY, WEEK, MONTH, YEAR;
		public String toString() {
			return name().toLowerCase();
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Take ");
		sb.append(amount);
		if (amount == 1)
			sb.append(" unit, ");
		else
			sb.append(" units, ");
		if (multiplier == 1)
			sb.append(" once a ");
		else
			sb.append(multiplier).append(" times a ");
		sb.append(period);
		return sb.toString();
	}
}
