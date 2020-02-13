package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IntArrayGame implements Game {

	private int[][] board;
	private final long goal;
	private int uid;
	private long longID;
	private List<Integer> numbers;
	private int width, height;
	private Point space;
	// private Stack<Move> undoStack;
	private Game parent;

	public IntArrayGame(int width, int height) {
		this.width = width;
		this.height = height;
		board = new int[width][height];
		numbers = new LinkedList<Integer>();
		// undoStack = new Stack<Move>();
		for (int n = 1; n < width * height; n++) {
			numbers.add(n);
		}
		numbers.add(0);
		reset();
		parent = null;
		goal = longID;
//		System.out.println("Goal = " + Long.toHexString(goal));
	}

	public IntArrayGame(int width, int height, List<Integer> numbers,
			Game parent, long goal) {
		this.width = width;
		this.height = height;
		board = new int[width][height];
		this.numbers = numbers;
		reset();
		this.parent = parent;
		this.goal = goal;
	}

	@Override
	public int hashCode() {
		return ((Long)longID).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntArrayGame other = (IntArrayGame) obj;
		return Arrays.deepEquals(board, other.board);
	}

	@Override
	public void print() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(Integer.toHexString(board[x][y]));
			}
			System.out.println();
		}
	}

	@Override
	public void reset() {
		int i = 0;
		for(int y = 0 ; y < height ; y++) {
			for(int x = 0 ; x < width ; x++) {
				board[x][y] = numbers.get(i++);
				if(board[x][y]==0)
					space = new Point(x, y);
			}
		}
		setUid();
		setLongID();
	}

	private void setLongID() {
		longID = 0;
		int i = 0;
		for(int y = 0 ; y < height ; y++) {
			for(int x = 0 ; x < width ; x++) {
				longID |= ((long)board[x][y] << (4 * i++));
			}
		}
//		System.out.println(Long.toHexString(longID));
//		print();
	}

	private void setUid() {
		uid = 0;
		for(int x = 0 ; x < width ; x++) {
			for(int y = 0 ; y < height ; y++) {
				uid *= 10;
				uid += board[x][y];
			}
		}
	}

	@Override
	public void shuffle(int n) {
		Random rng = new Random();
		for (int i = 0; i < n; i++) {
			int r = rng.nextInt(getMoves().size());
			Move move = getMoves().get(r);
			makeMove(move);
		}
	}

	@Override
	public boolean makeMove(Move move) {
		// System.out.println(move + " : " + space);
		int dx = (space.x - move.x) * (space.x - move.x);
		int dy = (space.y - move.y) * (space.y - move.y);
		// System.out.println(dx + " " + dy);
		if (dx + dy != 1)
			return false; // Invalid move
		int n = board[move.x][move.y];
		board[move.x][move.y] = 0;
		board[space.x][space.y] = n;
		// undoStack.push(new Move(space.x, space.y));
		space = new Point(move.x, move.y);
		setUid();
		setLongID();
		return true;
	}

//	@Override
//	public boolean makeMove(Game game) {
//		Move move = calculateMove(game);
//		return makeMove(move);
//	}
//
//	private Move calculateMove(Game game) {
//		return new Move(game.getSpace().x, game.getSpace().y);
//	}

	@Override
	public List<Game> nextStates() {
		List<Game> next = new LinkedList<Game>();
		for (Move move : getMoves()) {
			IntArrayGame clone = clone();
			clone.makeMove(move);
			next.add(clone);
		}
		return next;
	}

	// @Override
	private List<Move> getMoves() {
		List<Move> moves = new LinkedList<Move>();
		if (space.x < width - 1)
			moves.add(new Move(space.x + 1, space.y));
		if (space.y < height - 1)
			moves.add(new Move(space.x, space.y + 1));
		if (space.x > 0)
			moves.add(new Move(space.x - 1, space.y));
		if (space.y > 0)
			moves.add(new Move(space.x, space.y - 1));
		return moves;
	}

	@Override
	public boolean isGoal() {
		return longID == goal;
	}

	@Override
	public IntArrayGame clone() {
		List<Integer> numbers = new LinkedList<Integer>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				numbers.add(board[x][y]);
			}
		}
		return new IntArrayGame(width, height, numbers, this, goal);
	}

	@Override
	public int[][] getBoard() {
		int[][] clone = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				clone[x][y] = board[x][y];
			}
		}
		return clone;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	@Override
	public boolean canMove(int x, int y) {
		int dx = (space.x - x) * (space.x - x);
		int dy = (space.y - y) * (space.y - y);
		return (dx + dy == 1);
	}

	@Override
	public Point getSpace() {
		return new Point(space.x, space.y);
	}

	@Override
	public Game getParent() {
		return parent;
	}

	@Override
	public int uid() {
		return uid;
	}
	
	@Override
	public long longId() {
		return longID;
	}

	// @Override
	// public boolean undo() {
	// if (undoStack.size() > 0) {
	// Move move = undoStack.pop();
	// // System.out.println(move + " : " + space);
	// int dx = (space.x - move.x) * (space.x - move.x);
	// int dy = (space.y - move.y) * (space.y - move.y);
	// // System.out.println(dx + " " + dy);
	// if (dx + dy != 1)
	// return false; // Invalid move
	// int n = board[move.x][move.y];
	// board[move.x][move.y] = 0;
	// board[space.x][space.y] = n;
	// space = new Point(move.x, move.y);
	// return true;
	// }
	// return false;
	// }

}
