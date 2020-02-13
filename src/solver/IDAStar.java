package solver;

import java.util.LinkedList;
import java.util.List;

import model.Game;
import model.Move;
import solver.heuristic.BFSHeuristic;
import solver.heuristic.Heuristic;
import solver.heuristic.SpaceHeuristic;

public class IDAStar implements Solver {

	private final Heuristic h;
	private List<Move> path = null;
	
	public IDAStar(Heuristic h) {
		this.h = new BFSHeuristic();
	}
	
	@Override
	public List<Move> solve(Game game) {
		int limit = h.value(game);
		while(true) {
			System.out.println(limit);
			int t = search(game, game, 0, limit);
			if(t == -1) return path;
			if(t == Integer.MAX_VALUE) return null;
			limit = t;
		}
	}

	private int search(Game start, Game current, int depth, int limit) {
//		System.out.println(current.longId() + " " + sh.value(current));
		int f = depth + h.value(current);
		if (f > limit) return f;
		if(current.isGoal()) {
			path = calculateMoves(start, current);
			return -1;
		}
		int min = Integer.MAX_VALUE;
		for(Game child : current.nextStates()) {
			int t = search(start, child, depth + 1, limit);
			if(t == -1) return -1;
			if (t < min) {
				min = t;
			}
		}
		return min;
	}
	
	private List<Move> calculateMoves(Game game, Game current) {
		List<Move> moves = new LinkedList<Move>();
		while (current != null) {
			moves.add(0, new Move(current.getSpace().x,current.getSpace().y));
			current = current.getParent();
		}
		return moves;
	}
}
