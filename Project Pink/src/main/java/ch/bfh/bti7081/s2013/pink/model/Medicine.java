package ch.bfh.bti7081.s2013.pink.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/**
 * Class to represent a medication
 * 
 * @author chris
 * 
 */
@Entity
public class Medicine implements Serializable {
	private static final long serialVersionUID = 8422980973382356404L;

	@Id
	private String name;

	@ManyToMany
	private List<Ingredient> ingredients = new LinkedList<Ingredient>();

	@ElementCollection
	@CollectionTable(name = "MedicineUses", joinColumns = @JoinColumn(name = "name"))
	@Column(name = "use")
	private List<String> uses = new LinkedList<String>();

	@ElementCollection
	@CollectionTable(name = "MedicineEffects", joinColumns = @JoinColumn(name = "name"))
	@Column(name = "effect")
	private List<String> effects = new LinkedList<String>();

	@ElementCollection
	@CollectionTable(name = "MedicineSideeffects", joinColumns = @JoinColumn(name = "name"))
	@Column(name = "sideeffect")
	private List<String> sideeffects = new LinkedList<String>();

	public Medicine(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private Medicine() {
		// needed for Hibernate
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public List<String> getUses() {
		return uses;
	}

	public void addUse(String use) {
		uses.add(use);
	}

	public List<String> getEffects() {
		return effects;
	}

	public void addEffect(String effect) {
		effects.add(effect);
	}

	public List<String> getSideeffects() {
		return sideeffects;
	}

	public void addSideeffect(String sideeffect) {
		sideeffects.add(sideeffect);
	}

	@Override
	public String toString() {
		return name + ": used for " + uses + ". Helps with "
				+ effects.toString() + " but might cause " + sideeffects
				+ ". Contains " + ingredients;
	}
}
