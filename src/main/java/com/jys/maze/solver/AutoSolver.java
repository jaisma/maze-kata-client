package com.jys.maze.solver;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import com.jys.maze.connector.IRConnector;
import com.jys.maze.model.Maze;

/**
 * Credit: https://github.com/coderodde
 *
 */
public class AutoSolver {

	private AutoMaze maze;
	private Point source;
	private Point target;
	private boolean[][] visited;
	private Map<Point, Point> parents;

	public AutoSolver() {
	}

	private AutoSolver(final AutoMaze maze, final Point source, final Point target) {
		Objects.requireNonNull(maze, "The input maze is null.");
		Objects.requireNonNull(source, "The source node is null.");
		Objects.requireNonNull(target, "The target node is null.");

		this.maze = maze;
		this.source = source;
		this.target = target;

		checkSourceNode();
		checkTargetNode();

		this.visited = new boolean[maze.getHeight()][maze.getWidth()];
		this.parents = new HashMap<>();
		this.parents.put(source, null);
	}

	public List<Point> findPath(final AutoMaze maze, final Point source, final Point target) {
		return new AutoSolver(maze, source, target).compute();
	}

	private List<Point> compute() {
		final Queue<Point> queue = new ArrayDeque<>();
		final Map<Point, Integer> distances = new HashMap<>();

		queue.add(source);
		distances.put(source, 0);

		while (!queue.isEmpty()) {
			// Removes the head of the queue.
			final Point current = queue.remove();

			if (current.equals(target)) {
				return constructPath();
			}

			for (final Point child : generateChildren(current)) {
				if (!parents.containsKey(child)) {
					parents.put(child, current);
					// Appends 'child' to the end of this queue.
					queue.add(child);
				}
			}
		}

		// null means that the target node is not reachable
		// from the source node.
		return null;
	}

	private List<Point> constructPath() {
		Point current = target;
		final List<Point> path = new ArrayList<>();

		while (current != null) {
			path.add(current);
			current = parents.get(current);
		}

		Collections.<Point>reverse(path);
		return path;
	}

	private Iterable<Point> generateChildren(final Point current) {
		final Point north = new Point(current.x, current.y - 1);
		final Point south = new Point(current.x, current.y + 1);
		final Point west = new Point(current.x - 1, current.y);
		final Point east = new Point(current.x + 1, current.y);

		final List<Point> childList = new ArrayList<>(4);

		if (maze.cellIsTraversible(north)) {
			childList.add(north);
		}

		if (maze.cellIsTraversible(south)) {
			childList.add(south);
		}

		if (maze.cellIsTraversible(west)) {
			childList.add(west);
		}

		if (maze.cellIsTraversible(east)) {
			childList.add(east);
		}

		return childList;
	}

	private void checkSourceNode() {
		checkNode(source, "The source node (" + source + ") is outside the maze. " + "The width of the maze is "
				+ maze.getWidth() + " and " + "the height of the maze is " + maze.getHeight() + ".");

		if (!maze.cellIsFree(source.x, source.y)) {
			throw new IllegalArgumentException("The source node (" + source + ") is at a occupied cell.");
		}
	}

	private void checkTargetNode() {
		checkNode(target, "The target node (" + target + ") is outside the maze. " + "The width of the maze is "
				+ maze.getWidth() + " and " + "the height of the maze is " + maze.getHeight() + ".");

		if (!maze.cellIsFree(target.x, target.y)) {
			throw new IllegalArgumentException("The target node (" + target + ") is at a occupied cell.");
		}
	}

	private void checkNode(final Point node, final String errorMessage) {
		if (node.x < 0 || node.x >= maze.getWidth() || node.y < 0 || node.y >= maze.getHeight()) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public char[][] getHumanPath(Maze inputMaze) {
		int startX = inputMaze.getxStartingPosition();
		int startY = inputMaze.getyStartingPosition();

		int finishX = inputMaze.getxExitPosition();
		int finishY = inputMaze.getyExitPosition();

		String[][] grid = inputMaze.getGrid();

		int[][] mazePlan = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				mazePlan[i][j] = grid[i][j].equals("grass") ? 0 : 1;
			}
		}

		mazePlan[finishY][finishX] = 0;

		boolean[][] maze2 = new boolean[mazePlan.length][mazePlan[0].length];

		for (int i = 0; i < maze2.length; ++i) {
			for (int j = 0; j < maze2[i].length; ++j) {
				maze2[i][j] = mazePlan[i][j] > 0;
			}
		}

		final AutoMaze maze = new AutoMaze(maze2);
		final Point source = new Point(startX, startY); // Same as new Point(0, 0):
		final Point target = new Point(finishX, finishY);

		final List<Point> path = new AutoSolver().findPath(maze, source, target);

		System.out.println("Shortest path length: " + (path.size() - 1));
		System.out.println(maze.withPath(path));

