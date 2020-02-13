package solver.heuristic;

import java.awt.Point;

import model.Game;

public class SpaceHeuristic implements Heuristic {

	@Override
	public int value(Game game) {
		Point space = game.getSpace();
		
		return Math.abs(space.x - (game.getBoard().length-1)) + Math.abs(space.y - (game.getBoard()[0].length-1));
	}

}
