package solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Game;
import model.Move;

public abstract class AbstractSolver implements Solver {
	

	private Set<Game> visitedSet = new HashSet<Game>();
	private Set<Game> agendaSet = new HashSet<Game>();
	
	protected void visit(Game game) {
		visitedSet.add(game);
	}
	
	protected boolean visited(Game game) {
		return visitedSet.contains(game);
	}
	
	protected void clearVisited() {
		visitedSet.clear();
	}
	
	protected void agendaAdd(Game game) {
		agendaSet.add(game);
	}
	
	protected void agendaRemove(Game game) {
		agendaSet.remove(game);
	}
	
	protected boolean agendaContains(Game game) {
		return agendaSet.contains(game);
	}

	protected void agendaClear() {
		agendaSet.clear();
	}
	
	protected List<Move> calculateMoves(Game game, Game current) {
		List<Move> moves = new LinkedList<Move>();
		while (current != null) {
//			System.out.println(">>" + moves.size());
			moves.add(0, new Move(current.getSpace().x,current.getSpace().y));
			current = current.getParent();
		}
		return moves;
	}
}
