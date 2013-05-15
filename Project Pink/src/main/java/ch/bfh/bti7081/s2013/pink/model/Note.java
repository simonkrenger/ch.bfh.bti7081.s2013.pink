package ch.bfh.bti7081.s2013.pink.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {
	@Id
	@GeneratedValue
	private Long id;

	private Date timestamp;
	private String text;

	public Note(String text) {
		this.timestamp = new Date();
		this.text = text;
	}

	@SuppressWarnings("unused")
	private Note() {
		// Default constructor, needed for Hibernate
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return timestamp + ": " + text;
	}
}
