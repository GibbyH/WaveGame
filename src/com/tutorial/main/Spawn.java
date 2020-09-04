package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;	
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn (Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick () {
		 scoreKeep++;
		 
		 if (scoreKeep >= 250) {
			 scoreKeep = 0;
			 hud.setLevel(hud.getLevel() + 1);
			 
			 if (hud.getLevel() % 2 == 0) {
				 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			 } 
			 if (hud.getLevel() % 3 == 0) {
				 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				 handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			 }
			 if (hud.getLevel() % 4 == 0) {
				 handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			 }
			 if (hud.getLevel() % 10 == 0) {
				 handler.clearEnemys();
				 handler.addObject(new BossEnemy_1((Game.WIDTH / 2), -100, ID.BossEnemy, handler));				 
			 }
			 if (hud.getLevel() % 10 == 4) {
				 for (int i = 0; i < handler.objects.size(); i++) {
					GameObject tempObject = handler.objects.get(i);
							
					if (tempObject.getID() == ID.BossEnemy) handler.removeObject(tempObject);
				}
			 }			 
		 }
	}
}
