package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Objects {
	ArrayList<Snake> ar;
	
	private static final  int UP = 1;
	private static final int DOWN = 2;
	private static final int RIGHT = 3;
	private static final int LEFT = 4;
	
	/*
	 * Variable Reference For Coordinate Of Head's Snake
	 * X and Y 
	 */
	private static int tmpX;
	private static int tmpY;
	
	/*
	 * variable checkResetGame is used when checkCollision happen
	 */
	private int checkResetGame;
	private int life;
	
	public Objects() {
		ar = new ArrayList<Snake>();
		
		this.checkResetGame = 0;
		this.life = 3;
		
		// add 4 snake when load game
		first();
	}
	
	/*
	 *  Graphic
	 */
	// Paint snake
	public void paintSnake(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		for(int i=0;i<ar.size();i++) {
			if(i==0) g2d.setColor(Color.GRAY);
			else g2d.setColor(Color.BLACK);
			g2d.fillRect(ar.get(i).getX(), ar.get(i).getY(), 
						 ar.get(i).getWidth(), ar.get(i).getHeight());
		}
	}
	
	// Paint number life of snake 
	public void paintLife(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.GRAY);
		
		int tmp = 280;
		for(int i=0;i<this.life;i++) {
			g2d.fillRect(tmp, 540, 20, 20);
			tmp+=25;
		}
	}
	
	
	/*
	 * Type of game
	 */
	// Border -> Snake cannot move through board
	public void border() {
		if(ar.get(0).getX()==0||ar.get(0).getX()==600
		|| ar.get(0).getY()==0||ar.get(0).getY()==520) {
			ar.removeAll(ar);
			first();
			this.checkResetGame=1;
			this.life--;
		}
	}
	
	// No Border -> Snake can move through board
	public void noborder() {
		if(tmpX==600)	tmpX = 20; 
		if(tmpX==0) tmpX = 580;
		if(tmpY==0) tmpY = 500;
		if(tmpY==520) tmpY = 20;
	}
	
	
	
	/*
	 * Method management
	 */
	
	// Move snake
	public void moveObjects(int direction) {
		switch(direction) {
			case UP: tmpY-=20; break;
			case DOWN: tmpY+=20; break;
			case RIGHT: tmpX+=20; break;
			case LEFT: tmpX-=20; break;
		}
		System.out.println(ar.get(0).getX() + " " + ar.get(0).getY());
	}	

	// Reset coordinate of snake in every (time) millisecond
	public void resetCoord() {
		for(int i=ar.size()-1;i>0;i--) {
			ar.get(i).setX(ar.get(i-1).getX());
			ar.get(i).setY(ar.get(i-1).getY());
		}
		ar.get(0).setX(tmpX);
		ar.get(0).setY(tmpY);
	}
	
	// Plus -> Add a body into snake when snake eats food
	public void plus() {
		add(new Snake(ar.get(ar.size()-1).getX(),
				ar.get(ar.size()-1).getY()));
	}
	
	public void reset() {
		ar.removeAll(ar);
		first();
		this.checkResetGame=0;
		this.life=3;
	}
	
	// Add first coordinate of snake, 4 body of snake
	public void first() {
		int tmp = 120;
		for(int i=0;i<4;i++) {
			ar.add(new Snake(tmp,260));
			tmp-=20;
		}
		tmpX = ar.get(0).getX();
		tmpY = ar.get(0).getY();
	}
	
	// Add new body of snake when eating food
	public void add(Snake snake) {
		ar.add(snake);
	}
	
	// Check collision between head and body of snake
	public void checkCollision() {
		for(int i=2;i<ar.size();i++) {
			if(Math.abs(ar.get(i).getX()-ar.get(0).getX())<=10&&
				Math.abs(ar.get(i).getY()-ar.get(0).getY())<=10) {
				ar.removeAll(ar);
				first();
				this.checkResetGame=1;
				this.life--;
			}
		}
	}
	
	
	/*
	 *  Getter, Setter
	 */
	public int getCheckResetGame() {
		return this.checkResetGame;
	}
	public void setCheckResetGame(int checkResetGame) {
		this.checkResetGame = checkResetGame;
	}
	public int getLife() {
		return this.life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
}
