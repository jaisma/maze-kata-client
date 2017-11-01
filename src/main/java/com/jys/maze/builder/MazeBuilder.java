package com.jys.maze.builder;

import org.json.JSONObject;

import com.jys.maze.model.Abilities;
import com.jys.maze.model.Maze;

public class MazeBuilder {

	public Maze buildMaze(String json) {

		JSONObject obj = new JSONObject(json);

		AbilitiesBuilder abilitiesBuilder = new AbilitiesBuilder();
		Abilities abilities = abilitiesBuilder.buildAbilities(obj.get("abilities").toString());
		
		Maze maze = new Maze();
		maze.setAbilities(abilities);
		maze.setyStartingPosition(obj.getInt("yStartingPosition"));
		maze.setStartingDirection(obj.getString("startingDirection"));
		maze.setBaseUrl(obj.getString("baseUrl"));
		maze.setController(obj.getString("controller"));
		maze.setPersona(obj.getString("persona"));
		maze.setxStartingPosition(obj.getInt("xStartingPosition"));
		maze.setxPos(obj.getInt("xPos"));
		maze.setyPos(obj.getInt("yPos"));
		maze.setId(obj.getString("id"));

		return maze;
		
// NEW:
//		xPos - seems like its the same as xStartingPosition
//		yPos - seems like its the same as yStartingPosition
//		isAtExit - replacing exit positions i think
		
// DEPRECATED: no more grid
//		GridBuilder gridBuilder = new GridBuilder();
//		String[][] grid = gridBuilder.buildGrid(obj.getJSONArray("grid").toList());
//		maze.setGrid(grid);

// DEPRECATED: no more exit positions
//		maze.setxExitPosition(obj.getInt("xExitPosition"));
//		maze.setyExitPosition(obj.getInt("yExitPosition"));
	}

}
