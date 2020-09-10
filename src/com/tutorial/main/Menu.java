package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r;
	private HUD hud;
	
	public Menu (Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed (MouseEvent e) {
		r = new Random();
		
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			// Play button
			if (mouseOver(mx, my, 600, 350, 400, 64)) {
				//this.handler.objects.clear();
				//game.gameState = STATE.Game;
				//handler.addObject(new Player(Game.WIDTH / 2 -32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				
				game.gameState = STATE.Select;
				return;
			}
			
			// Help button
			if (mouseOver(mx, my, 600, 550, 400, 64)) {
				// TODO
				game.gameState = STATE.Help;
			}
			
			// Quit button
			if (mouseOver(mx, my, 600, 750, 400, 64)) {
				System.exit(0);
			}
		}		
		
		// Back button
		if (game.gameState == STATE.Help || game.gameState == STATE.End || game.gameState == STATE.Select) {
			if (mouseOver(mx, my, 600, 850, 400, 64)) {
				game.gameState = STATE.Menu;
				hud.setScore(0);
			}
		}
		
		if (game.gameState == STATE.Select) {
			// Mormal button
			if (mouseOver(mx, my, 600, 350, 400, 64)) {
				this.handler.objects.clear();
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 -32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				
				game.diff = 0;				
			}
			
			// Hard button
			if (mouseOver(mx, my, 600, 550, 400, 64)) {
				this.handler.objects.clear();
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 -32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
				
				game.diff = 1;	
			}
		}
		
	}
	
	public void mouseReleased (MouseEvent e) {
		
	}
	
	private boolean mouseOver (int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) return true;
			else return false;
		} else return false;		
	}
	
	public void tick () {
		
	}
	
	public void render (Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 728, 100);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(600, 350, 400, 64);
			g.drawString("Play", 775, 390);
			
			g.setColor(Color.white);
			g.drawRect(600, 550, 400, 64);
			g.drawString("Help", 780, 590);
			
			g.setColor(Color.white);
			g.drawRect(600, 750, 400, 64);
			g.drawString("Quit", 775, 790);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 728, 100);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("WASD to move player and dodge enemies.", 650, 400);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(600, 850, 400, 64);
			g.drawString("Back", 775, 890);			
		} else if (game.gameState == STATE.Select) {
				Font fnt = new Font("arial", 1, 50);
				Font fnt2 = new Font("arial", 1, 25);
				Font fnt3 = new Font("arial", 1, 15);
				
				g.setFont(fnt);
				g.setColor(Color.white);
				g.drawString("SelectDifficulty", 728, 100);
				
				g.setFont(fnt2);
				g.setColor(Color.white);
				g.drawRect(600, 350, 400, 64);
				g.drawString("Normal", 775, 390);
				
				g.setColor(Color.white);
				g.drawRect(600, 550, 400, 64);
				g.drawString("Hard", 780, 590);
				
				g.setFont(fnt2);
				g.setColor(Color.white);
				g.drawRect(600, 850, 400, 64);
				g.drawString("Back", 775, 890);
		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 728, 100);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("You made it to level: " + hud.getLevel(), 650, 400);
			g.drawString("With a score of: " + hud.getScore(), 650, 450);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(600, 850, 400, 64);
			g.drawString("Back", 775, 890);
		}
		
	}
}
