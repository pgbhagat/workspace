package com.burrito.items;

import java.util.HashMap;
import java.util.Map;

import com.burrito.rules.RuleExecutor;
import com.burritomatic.exception.RuleException;

public abstract class MenuItem implements IItem {

	private Map<IngredientItem, Integer> ingredientsToCount;

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof MenuItem) {
			return ((MenuItem) other).getName().equals(this.getName());
		}
		return false;
	}

	MenuItem(String name) {
		this.name = name;
		ingredientsToCount = new HashMap<IngredientItem, Integer>();
	}

	public void setIngredientsToCount(Map<IngredientItem, Integer> ingredientsToCount) {
		this.ingredientsToCount = ingredientsToCount;
	}

	public Map<IngredientItem, Integer> getAllIngredientsAndCount() {
		return ingredientsToCount;
	}

	public void addIngredientsToMenuItem(IngredientItem ingredient, int count) throws RuleException {
		ingredientsToCount.put(ingredient, count);
		try {
			RuleExecutor.validateRules(this);
		} catch (RuleException e) {
			removeIngredientFromMenuItem(ingredient);
			throw e;
		}
	}

	public void removeIngredientFromMenuItem(IngredientItem ingredient) {
		ingredientsToCount.remove(ingredient);
	}

}
