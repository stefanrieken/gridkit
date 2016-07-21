package com.iets.ding;

import com.iets.GridCanvas;
import com.iets.GridPane;

public abstract class IntervalDing extends Ding {
	protected long interval = 300L;

	@Override
	public void doeJeDing(GridCanvas gc) {
		setup(gc);
		gc.paint();

    	while (true) {
    		try {
    			Thread.sleep(interval);
    		} catch (Exception e) {}

    		doeJeIntervalDing(gc);
    		gc.paint();
    	}
	}

	public void setup(GridCanvas gc) {
		doeJeIntervalDing(gc);
	}

	public abstract void doeJeIntervalDing(GridCanvas gc);
}
