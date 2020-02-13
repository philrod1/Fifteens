package solver;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Game;
import model.Move;
import solver.heuristic.Heuristic;
import solver.heuristic.ManhattanDistance;

public class EnforcedHillClimbingSolver extends AbstractSolver {

	@Override
	public List<Move> solve(Game game) {
		Heuristic heuristic = new ManhattanDistance(game.getSize().width, game.getSize().height);
		Queue<Game> agenda = new LinkedList<Game>();
		agenda.add(game);
		agendaAdd(game);
		int bestHeuristic = heuristic.value(game);
		
		while(!agenda.isEmpty()) {
			Game currentState = agenda.remove();
			visit(currentState);
			List<Game> children = currentState.nextStates();
			for(Game child : children) {
				if(!visited(child) && !agendaContains(child)) {
					if(child.isGoal()) {
						return calculateMoves(game, child);
					}
					agenda.add(child);
					agendaAdd(child);
					int heuristicValue = heuristic.value(child);
					if (heuristicValue > bestHeuristic) {
						agenda.clear();
						agendaClear();
						agenda.add(child);
						agendaAdd(child);
						bestHeuristic = heuristicValue;
						break;
					}
				}
			}
		}
		return null;
	}
}
