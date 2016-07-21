package com.iets.ding;

import com.iets.GridCanvas;

public abstract class Ding {
	protected static int BLACK = 0x000000;
	protected static int WHITE = 0xFFFFFF;
	
    protected int random(int max) {
    	return (int) (Math.random() * max);
    }

	public abstract void doeJeDing(GridCanvas gc);
}
