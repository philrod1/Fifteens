package view;

import java.awt.Point;

import javax.swing.JPanel;

import model.Game;
import model.Move;
import controller.MyListener;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -5444461222633800021L;
	private final TilePanel[][] tiles;
	private final int width, height, scale = 100;

	public GamePanel(Game game) {
		setLayout(null);
		int[][] board = game.getBoard();
		width = game.getSize().width;
		height = game.getSize().height;
		tiles = new TilePanel[width][height];
		for(int y = 0 ; y < height ; y++) {
			for(int x = 0 ; x < width ; x++) {
				tiles[x][y] = new TilePanel(x, y, board[x][y], scale);
				add(tiles[x][y]);
				tiles[x][y].setBounds(x*scale, y*scale, scale, scale);
			}
		}
	}
	
	public void updateTiles(Game game) {
		int[][] board = game.getBoard();
		for(int y = 0 ; y < height ; y++) {
			for(int x = 0 ; x < width ; x++) {
				tiles[x][y].setN(board[x][y]);
			}
		}
		repaint();
	}
	
	public void makeMove(Game game, Move move) {
		Point space = game.getSpace();
		TilePanel movingTile = tiles[move.x][move.y];
		TilePanel spaceTile = tiles[space.x][space.y];
		movingTile.makeMove(space);
		tiles[move.x][move.y] = spaceTile;
		tiles[space.x][space.y] = movingTile;
	}

	public void addListener(MyListener mouseListener) {
		for(int y = 0 ; y < height ; y++) {
			for(int x = 0 ; x < width ; x++) {
				tiles[x][y].addMouseListener(mouseListener);
			}
		}
	}

}
