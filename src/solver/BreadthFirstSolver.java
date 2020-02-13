package solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Game;
import model.Move;

public class BreadthFirstSolver extends AbstractSolver {
	
	private HashMap<Integer, HashSet<Integer>> visited = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<Integer>> agendaCheck = new HashMap<Integer, HashSet<Integer>>();
	
	@Override
	public List<Move> solve(Game game) {
		Queue<Game> agenda = new LinkedList<Game>();
		agenda.add(game);
//		agendaAdd(game);
		visited.clear();
		while(agenda.size() > 0) {
			Game current = agenda.remove();
//			agendaRemove(current);
			if (current.isGoal()) {
//				System.out.println("Goal!");
				return calculateMoves(game, current);
			}
			List<Game> children = current.nextStates();
			for(Game child : children) {
				if(!visited(child)) {
					agenda.add(child);
					agendaAdd(child);
					visit(child);
				}
			}
		}
		return null;
	}

	@Override
	protected void agendaRemove(Game game) {
		HashSet<Integer> set = agendaCheck.get((int)(game.longId() >>> 32));
		if(set != null) {
			set.remove((int)game.longId());
		}
	}

	@Override
	protected void agendaAdd(Game game) {
		HashSet<Integer> set = agendaCheck.get((int)(game.longId() >>> 32));
		if(set == null) {
			set = new HashSet<Integer>();
			agendaCheck.put((int)(game.longId() >>> 32), set);
		}
		set.add((int)game.longId());
	}
	
	@Override
	protected boolean agendaContains(Game game) {
		HashSet<Integer> set = agendaCheck.get((int)(game.longId() >>> 32));
		if(set != null) {
			return set.contains((int)game.longId());
		}
		return false;
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
