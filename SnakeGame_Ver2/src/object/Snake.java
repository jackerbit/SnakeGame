package object;

public class Snake {
	private int x;
	private int y;
	private int height;
	private int width;
	
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		height = width = 20;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return this.x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return this.y;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return this.height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return this.width;
	}
}
