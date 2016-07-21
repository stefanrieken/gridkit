package com.iets.ding;

import com.iets.GridCanvas;

public class Circle extends Ding {

	@Override
	public void doeJeDing(GridCanvas gc) {
		double radius = ((double) gc.height-1) /2;

		for (int x = 0; x < gc.width; x++) {
			for (int y = 0; y < gc.height; y++) {
				 double aa = Math.abs(x-radius)*Math.abs(x-radius);
				 double bb = Math.abs(y-radius)*Math.abs(y-radius);

				int cc = (int) Math.sqrt(aa + bb);
				if (cc == Math.floor(radius)) {
					gc.grid[x][y] = 0;
				} else {
					gc.grid[x][y] = WHITE;
				}
			}
		}
	}
}
