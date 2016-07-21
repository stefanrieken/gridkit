package com.iets;

import java.awt.Color;

public class PaneCanvas extends GridCanvas {
	private GridPane gridPane;

	public PaneCanvas(GridPane gridPane) {
		super(gridPane.width, gridPane.height);
		this.gridPane = gridPane;
	}

	@Override
	public void paint() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				gridPane.cells[x][y].setBackground(new Color(grid[x][y]));
			}
		}
		gridPane.repaint();
	}

}
