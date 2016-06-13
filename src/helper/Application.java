package helper;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JFrame {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FPS = 60;
	public static final int DELAY = 1000/FPS;
	
	private Graphics graphic;
	private Board observer;
	

	private void initUI() {
		this.add(new Board(), BorderLayout.CENTER);
		this.pack();
		this.setResizable(false);
		// this.setSize(200,300);
		this.setTitle("Oakland University STEM CAMPS - Game Design - Flight Challenge");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public Application() {
		initUI();
		Application ex = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ex.setVisible(true);
				ex.pack();
			}
		});
	}

	public void init() {

	}

	public void update() {

	}
	
	public void draw(){
		
	}
	
	public void drawImage(Image image, int x, int y){
		this.graphic.drawImage(image, x, y, observer);
	}
	
	private void draw(Graphics g, Board observer) {
		this.graphic = g;
		this.observer = observer;
		this.draw();
	}


	class Board extends JPanel implements Runnable {

		private Thread animator;

		public Board() {
			setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(Application.WINDOW_WIDTH, Application.WINDOW_HEIGHT));
			setDoubleBuffered(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Application.this.draw(g, this);
			//Toolkit.getDefaultToolkit().sync();
		}

		public void addNotify() {
			super.addNotify();
			animator = new Thread(this);
			animator.start();
		}

		public void run() {
			long beforeTime, timeDiff, sleep;
			Application.this.init();
			while (true) {
				beforeTime = System.currentTimeMillis();
				Application.this.update();
				Toolkit.getDefaultToolkit().sync();
				repaint();
				timeDiff = System.currentTimeMillis() - beforeTime;
				sleep = DELAY - timeDiff;
				if(sleep < 1)
					sleep = 1;
				try{
					Thread.sleep(sleep);
				}catch(InterruptedException e){
					System.out.println("Interrupted:"+e.getMessage());
				}				
			}
		}

	}
}
