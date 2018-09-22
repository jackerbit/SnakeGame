package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Food {
	private int x;
	private int y;
	private int height;
	private int width;
	private int score;
	
	private Random rd;
	
	public Food() {
		rd = new Random();
		score = 0;
		
		this.width = this.height = 20;
		
		randomFood();
	}

	/*
	 * Graphic
	 */
	
	// Paint Food
	public void paintFood(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.RED);
		g2d.fillRect(this.x, this.y, this.width, this.height);
	}
	
	/*
	 * Method Management
	 */
	
	// Random new coordinate of food when snake eats food
	public void randomFood() {
		this.x = 20 + rd.nextInt(560);
		this.y = 20 + rd.nextInt(480);
		while(this.x%20!=0) this.x++;
		while(this.y%20!=0) this.y++;
	}
	
	// Check collision between head of snake and food
	public void checkCollision(Objects obj) {
		if(Math.abs(obj.ar.get(0).getX()-this.x)==0&&
			Math.abs(obj.ar.get(0).getY()-this.y)==0) {
			obj.plus();
			randomFood();
			this.score+=10;
		}
	}
	
	
	/*
	 * Getter, Setter
	 */
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
