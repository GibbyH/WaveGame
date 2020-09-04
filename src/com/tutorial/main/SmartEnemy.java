package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy (float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getID() == ID.Player) player = handler.objects.get(i);
		}		
	}
	
	public void tick () {
		x += velX;
		y += velY;
		
		float difX = x - player.getX() - 8;
		float difY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-1.0/distance) * difX);
		velY = (float) ((-1.0/distance) * difY);		
		
		
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.02f, handler));
	}
	
	public void render (Graphics g) {
		g.setColor(Color.orange);
		g.drawRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
}
