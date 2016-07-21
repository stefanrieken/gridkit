package com.iets.ding;

import com.iets.GridCanvas;

public class ComplexLife extends IntervalDing {
	private int ALIVE_R = 0xFF0000;
	private int ALIVE_G = 0x00FF00;
	private int ALIVE_B = 0x0000FF;
	private int BORN_R = 0xFF8080;
	private int BORN_G = 0x80FF80;
	private int BORN_B = 0x8080FF;
	private int DIED = 0x808080;
	private int DEAD = WHITE;

	private int[] STATES = {ALIVE_R, ALIVE_G, ALIVE_B, BORN_R, BORN_G, BORN_B, DIED, DEAD};

	// tweaking params

	// when to mate
	private boolean JONG = false;
	private boolean OLD = true;

	// shorten the life cycle from 4 states to 3 or 2
	private boolean SHORT_LIVED = false; // nb only 'born' is kept; 'alive' is gone, so use JONG
	private boolean SUDDEN_DEATH = true; // here 'dead' is kept, but it shouldn't matter

	@Override
	public void setup(GridCanvas gc) {
		this.interval = 0L;

		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				int random = random(6);

				gc.grid[x][y] = STATES[random];
			}
		}
	}

	@Override
	public void doeJeIntervalDing(GridCanvas gc) {
		int[][] newValues = new int[gc.width][gc.height];

		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {

				newValues[x][y] = gc.grid[x][y];

				if (gc.grid[x][y] == DEAD) {

					int rs = 0;
					if (OLD) rs += neighboursWith(ALIVE_R, gc, x, y);
					if (JONG) rs += neighboursWith(BORN_R, gc, x, y);

					int gs = 0;
					if (OLD) gs += neighboursWith(ALIVE_G, gc, x, y);
					if (JONG) gs += neighboursWith(BORN_G, gc, x, y);

					int bs = 0;
					if (OLD) bs += neighboursWith(ALIVE_B, gc, x, y);
					if (JONG) bs += neighboursWith(BORN_B, gc, x, y);

					if (rs == gs && rs != bs) {
						newValues[x][y] = BORN_B; 
					} else if (rs == bs && rs != gs) {
						newValues[x][y] = BORN_G;
					} else if (gs == bs && gs != rs) {
						newValues[x][y] = BORN_R;
					}
				} else if (gc.grid[x][y] == BORN_R) {
					newValues[x][y] = SHORT_LIVED ? DIED : ALIVE_R;
				} else if (gc.grid[x][y] == BORN_G) {
					newValues[x][y] = SHORT_LIVED ? DIED : ALIVE_G;
				} else if (gc.grid[x][y] == BORN_B) {
					newValues[x][y] = SHORT_LIVED ? DIED : ALIVE_B;
				} else if (gc.grid[x][y] == ALIVE_R || gc.grid[x][y] == ALIVE_G || gc.grid[x][y] == ALIVE_B) {
					newValues[x][y] = DIED;
				} else if (gc.grid[x][y] == DIED) {
					newValues[x][y] = DEAD;
				}
				
				if (SUDDEN_DEATH && newValues[x][y] == DIED) {
					newValues[x][y] = DEAD;
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

	private int neighboursWith(int state, GridCanvas gc, int x, int y) {
		int neighbours = 0;
		int[][] grid = gc.grid;
		if (state(grid, x - 1, y - 1) == state) neighbours++;
		if (state(grid, x, y - 1) == state) neighbours++;
		if (state(grid, x + 1, y - 1) == state) neighbours++;
		if (state(grid, x - 1, y) == state) neighbours++;
		if (state(grid, x + 1, y) == state) neighbours++;
		if (state(grid, x - 1, y + 1) == state) neighbours++;
		if (state(grid, x, y + 1) == state) neighbours++;
		if (state(grid, x + 1, y + 1) == state) neighbours++;
		
		return neighbours;
	}

	private int state(int[][] grid, int x, int y) {
		x = correct(x, grid.length);
		y = correct(y, grid[0].length);
		
		return grid[x][y];
	}

	private int correct(int toCorrect, int max) {
		if (toCorrect < 0) {
			return max + toCorrect;
		} else if (toCorrect >= max) {
			return toCorrect - max;
		}
		return toCorrect;
	}
}
