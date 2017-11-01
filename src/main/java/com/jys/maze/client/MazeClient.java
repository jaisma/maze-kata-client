package com.jys.maze.client;

import java.util.Scanner;

import com.jys.maze.builder.MazeBuilder;
import com.jys.maze.connector.IRConnector;
import com.jys.maze.model.Maze;
import com.jys.maze.solver.AutoSolver;

/**
 * Client to run the game and interact with the player
 * 
 * @author Jai Sun
 *
 */
public class MazeClient {

	private Maze maze;
	private IRConnector connector;

	public MazeClient() {
		connector = new IRConnector();
	}

	public boolean start() {

		if (!welcome()) {
			return false;
		}

		MazeBuilder mb = new MazeBuilder();
		maze = mb.buildMaze(connector.start()); // start

		System.out.println(
				"As a " + maze.getPersona() + " you have the following abilities: " + maze.getAbilities().toString());

		if (maze.getPersona().equals("Human")) {
			System.out.println("auto solve? type y : n");
			String answer = takeInput();
			if (answer.equals("y"))
				autoSolveHuman();
		} else if (maze.getPersona().equals("Superman")) {
			System.out.println("auto solve? type y : n");
			String answer = takeInput();
			if (answer.equals("y"))
				autoSolveSuperman();
		} else if (maze.getPersona().equals("Aquaman")) {
			System.out.println("auto solve? type y : n");
			String answer = takeInput();
			if (answer.equals("y"))
				autoSolveAquaman();
		}

		run(maze);

		System.out.println("Do you want to play again? type y : n");
		String replay = takeInput();
		return replay.equals("y") ? true : false;
	}

	public void run(Maze maze) {
		maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "START");
		maze.printGrid();

