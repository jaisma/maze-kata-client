package com.jys.maze.builder;

import org.json.JSONObject;

import com.jys.maze.model.Abilities;
import com.jys.maze.model.Maze;

public class MazeBuilder {

	public Maze buildMaze(String json) {

		JSONObject obj = new JSONObject(json);

		AbilitiesBuilder abilitiesBuilder = new AbilitiesBuilder();
		Abilities abilities = abilitiesBuilder.buildAbilities(obj.get("abilities").toString());

//		GridBuilder gridBuilder = new GridBuilder();
//		String[][] grid = gridBuilder.buildGrid(obj.getJSONArray("grid").toList());

		Maze maze = new Maze();
		maze.setAbilities(abilities);
//		maze.setGrid(grid);

//		maze.setxExitPosition(obj.getInt("xExitPosition"));
		maze.setxStartingPosition(obj.getInt("xStartingPosition"));
//		maze.setyExitPosition(obj.getInt("yExitPosition"));
		maze.setyStartingPosition(obj.getInt("yStartingPosition"));

		maze.setStartingDirection(obj.getString("startingDirection"));
		maze.setBaseUrl(obj.getString("baseUrl"));
		maze.setController(obj.getString("controller"));
		maze.setPersona(obj.getString("persona"));
		maze.setId(obj.getString("id"));

		return maze;
	}

}
