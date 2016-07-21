package com.iets.ding;

import com.iets.GridCanvas;

public class Life extends IntervalDing {
	private int ALIVE = BLACK;
	private int BORN = 0x808080;
	private int DIED = 0x404040;
	private int DEAD = WHITE;

	//private boolean[][] values;

	@Override
	public void setup(GridCanvas gc) {
		this.interval = 10L;

		randomSetup(gc);
//		gliderSetup(gc);
	}

	private void randomSetup(GridCanvas gc) {
		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				int random = random(2);
				gc.grid[x][y] = random * WHITE;
			}
		}
	}

	private void gliderSetup(GridCanvas gc) {
		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				gc.grid[x][y] = DEAD;
			}
		}

		gc.grid[0][1] = ALIVE;
		gc.grid[1][2] = ALIVE;
		gc.grid[2][0] = ALIVE;
		gc.grid[2][1] = ALIVE;
		gc.grid[2][2] = ALIVE;
	}

	@Override
	public void doeJeIntervalDing(GridCanvas gc) {
		int[][] newValues = new int[gc.width][gc.height];

		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				int neighbours = 0;
				if (alive(gc.grid, x - 1, y - 1)) neighbours++;
				if (alive(gc.grid, x, y - 1)) neighbours++;
				if (alive(gc.grid, x + 1, y - 1)) neighbours++;
				if (alive(gc.grid, x - 1, y)) neighbours++;
				if (alive(gc.grid, x + 1, y)) neighbours++;
				if (alive(gc.grid, x - 1, y + 1)) neighbours++;
				if (alive(gc.grid, x, y + 1)) neighbours++;
				if (alive(gc.grid, x + 1, y + 1)) neighbours++;

				newValues[x][y] = gc.grid[x][y];
				if (alive(gc.grid, x, y)) {
					// Any live cell with fewer than two live neighbours dies
					// Any live cell with more than three live neighbours dies
					if (neighbours < 2 || neighbours > 3) {
						newValues[x][y] = DIED;
					} else {
						newValues[x][y] = ALIVE;
					}
				} else {
					// Any dead cell with exactly three live neighbours becomes
					// a live cell
					if (neighbours == 3) {
						newValues[x][y] = BORN;
					} else {
						newValues[x][y] = DEAD;
					}
				}
			}
		}

		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				gc.grid[x][y] = newValues[x][y];
			}
		}

//		boolean[][] temp = values;
//		values = newValues;
//		newValues = temp;
	}

	private boolean alive(int[][] grid, int x, int y) {
		if (x < 0) {
			x = grid.length + x;
		} else if (x >= grid.length) {
			x = x - grid.length;
		}

		if (y < 0) {
			y = grid[0].length + y;
		} else if (y >= grid[0].length) {
			y = y - grid[0].length;
		}

		return (grid[x][y] == ALIVE) || (grid[x][y] == BORN);
	}
}