		while (true) {
			if (maze.getxStartingPosition() == maze.getxExitPosition()
					&& maze.getyStartingPosition() == maze.getyExitPosition()) {
				System.out.println("Congrats, you made it!");
				break;
			}

			System.out.println("Your next move? type turn : advance : check");
			String userInput = takeInput();
			// turn, advance, check allowed, start new maze

			if (userInput.equals("turn")) {
				System.out.println("left or right? type l : r");
				String direction = takeInput();
				System.out.println("\nCurrent Score: " + turn(direction));
			} else if (userInput.equals("advance")) {
				if (maze.getAbilities().isQuickFeet()) {
					System.out.println("Quick Feet advance or Normal advance? type q : n");
					boolean decision = takeInput().equals("q") ? true : false;
					System.out.println("\nCurrent Score: " + advance(decision));
				} else {
					System.out.println("\nCurrent Score: " + advance(false));
				}
			} else if (userInput.equals("check")) {
				// if advance is possible
				boolean decision = false;
				if (maze.getAbilities().isQuickFeet()) {
					System.out.println("Quick Feet advance or Normal advance? type q : n");
					decision = takeInput().equals("q") ? true : false;
				}
				boolean checked = decision ? checkQF() : check();
				if (checked) {
					System.out.println("**Valide movement**");
				} else {
					System.out.println("**Forward advance blocked. Try turning**");
				}
			} else {
				System.out.println("invalid input");
			}

			maze.printGrid();

		}

	}

	public String advance(boolean quickFeet) {
		String response = "";
		if (quickFeet) {
			if (checkQF()) {
				response = connector.advance(maze.getId(), true);
				if (maze.getStartingDirection().equals("north")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "      ");
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition() - 1, "     ");
					maze.setyStartingPosition(maze.getyStartingPosition() - 2);
				} else if (maze.getStartingDirection().equals("south")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition() + 1, "     ");
					maze.setyStartingPosition(maze.getyStartingPosition() + 2);
				} else if (maze.getStartingDirection().equals("west")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.updateTile(maze.getxStartingPosition() - 1, maze.getyStartingPosition(), "     ");
					maze.setxStartingPosition(maze.getxStartingPosition() - 2);
				} else if (maze.getStartingDirection().equals("east")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.updateTile(maze.getxStartingPosition() + 1, maze.getyStartingPosition(), "     ");
					maze.setxStartingPosition(maze.getxStartingPosition() + 2);
				}
			}
		} else {
			if (check()) {
				response = connector.advance(maze.getId(), false);
				if (maze.getStartingDirection().equals("north")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.setyStartingPosition(maze.getyStartingPosition() - 1);
				} else if (maze.getStartingDirection().equals("south")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.setyStartingPosition(maze.getyStartingPosition() + 1);
				} else if (maze.getStartingDirection().equals("west")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.setxStartingPosition(maze.getxStartingPosition() - 1);
				} else if (maze.getStartingDirection().equals("east")) {
					maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), "     ");
					maze.setxStartingPosition(maze.getxStartingPosition() + 1);
				}
			}
		}

		maze.updateTile(maze.getxStartingPosition(), maze.getyStartingPosition(), " HERE");

		return response;

	}

	public boolean checkQF() {
		String response = "";
		if (maze.getStartingDirection().equals("north")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition(), maze.getyStartingPosition() - 2);
		} else if (maze.getStartingDirection().equals("south")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition(), maze.getyStartingPosition() + 2);
		} else if (maze.getStartingDirection().equals("west")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition() - 2, maze.getyStartingPosition());
		} else if (maze.getStartingDirection().equals("east")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition() + 2, maze.getyStartingPosition());
		}

		if (response.contains("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean check() {
		String response = "";
		if (maze.getStartingDirection().equals("north")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition(), maze.getyStartingPosition() - 1);
		} else if (maze.getStartingDirection().equals("south")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition(), maze.getyStartingPosition() + 1);
		} else if (maze.getStartingDirection().equals("west")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition() - 1, maze.getyStartingPosition());
		} else if (maze.getStartingDirection().equals("east")) {
			response = connector.check(maze.getId(), maze.getxStartingPosition() + 1, maze.getyStartingPosition());
		}

		if (response.contains("true")) {
			return true;
		} else {
			return false;
		}
	}

	public String turn(String direction) {
		String response = connector.turn(maze.getId(), direction);
		String currentDirection = maze.getStartingDirection();
		String newDirection = "";
		if (currentDirection.equals("north")) {
			newDirection = direction.equals("l") ? "west" : "east";
		} else if (currentDirection.equals("south")) {
			newDirection = direction.equals("l") ? "east" : "west";
		} else if (currentDirection.equals("west")) {
			newDirection = direction.equals("l") ? "south" : "north";
		} else if (currentDirection.equals("east")) {
			newDirection = direction.equals("l") ? "north" : "south";
		}
		maze.setStartingDirection(newDirection);

		return response;
	}

	public String takeInput() {
		Scanner in = new Scanner(System.in);
		String temp = in.nextLine().toLowerCase().trim();
		return temp;
	}

	public boolean welcome() {

		// Welcome message
		System.out.println("Welcome stranger, do you want to be a Superhero?");
		System.out.println("Type \"Y\" to start the 'Maze Challenge', type \"N\" to exit");

		// User input
		String input = takeInput();
		if (input.toLowerCase().equals("y")) {
			System.out.println("Careful what you wish for, Hero");
			return true;
		}

		return false;
	}

	public void autoSolveHuman() {
		AutoSolver as = new AutoSolver();
		MazeBuilder mb = new MazeBuilder();
		maze = mb.buildMaze(connector.start());
		char[][] path = as.getHumanPath(maze);
		as.autoSolve(path, maze);
	}

	public void autoSolveSuperman() {
		AutoSolver as = new AutoSolver();
		MazeBuilder mb = new MazeBuilder();
		maze = mb.buildMaze(connector.start());
		char[][] path = as.getSupermanPath(maze);
		as.autoSolve(path, maze);
	}

	public void autoSolveAquaman() {
		AutoSolver as = new AutoSolver();
		MazeBuilder mb = new MazeBuilder();
		maze = mb.buildMaze(connector.start());
		char[][] path = as.getAquaPath(maze);
		as.autoSolve(path, maze);
	}

	public void autoSolveFlash() {

	}

}
