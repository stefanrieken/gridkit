package com.iets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GridPane extends JPanel {
	private static final long serialVersionUID = 1L;

	public int height;
	public int width;
	public JPanel[][] cells;

    public GridPane(int height, int width, int size) {
    	this.height = height;
    	this.width = width;
    	cells = new JPanel[width][height];
    	setup(size);
    }
    
    private void setup(int size) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gbc.gridx = x;
                gbc.gridy = y;

                cells[x][y] = new JPanel();
                cells[x][y].setPreferredSize(new Dimension(size, size));
                cells[x][y].setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
                //cells[x][y].setToolTipText("" + i++);
                cells[x][y].setToolTipText("" + x + "," + y);
                add(cells[x][y], gbc);
            }
        }
    }
}