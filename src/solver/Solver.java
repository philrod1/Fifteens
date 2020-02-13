package solver;

import java.util.List;

import model.Game;
import model.Move;

public interface Solver {
	List<Move> solve(Game game);
}
