package com.iets;

public abstract class GridCanvas {
	public int width;
	public int height;
	
	public int[][] grid;

	public GridCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new int[width][height];
	}

	public abstract void paint();
}
