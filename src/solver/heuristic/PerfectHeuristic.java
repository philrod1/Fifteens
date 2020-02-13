package solver.heuristic;

import model.Game;
import solver.BreadthFirstSolver;
import solver.Solver;

public class PerfectHeuristic implements Heuristic {

	private Solver solver = new BreadthFirstSolver();
	
	@Override
	public int value(Game game) {
		return solver.solve(game).size();
	}

}
