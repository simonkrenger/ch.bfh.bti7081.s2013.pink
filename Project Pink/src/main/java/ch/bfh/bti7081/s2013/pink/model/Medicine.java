package ch.bfh.bti7081.s2013.pink.model;

import java.util.LinkedList;
import java.util.List;

public class Medicine {
	private String name;
	private List<Ingredient> ingredients = new LinkedList<Ingredient>();
	private List<String> effects;
	private List<String> uses;
	private List<String> sideeffects;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
}
