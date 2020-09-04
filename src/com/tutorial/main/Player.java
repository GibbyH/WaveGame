package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	
	public Player (float x, float y, ID id, Handler handler) {
		super(x, y , id);
		this.handler = handler;
	}
	
	public void tick() {
		 x += velX;
		 y += velY;
		 
		 x = Game.clamp((int)x, 0, Game.WIDTH - 32);
		 y = Game.clamp((int)y, 0, Game.HEIGHT - 64);
		 
		 collision();
		 
		 handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
	}
	
	public void render (Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 32, 32);
	}	
	
	public void collision () {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SmartEnemy) {
				// Collision code
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
}
