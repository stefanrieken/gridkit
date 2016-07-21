package com.iets.ding;

import com.iets.GridCanvas;

public class Unicorn extends IntervalDing {

	@Override
	public void doeJeIntervalDing(GridCanvas gc) {
		int x = random(gc.width);
		int y = random(gc.height);
		int color = random(WHITE);

		gc.grid[x][y] = color;
	}
}
