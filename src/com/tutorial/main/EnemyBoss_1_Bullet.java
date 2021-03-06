package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss_1_Bullet extends GameObject {
	
	private Handler handler;
	private Random r = new Random();	

	public EnemyBoss_1_Bullet (float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - (-5)) + -5);
		velY = 5;
	}
	
	public void tick () {
		x += velX;
		y += velY;
		
//		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
//		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if (y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}
	
	public void render (Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
}
