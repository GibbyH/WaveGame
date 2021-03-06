package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private Handler handler;
	private Random r = new Random();
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	private Color col;
	private int dir = 0;
	
	public MenuParticle (float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		dir = r.nextInt(2);
		
		if (dir == 0) {
			velX = 2;
			velY = 7;
		} else {
			velX = 7;
			velY = 2;
		}
		
		col = new Color(red, green, blue);
	}
	
	public void tick () {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, col, 8, 8, 0.02f, handler));
	}
	
	public void render (Graphics g) {
		g.setColor(col);
		g.drawRect((int)x, (int)y, 8, 8);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 8, 8);
	}
}



