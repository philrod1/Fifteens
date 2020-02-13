package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Game;
import model.IntArrayGame;
import model.Move;
import solver.AStarSolver;
import solver.Solver;
import solver.heuristic.Heuristic;
import solver.heuristic.ManhattanDistance;
import solver.heuristic.MySimpleHeuristic;
import view.GamePanel;

public class GameController {
	private Game game;
	private GamePanel gp;
	public GameController (final int width, final int height) {
		game = new IntArrayGame(width, height);
		game.shuffle(80000);
		gp = new GamePanel(game);
		gp.addListener(new MyListener(this));
		SwingUtilities.invokeLater(() -> {
				JFrame frame = new JFrame("Puzzle");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(100, 100, 100 * width + 17, 100 * height + 39);
				frame.add(gp);
				frame.setVisible(true);
		});
	}

	public void solve() {
		Heuristic h = new MySimpleHeuristic(game.getBoard().length, game.getBoard()[0].length);
		System.out.println("Heuristic = " + h.value(game));
		Solver s = new AStarSolver(h);
		System.out.println("solving");
		long start = System.currentTimeMillis();
		List<Move> moves = s.solve(game);
		if(moves != null) {
			System.out.println("Solved");
			System.out.println(System.currentTimeMillis() - start);
			System.out.println(moves.size());
			for(Move move : moves) {
				moveClick(move);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Fail.");
		}
	}

	public void moveClick(Move move) {
		gp.makeMove(game, move);
		game.makeMove(move);
//		gp.updateTiles(game);
		if(game.isGoal()) {
			System.out.println("GOAL!");
		}
	}
	
}
