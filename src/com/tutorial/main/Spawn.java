package com.tutorial.main;

import java.util.Random;

public class Spawn {
	
	private Game game;
	private Handler handler;	
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn (Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick () {
		 scoreKeep++;
		 
		 if (scoreKeep >= 250) {
			 scoreKeep = 0;
			 hud.setLevel(hud.getLevel() + 1);
			 
			 if (game.diff == 0) {
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
			 } else if (game.diff == 1) {
				 if (hud.getLevel() % 2 == 0) {
					 handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					 handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
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
}
