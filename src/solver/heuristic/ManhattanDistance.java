package solver.heuristic;

import java.awt.Point;

import model.Game;

public class ManhattanDistance implements Heuristic {
	private Point[] origins;
	
	public ManhattanDistance (int w, int h) {
		int n = w * h;
		origins = new Point[n + 1];
		int i = 0;
		origins[0] = new Point(w-1,h-1);
		System.out.println(0 + " - " + origins[0]);
		for(int y = 0 ; y < h ; y++) {
			for(int x = 0 ; x < w ; x++) {
				origins[++i] = new Point(x,y);
				System.out.println(i + " " + origins[i]);
			}
		}
	}
	
	@Override
	public int value(Game game) {
		int distance = 0;
		int[][] board = game.getBoard();
		for(int x = 0 ; x < game.getSize().width ; x++) {
			for(int y = 0 ; y < game.getSize().height ; y++) {
				int n = board[x][y];
				if (n>0) {
					distance += manhattanDistance(new Point(x,y), origins[n]);
//					System.out.println("Tile " + n + " should be (" + origins[n-1].x + "," + origins[n-1].y +
//							"), is at (" + x + "," + y + ").  Distance = " + manhattanDistance(new Point(x,y), origins[n-1]));
				}
			}
		}
		return distance;
	}
	
	private int manhattanDistance (Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); 
	}

}
