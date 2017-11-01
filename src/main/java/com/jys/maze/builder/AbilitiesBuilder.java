package com.jys.maze.builder;

import java.util.Map;

import com.google.common.base.Splitter;
import com.jys.maze.model.Abilities;

/**
 * Abilities builder.
 *
 * @author Jai Sun
 *
 */
public class AbilitiesBuilder {

	/**
	 * Takes in string representation of abilities parsed from json response. Using
	 * regex to remove all special characters except for ":" character. Expecting
	 * map of string:boolean format. Using helper method to create map.
	 * 
	 * Assume valid input.
	 * 
	 * @param abilities
	 *            string representation of abilities extracted from json response
	 * @return Abilities object
	 */
	public Abilities buildAbilities(String abilities) {

		abilities = abilities.toLowerCase().replaceAll("[$&}{\"+;=?@#|'<>.^*()%!-]", "");

		Map<String, String> abilitiesMap = splitToMap(abilities);

		Abilities result = new Abilities();
		abilitiesMap.forEach((k, v) -> {
			if (k.equals("water")) {
				result.setWater(Boolean.parseBoolean(v));
			} else if (k.equals("wood")) {
				result.setWood(Boolean.parseBoolean(v));
			} else if (k.equals("fire")) {
				result.setFire(Boolean.parseBoolean(v));
			} else if (k.equals("grass")) {
				result.setGrass(Boolean.parseBoolean(v));
			} else if (k.equals("quick feet")) {
				result.setQuickFeet(Boolean.parseBoolean(v));
			}
		});

		return result;
	}

	/**
	 * Creates map from raw string using "," as individual input and ":" as
	 * key-value separator
	 * 
	 * @param in
	 *            raw string
	 * @return map of string:string
	 */
	private Map<String, String> splitToMap(String in) {
		return Splitter.on(",").withKeyValueSeparator(":").split(in);
	}

}
