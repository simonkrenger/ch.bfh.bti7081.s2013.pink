package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Warning {
	@Id
	@GeneratedValue
	private Long id;

	private String text;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Note> updates = new LinkedList<Note>();

	public Warning(String text) {
		this.text = text;
	}

	@SuppressWarnings("unused")
	private Warning() {
		// needed for Hibernate
	}

	public String getText() {
		return text;
	}

	public void addUpdate(Note text) {
		updates.add(text);
	}

	public List<Note> getUpdates() {
		return updates;
	}
}
