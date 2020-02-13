package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Move;
import solver.heuristic.MyHeuristic;
import view.TilePanel;

public class MyListener extends MouseAdapter {
	private GameController gc;
	public MyListener (GameController gc) {
		this.gc = gc;
	}
	private MyHeuristic mh;
	public MyListener (MyHeuristic mh) {
		this.mh = mh;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof TilePanel) {
			if(gc!=null)
				gc.moveClick(((TilePanel) e.getSource()).getMove());
//			if(mh!=null)
//				mh.moveClick(((TilePanel) e.getSource()).getMove());
		}
	}

}
