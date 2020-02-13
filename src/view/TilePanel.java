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
	private int x, y, scale;
	private int n;

	public void setN(int n) {
		this.n = n;
	}

	public TilePanel (int x, int y, int n, int scale) {
		setLayout(null);
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.n = n;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (n>0) {
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
		if (move.x > x) {
			for (int i = 0 ; i < scale ; i++) {
				setLocation(x*scale+i, y*scale);
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (move.x < x) {
			for (int i = 0 ; i < scale ; i++) {
				setLocation(x*scale-i, y*scale);
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (move.y > y) {
			for (int i = 0 ; i < scale ; i++) {
				setLocation(x*scale, y*scale+i);
				
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else  {
			for (int i = 0 ; i < scale ; i++) {
				setLocation(x*scale, y*scale-i);
				
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		

		x = move.x;
		y = move.y;
		setBounds(x*scale, y*scale, scale, scale);
		repaint();
	}
	
	public Move getMove() {
		return new Move(x, y);
	}
	
}