		return maze.getMatrix(path);
	}

	public char[][] getAquaPath(Maze inputMaze) {
		int startX = inputMaze.getxStartingPosition();
		int startY = inputMaze.getyStartingPosition();

		int finishX = inputMaze.getxExitPosition();
		int finishY = inputMaze.getyExitPosition();

		String[][] grid = inputMaze.getGrid();

		int[][] mazePlan = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				mazePlan[i][j] = grid[i][j].equals("grass") || grid[i][j].equals("water") ? 0 : 1;
			}
		}

		mazePlan[finishY][finishX] = 0;

		boolean[][] maze2 = new boolean[mazePlan.length][mazePlan[0].length];

		for (int i = 0; i < maze2.length; ++i) {
			for (int j = 0; j < maze2[i].length; ++j) {
				maze2[i][j] = mazePlan[i][j] > 0;
			}
		}

		final AutoMaze maze = new AutoMaze(maze2);
		final Point source = new Point(startX, startY); // Same as new Point(0, 0):
		final Point target = new Point(finishX, finishY);

		final List<Point> path = new AutoSolver().findPath(maze, source, target);

		System.out.println("Shortest path length: " + (path.size() - 1));
		System.out.println(maze.withPath(path));

		return maze.getMatrix(path);
	}

	public char[][] getSupermanPath(Maze inputMaze) {
		int startX = inputMaze.getxStartingPosition();
		int startY = inputMaze.getyStartingPosition();

		int finishX = inputMaze.getxExitPosition();
		int finishY = inputMaze.getyExitPosition();

		String[][] grid = inputMaze.getGrid();

		int[][] mazePlan = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				mazePlan[i][j] = 0;
			}
		}

		mazePlan[finishY][finishX] = 0;

		boolean[][] maze2 = new boolean[mazePlan.length][mazePlan[0].length];

		for (int i = 0; i < maze2.length; ++i) {
			for (int j = 0; j < maze2[i].length; ++j) {
				maze2[i][j] = mazePlan[i][j] > 0;
			}
		}

		final AutoMaze maze = new AutoMaze(maze2);
		final Point source = new Point(startX, startY); // Same as new Point(0, 0):
		final Point target = new Point(finishX, finishY);

		final List<Point> path = new AutoSolver().findPath(maze, source, target);

		System.out.println("Shortest path length: " + (path.size() - 1));
		System.out.println(maze.withPath(path));

		return maze.getMatrix(path);
	}

	public void autoSolve(char[][] path, Maze maze) {
		IRConnector connector = new IRConnector();

		int x = maze.getxStartingPosition();
		int y = maze.getyStartingPosition();
		String dir = maze.getStartingDirection();

		int fx = maze.getxExitPosition();
		int fy = maze.getyExitPosition();
		System.out.println("Finish point: " + fx + " " + fy);

		int px = 0;
		int py = 0;
		int next = -1;

		while (x != fx || y != fy) {
			px = x;
			py = y;
			x = maze.getxStartingPosition();
			y = maze.getyStartingPosition();
			next = findNextStepchar(path, x, y, px, py);
			if (next == 1) {
				// need to go up
				if (dir.equals("east")) {
					System.out.println(connector.turn(maze.getId(), "left"));
					maze.setStartingDirection("north");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setyStartingPosition(y - 1);
				} else {
					System.out.println(connector.turn(maze.getId(), "right"));
					maze.setStartingDirection("north");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setyStartingPosition(y - 1);
				}
			} else if (next == 2) {
				// need to go right
				if (dir.equals("north")) {
					System.out.println(connector.turn(maze.getId(), "right"));
					maze.setStartingDirection("east");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setxStartingPosition(x + 1);
				} else {
					System.out.println(connector.turn(maze.getId(), "left"));
					maze.setStartingDirection("east");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setxStartingPosition(x + 1);
				}
			} else if (next == 3) {
				// need to go down
				if (dir.equals("west")) {
					System.out.println(connector.turn(maze.getId(), "left"));
					maze.setStartingDirection("south");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setyStartingPosition(y + 1);
				} else {
					System.out.println(connector.turn(maze.getId(), "right"));
					maze.setStartingDirection("south");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setyStartingPosition(y + 1);
				}
			} else if (next == 4) {
				// need to go left
				if (dir.equals("north")) {
					System.out.println(connector.turn(maze.getId(), "left"));
					maze.setStartingDirection("west");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setxStartingPosition(x - 1);
				} else {
					System.out.println(connector.turn(maze.getId(), "right"));
					maze.setStartingDirection("west");
					System.out.println(connector.advance(maze.getId(), false));
					maze.setxStartingPosition(x - 1);

				}
			}
			System.out.println(x + " " + y);
		}

	}

	// x moves left and right
	// y moves up and down
	public int findNextStepchar(char[][] path, int cx, int cy, int px, int py) {
		if (cx + 1 != px && cx + 1 < path[0].length && path[cy][cx + 1] == 'o') {
			return 2;
		} else if (cx != 0 && cx - 1 != px && cx + 1 < path[0].length && path[cy][cx - 1] == 'o') {
			return 4;
		} else if (cy + 1 != py && cy + 1 < path.length && path[cy + 1][cx] == 'o') {
			return 3;
		} else if (cy != 0 && cy - 1 != py && path[cy - 1][cx] == 'o') {
			return 1;
		} else {
			return 0;
		}
	}

}
