
package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class to represent a note that can be added to another entity (for example to
 * a {@link Patient} or {@link Patient}).
 * 
 * @author chris
 * 
 */
@Entity
public class Note implements Serializable {
	private static final long serialVersionUID = 8351635897658965640L;

	@Id
	@GeneratedValue
	private Long id;

	private Date timestamp;

	/**
	 * Text in the note
	 */
	private String text;

	public Note(String text) {
		this.timestamp = new Date();
		this.text = text;
	}

	@SuppressWarnings("unused")
	private Note() {
		// Default constructor, needed for Hibernate
	}

	/**
	 * Get timestamp of the note
	 * 
	 * @return Timestamp of the note
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Get the text of the note
	 * 
	 * @return The text of the note as a String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Update the text of the note
	 * 
	 */
	public void updateText(String newText) {
		this.text = newText;
	}
	@Override
	public String toString() {
		return timestamp + ": " + text;
	}
}