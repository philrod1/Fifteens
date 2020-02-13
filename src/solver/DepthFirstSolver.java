package solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import model.Game;
import model.Move;

public class DepthFirstSolver extends AbstractSolver {
	private HashMap<Integer, HashSet<Integer>> pathCheck = new HashMap<Integer, HashSet<Integer>>();

	@Override
	public List<Move> solve(Game game) {
		return dfs(game, game, new Stack<Game>());
	}
	
	private List<Move> dfs(Game start, Game current, Stack<Game> path) {
		if(current.isGoal()) {
			return calculateMoves(start, current);
		}
		for(Game child : current.nextStates()) {
			if(!pathContains(child)) {
				path.push(child);
				pathAdd(child);
				List<Move> result = dfs(start, child, path);
				if(result != null) {
					return result;
				}
				path.pop();
				pathRemove(child);
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
