package com.jys.maze.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AbilitiesTest {

	@Test
	public void testHasAbility() {
		Abilities bruceLee = new Abilities(true, true, true, true, true);

		assertTrue(bruceLee.hasAbility("water"));
		assertTrue(bruceLee.hasAbility("fire"));
		assertTrue(bruceLee.hasAbility("wood"));
		assertTrue(bruceLee.hasAbility("grass"));
		assertTrue(bruceLee.hasAbility("quickfeet"));
		assertTrue(!bruceLee.hasAbility("dragon"));
		assertTrue(bruceLee.hasAbility("WaTeR"));
	}

	@Test
	public void testToString() {
		Abilities bruceLee = new Abilities(true, true, true, true, true);

		String result = bruceLee.toString();
		assertEquals("water wood fire grass quickFeet", result);

	}

}
