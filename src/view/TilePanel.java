package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.Move;

public class TilePanel extends JPanel {

	private static final long serialVersionUID = -662251637153041228L;
	private int x, y;
	private final int n;
	private final GamePanel gp;
	private boolean moving = false;

	public TilePanel (int x, int y, int n, GamePanel gp) {
		setLayout(null);
		this.x = x;
		this.y = y;
		this.n = n;
		this.gp = gp;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (n>0) {
			if (!moving) {
				int scale = Math.min(gp.getWidth()/gp.width, gp.getHeight()/gp.height);
				setBounds(x*scale, y*scale, scale, scale);
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			if (n%2==0) g2.setColor(new Color(0xb36be7));
			else g2.setColor(new Color(0xecac1e));
			g2.fillRect(2, 1, getWidth()-3, getHeight()-2);
			g2.setColor(Color.BLACK);
			g2.drawRect(2, 2, getWidth()-4, getHeight()-4);
			g2.setFont(g2.getFont().deriveFont(40.0f));
			String s = "" + n;
			int stringLen = (int)g2.getFontMetrics().getStringBounds(s, g2).getWidth(); 
			g2.drawString(s, getWidth()/2 - stringLen/2, (int)(getHeight()*0.65));
		}
	}
	
	public void makeMove(Point move) {
		moving = true;
		int scale = Math.min(gp.getWidth()/gp.width, gp.getHeight()/gp.height);
		int step = scale/100;
		if (move.x > x) {
			for (int i = 0 ; i < scale ; i+=step) {
				moveTo(x*scale+i, y*scale);
			}
		} else if (move.x < x) {
			for (int i = 0 ; i < scale ; i+=step) {
				moveTo(x*scale-i, y*scale);
			}
		} else if (move.y > y) {
			for (int i = 0 ; i < scale ; i+=step) {
				moveTo(x*scale, y*scale+i);
			}
		} else  {
			for (int i = 0 ; i < scale ; i+=step) {
				moveTo(x*scale, y*scale-i);
			}
		}
		x = move.x;
		y = move.y;
		setBounds(x*scale, y*scale, scale, scale);
		moving = false;
		repaint();
	}

	private void moveTo(int x, int y) {
		setLocation(x, y);
		try {
			Thread.sleep(0, 500000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Move getMove() {
		return new Move(x, y);
	}
	
}
