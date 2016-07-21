package com.iets.ding;

import java.util.Date;

import com.iets.GridCanvas;

public class Clock extends IntervalDing {
	private Circle circle = new Circle();
	
	@Override
	public void doeJeIntervalDing(GridCanvas gc) {
		circle.doeJeDing(gc);

		Date date = new Date();
		int hoursAngle = ((int)date.getHours() % 12) * 30; // + date.getMinutes() / 60;
		int minutesAngle = date.getMinutes() * 6;
		int secondsAngle = date.getSeconds() * 6;
		
		paintAngle(gc, hoursAngle, 0x0000FF);
		paintAngle(gc, minutesAngle, 0xFF0000);
		paintAngle(gc, secondsAngle, 0xFFFF00);
	}
	
	private void paintAngle(GridCanvas gc, int angle, int color) {
		int[] endpoint = findEndpoint(gc, angle);
		gc.grid[endpoint[0]][endpoint[1]] = color;
		drawHand(gc, endpoint[0], endpoint[1]);
	}
	
	private int[] findEndpoint(GridCanvas gc, int angle) {
		double radius = ((double) gc.height-1) /2;

		double x = Math.sin(angle * (Math.PI / 180)) * radius;
		double y = Math.cos(angle * (Math.PI / 180)) * radius;
		
		int[] result = new int[2];
		result[0] = (int) Math.round(radius + x);
		result[1] = (int) Math.round(radius + y);
		
		return result;
	}
	
	private void drawHand(GridCanvas gc, int x, int y) {
		int base = gc.height / 2;

		int startx = base > x ? x : base;
		int starty = base > y ? y : base;
		int endx = base > x ? base : x;
		int endy = base > y ? base : y;

		double ratio = (double) (endy - starty) / (double) (endx - startx);
		System.out.println(endx - startx);
		System.out.println(endy - starty);
		System.out.println(ratio);

		
		for (int x1 = startx; x1 < endx; x1++) {
			int y1 = (int) (x1 * ratio);
			gc.grid[x1][y1] = 0;
		}
	}
}
