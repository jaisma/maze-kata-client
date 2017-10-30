package com.jys.maze.builder;

import java.util.Map;

import com.google.common.base.Splitter;
import com.jys.maze.model.Abilities;

public class AbilitiesBuilder {

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

	private Map<String, String> splitToMap(String in) {
		return Splitter.on(",").withKeyValueSeparator(":").split(in);
	}

}
