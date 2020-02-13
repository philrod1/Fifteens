package solver.heuristic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import controller.MyListener;
import solver.AStarSolver;
import solver.BreadthFirstSolver;
import solver.Solver;
import view.GamePanel;
import model.Game;
import model.IntArrayGame;
import model.Move;

public class MyHeuristic implements Heuristic {
	
	private Game sub = null;

	private GamePanel gp;
	
	private static final Point[] outer = new Point[]{
		new Point(3,0),
		new Point(3,1),
		new Point(3,2),
		new Point(3,3),
		new Point(2,3),
		new Point(1,3),
		new Point(0,3)
	};

	@Override
	public int value(Game game) {
		int index = 0;
		int[][] state = game.getBoard();
		List<Integer> numbers = new ArrayList<Integer>(9);
		int r=0;
		for(int x = 0 ; x < 3 ; x++) {
			for(int y = 0 ; y < 3 ; y++) {
				int v1 = state[x][y]; 
				if(v1 > 8) {
					for( ; index < 7 ; index++) {
						int v2 = state[outer[index].x][outer[index].y];
						if(v2 < 9) {
							state[x][y] = v2;
							state[outer[index].x][outer[index].y] = v1;
							r += Math.abs(x - outer[index].x) + Math.abs(y - outer[index].y);
							index++;
							break;
						}
					}
				}
				numbers.add(state[x][y]);
			}	
		}
		sub = new IntArrayGame(3, 3, numbers, null, 0x87654321L);
		Solver solver = new BreadthFirstSolver();
		System.out.println("SUB:");
		sub.print();
		gp = new GamePanel(sub);
		gp.addListener(new MyListener(this));
		JFrame frame = new JFrame("Puzzle2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 100 * 3 + 17, 100 * 3 + 39);
		frame.add(gp);
		frame.setVisible(true);
		List<Move> sol = solver.solve(sub);
		if(sol==null) {
			System.out.println("No solution");
			for(int i = 0 ; i < 8 ; i++) {
				if(numbers.get(i) > 0 && numbers.get(i+1) > 0) {
				int n1 = numbers.remove(i);
				int n2 = numbers.remove(i);
				numbers.add(n1);
				numbers.add(n2);
				}
			}
		}
		sub = new IntArrayGame(3, 3, numbers, null, 0x87654321L);
		solver = new AStarSolver(new ManhattanDistance(3,3));
		System.out.println("SUB2:");
		sub.print();
		sol = solver.solve(sub);
		System.out.println(sol == null);
		r += sol.size();
		return r;
	}

//	public void moveClick(Move move) {
//		sub.makeMove(move);
//		gp.updateTiles(sub.getSpace(), move);
//		if(sub.isGoal()) {
//			System.out.println("GOAL!");
//		}
//	}

}
