package com.jys.maze;

import com.jys.maze.client.MazeClient;

/**
 * Maze Kata Client! What does Kata mean?
 * 
 * @author Jai Sun
 *
 */
public class MazeKata {

	public static void main(String[] args) throws Exception {

		MazeClient client = new MazeClient();
		boolean gameTime = true;

		// Run maze
		while (gameTime == true) {
			gameTime = client.start();
		}

	}

}
