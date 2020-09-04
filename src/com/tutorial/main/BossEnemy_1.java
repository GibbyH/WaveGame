package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy_1 extends GameObject {
	
	private Handler handler;
	private int timer = 75;
	private int timer2 = 30;
	private Random r = new Random();

	public BossEnemy_1 (float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	
	public void tick () {
		x += velX;
		y += velY;
		
		if (timer <= 0) velY = 0;
		else timer--;
		
		if (timer <= 0) timer2--;
		if (timer2 <= 0) {
			if (velX == 0) velX = 3;
			int spawn = r.nextInt(10);
			if (spawn == 0) handler.addObject(new EnemyBoss_1_Bullet(x + 96, y + 96, ID.BasicEnemy, handler));
		}
		
		if (x <= 0 || x >= Game.WIDTH - 128) velX *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 128, 128, 0.02f, handler));
	}
	
	public void render (Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int)x, (int)y, 128, 128);
	}
	
	public Rectangle getBounds () {
		return new Rectangle((int)x, (int)y, 128, 128);
	}
}
