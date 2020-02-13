package solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import model.Move;
import solver.heuristic.Heuristic;
import solver.heuristic.HeuristicNode;
//import com.carrotsearch.hppc.LongOpenHashSet;
import model.Game;

public class AStarSolver extends AbstractSolver {
	
	private HashMap<Integer, HashSet<Integer>> visited = new HashMap<Integer, HashSet<Integer>>();
//	private LongOpenHashSet agendaTest = new LongOpenHashSet();
	private Heuristic heuristic;

	public AStarSolver(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public List<Move> solve(Game game) {
		PriorityQueue<HeuristicNode> agenda = new PriorityQueue<HeuristicNode>();
		HeuristicNode start = new HeuristicNode(game, 0, heuristic.value(game));
		agenda.add(start);
		while(agenda.size() > 0) {
			HeuristicNode currNode = agenda.poll();
			Game current = currNode.state;
			if (current.isGoal()) {
				return calculateMoves(game, current);
			}
			List<Game> children = current.nextStates();
			for(Game child : children) {
				if (current.isGoal()) {
					return calculateMoves(game, child);
				} else if (!visited(child)) {
					HeuristicNode node = new HeuristicNode(child, currNode.g+1, heuristic.value(child));
					agenda.offer(node);
					visit(child);
				}
			}
		}
		return null;
	}

	@Override
	protected void visit(Game game) {
		HashSet<Integer> set = visited.get((int)(game.longId() >>> 32));
		if(set == null) {
			set = new HashSet<Integer>();
			visited.put((int)(game.longId() >>> 32), set);
		}
		set.add((int)game.longId());
	}
	
	@Override
	protected boolean visited(Game game) {
		HashSet<Integer> set = visited.get((int)(game.longId() >>> 32));
		if(set != null) {
			return set.contains((int)game.longId());
		}
		return false;
	}
}
