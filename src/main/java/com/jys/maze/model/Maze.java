package com.jys.maze.model;

import com.jys.maze.model.Abilities;

public class Maze {
	private Abilities abilities;
	private String startingDirection, baseUrl, controller, persona, id;
	private int xExitPosition, yStartingPosition, yExitPosition, xStartingPosition;
//	private String[][] grid;
	
//	public void printGrid() {
//		System.out.println("\nMaze Map:");
//		for (int i = 0; i < grid.length; ++i) {
//			for (int j = 0; j < grid[0].length; ++j) {
//				System.out.print(grid[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(startingDirection);
//		System.out.println();
//	}
	
//	public void updateTile(int x, int y, String val) {
//		// okay, x and y are flipped from my point of view :/
//		grid[y][x] = val;
//	}
	
//	public String getTile(int x, int y) {
//		return grid[y][x];
//	}
	
//	public String[][] getGrid() {
//		return grid;
//	}

//	public void setGrid(String[][] grid) {
//		this.grid = grid;
//	}

	public Abilities getAbilities() {
		return abilities;
	}

	public void setAbilities(Abilities abilities) {
		this.abilities = abilities;
	}

	public String getStartingDirection() {
		return startingDirection;
	}

	public void setStartingDirection(String startingDirection) {
		this.startingDirection = startingDirection;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getxExitPosition() {
		return xExitPosition;
	}

	public void setxExitPosition(int xExitPosition) {
		this.xExitPosition = xExitPosition;
	}

	public int getyStartingPosition() {
		return yStartingPosition;
	}

	public void setyStartingPosition(int yStartingPosition) {
		this.yStartingPosition = yStartingPosition;
	}

	public int getyExitPosition() {
		return yExitPosition;
	}

	public void setyExitPosition(int yExitPosition) {
		this.yExitPosition = yExitPosition;
	}

	public int getxStartingPosition() {
		return xStartingPosition;
	}

	public void setxStartingPosition(int xStartingPosition) {
		this.xStartingPosition = xStartingPosition;
	}

}
