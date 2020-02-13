package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;


public interface Game {
	void print();
	void reset();
	void shuffle(int n);
	boolean makeMove(Move move);
//	boolean makeMove(Game game);
	List<Game> nextStates();
	boolean isGoal();
	int[][] getBoard();
	Dimension getSize();
	boolean canMove(int x, int y);
	Point getSpace();
	Game getParent();
	int uid();
	long longId();
}
