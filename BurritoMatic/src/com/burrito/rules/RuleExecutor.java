package com.burrito.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.burrito.items.BurritoInBowl;
import com.burrito.items.IngredientItem;
import com.burrito.items.MenuItem;
import com.burrito.items.ThreeIngredientBurrito;
import com.burrito.items.TwoIngredientBurrito;
import com.burritomatic.exception.RuleException;

public class RuleExecutor {
	// This is the max limit rule to control how much max items can be ordered
	// per order
	final static Integer MAX_ITEMS_PER_ORDER = 100;

	static interface IRule {
		boolean evaluate(MenuItem menuItem);
	};

	static abstract class Rule implements IRule {

		IngredientItem itemTypeForRuleEvaluation;
		int matchValue;

		Rule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			this.itemTypeForRuleEvaluation = itemTypeForRuleEvaluation;
			this.matchValue = matchValue;

		};

		@Override
		abstract public boolean evaluate(MenuItem menuItem);
	}

	static class IngredientCountEqualsRule extends Rule {
		IngredientCountEqualsRule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			super(itemTypeForRuleEvaluation, matchValue);
		}

		public boolean evaluate(MenuItem menuItem) {
			if (menuItem.getAllIngredientsAndCount().containsKey(itemTypeForRuleEvaluation)) {
				int value = menuItem.getAllIngredientsAndCount().get(itemTypeForRuleEvaluation);
				return value == matchValue;
			}
			return true;
		}
	}

	static class MustNotHaveIngredientRule extends Rule {
		MustNotHaveIngredientRule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			super(itemTypeForRuleEvaluation, 0);
		}

		public boolean evaluate(MenuItem menuItem) {
			if (menuItem.getAllIngredientsAndCount().containsKey(itemTypeForRuleEvaluation)) {
				int value = menuItem.getAllIngredientsAndCount().get(itemTypeForRuleEvaluation);
				return value == matchValue;
			}
			return true;
		}
	}

	static class IngredientCountLessThanRule extends Rule {
		IngredientCountLessThanRule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			super(itemTypeForRuleEvaluation, matchValue);
		}

		public boolean evaluate(MenuItem menuItem) {
			if (menuItem.getAllIngredientsAndCount().containsKey(itemTypeForRuleEvaluation)) {
				int value = menuItem.getAllIngredientsAndCount().get(itemTypeForRuleEvaluation);
				return value < matchValue;
			}
			return true;
		}
	}

	static class MaxAllIngredientsCountRule extends Rule {
		MaxAllIngredientsCountRule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			super(itemTypeForRuleEvaluation, matchValue);
		}

		public boolean evaluate(MenuItem menuItem) {
			Map<IngredientItem, Integer> ingredients = menuItem.getAllIngredientsAndCount();
			int allIngredientCount = 0;
			for (Entry<IngredientItem, Integer> entry : ingredients.entrySet()) {
				allIngredientCount += entry.getValue();
			}
			return allIngredientCount <= matchValue;
		}
	}

	static class IngredientTypeCountNotMoreThanRule extends Rule {
		IngredientTypeCountNotMoreThanRule(IngredientItem itemTypeForRuleEvaluation, int matchValue) {
			super(itemTypeForRuleEvaluation, matchValue);
		}

		public boolean evaluate(MenuItem menuItem) {
			Map<IngredientItem, Integer> ingredients = menuItem.getAllIngredientsAndCount();
			int existingIngredientType = 0;
			for (Entry<IngredientItem, Integer> entry : ingredients.entrySet()) {
				if (entry.getKey().getType().equals(itemTypeForRuleEvaluation.getType())) {
					existingIngredientType++;
				}
			}
			return existingIngredientType <= matchValue;
		}
	}

	private static final Map<MenuItem, List<IRule>> menuItemsToRules;

	static {

		menuItemsToRules = new HashMap<MenuItem, List<IRule>>();

		List<IRule> rules = new ArrayList<>();
		rules.add(new IngredientCountEqualsRule(IngredientItem.Tortilla, 0));
		rules.add(new MaxAllIngredientsCountRule(null, 3));
		rules.add(new IngredientTypeCountNotMoreThanRule(IngredientItem.Chicken, 1));

		rules.add(new IngredientCountEqualsRule(IngredientItem.GratedCheese, 0));
		rules.add(new IngredientCountEqualsRule(IngredientItem.SourCream, 0));
		rules.add(new IngredientCountEqualsRule(IngredientItem.Gucamole, 0));

		menuItemsToRules.put(new BurritoInBowl(), rules);

		rules = new ArrayList<>();
		rules.add(new MaxAllIngredientsCountRule(null, 3));
		rules.add(new IngredientTypeCountNotMoreThanRule(IngredientItem.Chicken, 1));

		rules.add(new IngredientCountEqualsRule(IngredientItem.GratedCheese, 0));
		rules.add(new IngredientCountEqualsRule(IngredientItem.SourCream, 0));
		rules.add(new IngredientCountEqualsRule(IngredientItem.Gucamole, 0));

		menuItemsToRules.put(new TwoIngredientBurrito(), rules);
		rules = new ArrayList<>();

		rules.add(new MaxAllIngredientsCountRule(null, 4));
		rules.add(new IngredientTypeCountNotMoreThanRule(IngredientItem.Chicken, 1));

		menuItemsToRules.put(new ThreeIngredientBurrito(), rules);
	}

	public static void validateRules(MenuItem menuItem) throws RuleException {
		List<IRule> rules = menuItemsToRules.get(menuItem);
		if (rules != null) {
			for (IRule rule : rules) {
				if (!rule.evaluate(menuItem)) {
					throw new RuleException("Rule evaluation failed for menu - " + menuItem.getName());
				}
			}
		}
	}

}
