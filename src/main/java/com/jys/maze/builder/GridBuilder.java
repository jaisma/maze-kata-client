package com.jys.maze.builder;

import java.util.List;

/**
 * Deprecated
 * @author Jai Sun
 *
 */
public class GridBuilder {

	@SuppressWarnings("unchecked")
	public String[][] buildGrid(List<Object> gridList) {
		int rowNum = gridList.size();
		int colNum = ((List<String>) gridList.get(0)).size();

		String[][] grid = new String[rowNum][colNum];

		for (int i = 0; i < rowNum; ++i) {
			for (int j = 0; j < colNum; ++j) {
				List<String> temp = (List<String>) gridList.get(i);
				String cleanString = temp.get(j).substring(8, temp.get(j).lastIndexOf('-'));
				if (cleanString.equals("cancel")) {
					cleanString = "FINISH";
				}
				grid[i][j] = String.format("%1$5s", cleanString);
			}
		}

		return grid;
	}

}
