package solver;

import java.util.List;
import java.util.Stack;

import model.Game;
import model.Move;
import solver.heuristic.Heuristic;
import solver.heuristic.ManhattanDistance;
import solver.heuristic.MySimpleHeuristic;

public class IDDepthFirstSearch extends AbstractSolver {

	private Heuristic heuristic;
//	private BinarySearchTree visited = new BinarySearchTree(0);
//	private HashSet<Game> visited = new HashSet<Game>();
	
//	private HashMap<Integer, HashSet<Integer>> visited = new HashMap<Integer, HashSet<Integer>>();
//	private HashMap<Integer, HashSet<Integer>> agendaCheck = new HashMap<Integer, HashSet<Integer>>();
	
	@Override
	public List<Move> solve(Game game) {
		int[][] board = game.getBoard();
		heuristic = new MySimpleHeuristic(board.length, board[0].length);
		List<Move> result = null;
		for(int limit = heuristic.value(game) ; limit < 81 ; limit++) {
			System.out.print(limit);
			long start = System.currentTimeMillis();
//			visited.clear();
			result = dfs(game, game, limit, 0, new Stack<Game>());
			System.out.println(" = " + (System.currentTimeMillis()-start));
			if(result != null) {
				return result;
			}
		}
		return result;
	}

	private List<Move> dfs(Game start, Game current, int limit, int depth, Stack<Game> path) {
		if(current.isGoal()) {
			return calculateMoves(start, current);
		}
		visit(current);
		if(depth < limit) {
			for(Game child : current.nextStates()) {
				if(!path.contains(child)) {
					path.push(child);
					List<Move> result = dfs(start, child, limit, depth+1, path);
					if(result != null) {
						return result;
					}
					path.pop();
				}
			}
		}
		return null;
	}
	
//	@Override
//	protected void visit(Game game) {
//		HashSet<Integer> set = visited.get((int)(game.longId() >>> 32));
//		if(set == null) {
//			set = new HashSet<Integer>();
//			visited.put((int)(game.longId() >>> 32), set);
//		}
//		set.add((int)game.longId());
//	}
//	
//	@Override
//	protected boolean visited(Game game) {
//		HashSet<Integer> set = visited.get((int)(game.longId() >>> 32));
//		if(set != null) {
//			return set.contains((int)game.longId());
//		}
//		return false;
//	}
}
