package com.jys.maze.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jys.maze.model.Maze;

public class MazeBuilderTest {

	MazeBuilder testMazeBuilder;

	@Before
	public void init() {
		testMazeBuilder = new MazeBuilder();

	}

	@Test
	public void testBuildMaze() {
		String testString = "{\"xPos\":9,\"yPos\":9,\"abilities\":{\"Water\":true,\"Wood\":false,\"Fire\":false,\"Grass\":true},\"yStartingPosition\":5,\"startingDirection\":\"east\",\"baseUrl\":\"http://hiremeplease.com\",\"controller\":\"maze\",\"persona\":\"Sunny\",\"xStartingPosition\":0,\"quickFeet\":false,\"id\":\"12345\"}";
		Maze testMaze = testMazeBuilder.buildMaze(testString);
		
		assertEquals("Sunny", testMaze.getPersona());
		assertEquals(5, testMaze.getyStartingPosition());
		assertEquals("east", testMaze.getStartingDirection());
		assertEquals("http://hiremeplease.com", testMaze.getBaseUrl());
		assertEquals("12345", testMaze.getId());
		assertEquals(9, testMaze.getxPos());
		assertEquals(9, testMaze.getyPos());

	}

}
