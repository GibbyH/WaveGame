package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;	
	protected ID id;
	protected float velX, velY;
	
	public GameObject (float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX () { return this.x; }	
	public void setX (int x) { this.x = x; }
	
	public float getY () { return this.y; } 	
	public void setY (int y) { this.y = y; }
	
	public ID getID () { return this.id; }
	public void setID (ID id) { this.id = id; }	
		
	public float getVelX () { return this.velX; }	
	public void setVelX (int velX) { this.velX = velX; }
	
	public float getVelY () { return this.velY; }	
	public void setVelY (int velY) { this.velY = velY; }
}
