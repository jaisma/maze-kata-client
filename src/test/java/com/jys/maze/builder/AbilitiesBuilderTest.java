package com.jys.maze.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jys.maze.model.Abilities;

public class AbilitiesBuilderTest {

	AbilitiesBuilder testAbilitiesBuilder;

	@Before
	public void init() {
		testAbilitiesBuilder = new AbilitiesBuilder();
	}

	@Test
	public void testBuildAbilities() {
		String testRawStringI = "{\"Water\":true,\"Wood\":false,\"Fire\":false,\"Grass\":true}";
		String testRawStringII = "{\"Water\":true,\"Wood\":true,\"Fire\":true,\"Grass\":true}";
		String testRawStringIII = "{\"Water\":false,\"Wood\":false,\"Fire\":false,\"Grass\":true,\"quick feet\":true}";
		String testRawStringIV = "{\"Wate@#r\":f%&#alse,\"@#Wood\":false,\"Fire@#\":false,\"@#Grass\":true,\"quick feet\":true}";

		Abilities testAbilityI = testAbilitiesBuilder.buildAbilities(testRawStringI);
		Abilities testAbilityII = testAbilitiesBuilder.buildAbilities(testRawStringII);
		Abilities testAbilityIII = testAbilitiesBuilder.buildAbilities(testRawStringIII);
		Abilities testAbilityIV = testAbilitiesBuilder.buildAbilities(testRawStringIV);

		assertEquals("water grass", testAbilityI.toString());
		assertEquals("water wood fire grass", testAbilityII.toString());
		assertEquals("grass quickFeet", testAbilityIII.toString());
		assertEquals("grass quickFeet", testAbilityIV.toString());

	}

}
