package solver.heuristic;

import model.Game;

public class MySimpleHeuristic implements Heuristic {

	private final ManhattanDistance md;
	
	public MySimpleHeuristic(int width, int height) {
		md = new ManhattanDistance(width, height);
	}
	
	@Override
	public int value(Game game) {
		return (int) ((md.value(game)  + parity(game)));
	}

	private int parity(Game game) {
		int[][] grid = game.getBoard();
		int w = game.getSize().width;
		int h = game.getSize().height;
		int[] a = new int[w * h];
		for(int x = 0 ; x < w ; x++) {
			for(int y = 0 ; y < h ; y++) {
				a[y*w+x] = grid[x][y];
			}
		}
		return parity(a)/2;
	}

	private int parity(int... puzzle) {
		int parity = 0;
		for (int tile = 0; tile < puzzle.length; tile++) {
			for (int tileBefore = 0; tileBefore < tile; tileBefore++) {
				if (puzzle[tileBefore] > puzzle[tile])
					parity++;
			}
		}
		
		return parity;
	}

}
