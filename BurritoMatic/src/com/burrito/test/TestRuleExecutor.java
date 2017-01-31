package com.burrito.test;

import org.junit.Assert;
import org.junit.Test;

import com.burrito.items.BurritoInBowl;
import com.burrito.items.IngredientItem;
import com.burrito.items.MenuItem;
import com.burrito.items.ThreeIngredientBurrito;
import com.burrito.items.TwoIngredientBurrito;
import com.burritomatic.exception.RuleException;

import junit.framework.TestCase;

public class TestRuleExecutor extends TestCase {

	/**
	 * Test rule BurritoInBown can not have tortilla
	 * 
	 */
	@Test
	public void testValidateRuleTortilla() {
		MenuItem burritoInBowl = new BurritoInBowl();
		boolean failure = false;
		try {
			burritoInBowl.addIngredientsToMenuItem(IngredientItem.Tortilla, 1);
		} catch (RuleException e) {
			System.out.println(e.getMessage());
			failure = true;
		}
		if (!failure) {
			Assert.fail("burritoInBowl should not be allowed with Tortilla");
		}
	}

	/**
	 * Test rule that all products can have only ONE meat
	 * 
	 */

	@Test
	public void testValidateOnlyOneMeatRule() {
		MenuItem twoIngredientBurrito = new TwoIngredientBurrito();
		boolean failure = false;
		try {
			twoIngredientBurrito.addIngredientsToMenuItem(IngredientItem.Chicken, 1);
			twoIngredientBurrito.addIngredientsToMenuItem(IngredientItem.Steak, 1);
		} catch (RuleException e) {
			System.out.println(e.getMessage());
			failure = true;
		}
		if (!failure) {
			Assert.fail("Any Burrito can have only one meat type ingredient");
		}
	}

	/**
	 * Test rule for three ingredient burrito
	 * 
	 */

	@Test
	public void testValidateThreeRule() {
		MenuItem threeIngredientBurrito = new ThreeIngredientBurrito();
		boolean failure = false;
		try {
			threeIngredientBurrito.addIngredientsToMenuItem(IngredientItem.Chicken, 1);
			threeIngredientBurrito.addIngredientsToMenuItem(IngredientItem.GratedCheese, 1);
			threeIngredientBurrito.addIngredientsToMenuItem(IngredientItem.RedSalsa, 1);
		} catch (RuleException e) {
			System.out.println(e.getMessage());
		}
		try {
			threeIngredientBurrito.addIngredientsToMenuItem(IngredientItem.Queso, 3);
		} catch (RuleException e) {
			System.out.println(e.getMessage());
			failure = true;
		}
		if (!failure) {
			Assert.fail("three ingredient burrito can have max three ingredients");
		}
	}
}
