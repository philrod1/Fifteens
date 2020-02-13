package solver.heuristic;

import model.Game;

public class BFSHeuristic implements Heuristic {


	private static final int[] ROW = {3,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3};
	private static final int[] COL = {3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2};
	
	@Override
	public int value(Game game) {
		int[][] state = game.getBoard();
		int r=0;
		for(int i=0;i<game.getSize().height;i++)
			for(int j=0;j<game.getSize().width;j++)
				if(state[i][j]!=0)
					r+=Math.abs(ROW[state[i][j]]-i)+Math.abs(COL[state[i][j]]-j);
		return r;
	}
}
