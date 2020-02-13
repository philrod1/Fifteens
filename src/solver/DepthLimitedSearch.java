package solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import solver.heuristic.ManhattanDistance;
import model.Game;
import model.Move;

public class DepthLimitedSearch extends AbstractSolver {
	
	private HashMap<Integer, HashSet<Integer>> pathCheck = new HashMap<Integer, HashSet<Integer>>();

	@Override
	public List<Move> solve(Game game) {
		ManhattanDistance heuristic = new ManhattanDistance(game.getSize().width, game.getSize().height);
		return dfs(game, game, 81, heuristic.value(game), new Stack<Game>());
	}
	
	private List<Move> dfs(Game start, Game current, int limit, int depth, Stack<Game> path) {
		System.out.println(depth);
		if(current.isGoal()) {
			return calculateMoves(start, current);
		}
		visit(current);
		if(depth < limit) {
			for(Game child : current.nextStates()) {
				if(!pathContains(child)) {
					path.push(child);
					pathAdd(child);
					List<Move> result = dfs(start, child, limit, depth+1, path);
					if(result != null) {
						return result;
					}
					path.pop();
					pathRemove(child);
				}
			}
		}
		return null;
	}
	
	private void pathRemove(Game game) {
		HashSet<Integer> set = pathCheck.get((int)(game.longId() >>> 32));
		if(set != null) {
			set.remove((int)game.longId());
		}
	}

	private void pathAdd(Game game) {
		HashSet<Integer> set = pathCheck.get((int)(game.longId() >>> 32));
		if(set == null) {
			set = new HashSet<Integer>();
			pathCheck.put((int)(game.longId() >>> 32), set);
		}
		set.add((int)game.longId());
	}
	
	private boolean pathContains(Game game) {
		HashSet<Integer> set = pathCheck.get((int)(game.longId() >>> 32));
		if(set != null) {
			return set.contains((int)game.longId());
		}
		return false;
	}

}
