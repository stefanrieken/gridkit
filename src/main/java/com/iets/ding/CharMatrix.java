package com.iets.ding;

import java.util.Date;

import com.iets.GridCanvas;

public class CharMatrix extends IntervalDing {
	int count = 0;

	// giants
/*
	short[][] chars = {
			{6, 9, 9, 6},  // 0
			{15, 4, 4, 6}, // 1
			{14, 4, 8, 6}, // 2
			{7, 8, 4, 15}, // 3
			{8, 15, 9, 9}, // 4
			{7, 8, 7, 15}, // 5
			{6, 9, 7, 14}, // 6
			{2, 4, 8, 15}, // 7
			{7, 13, 11, 14}, // 8
			{7, 14, 9, 6}, // 9
	};
*/
	// dwarfs
	short[][] chars = {
			{6, 10, 12, 0},  // 0
			{14, 4, 6, 0}, // 1
			{14, 4, 8, 6}, // 2
			{6, 8, 4, 14}, // 3
			{8, 14, 10, 0}, // 4
			{6, 8, 6, 14}, // 5
			{4, 10, 6, 12}, // 6
			{4, 8, 14, 0}, // 7
			{7, 13, 11, 14}, // 8
			{6, 12, 10, 4}, // 9
	};

	@Override
	public void setup(GridCanvas gc) {
		interval = 2000L;
		super.setup(gc);
	}

	@Override
	public void doeJeIntervalDing(GridCanvas gc) {
		for (int offsetY = 0; offsetY < gc.height; offsetY+=4) {
			for (int offsetX = 0; offsetX < gc.width; offsetX+=4) {
				paintCharAt(gc, nextChar(), offsetX, offsetY);
			}
		}
	}

	private void paintCharAt(GridCanvas gc, short c, int offsetX, int offsetY) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y <4; y++) {
				boolean set = (c & (1 << (4*y + x))) != 0; 
				gc.grid[x+offsetX][y+offsetY] = set ? 0 : WHITE;

			}
		}
	}

	private short nextChar() {

//		if (count == chars.length) count = 0;
//		short[] c = chars[count++ % chars.length];

//		short[] c = chars[(int)(Math.random() * chars.length)];
		short[] c = chars[0];

		Date date = new Date();
		if (count %4 == 0) c = chars[date.getHours() / 10];
		if (count %4 == 1) c = chars[date.getHours() % 10];
		if (count %4 == 2) c = chars[date.getMinutes() / 10];
		if (count %4 == 3) c = chars[date.getMinutes() % 10];

		count++;
		if (count > 3) count = 0;

		short one = 0;
		for (int i = 0; i < c.length; i++) {
			one = (short) (one << (4) | c[i]);
		}

		return one;
	}
}
