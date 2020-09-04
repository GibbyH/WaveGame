package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter {

	private Game game;
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private Random r;
	
	public KeyInput (Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();
		r = new Random();
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			
			if (tempObject.getID() == ID.Player) {
				// Key events for player one.
				
				if (key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
				if (key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
				if (key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[2] = true; }
				if (key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[3] = true; }
			}
		}	
		
		if (key == KeyEvent.VK_P) {
			if (Game.paused) Game.paused = false;
			else Game.paused = true;
		}
		
		if (key == KeyEvent.VK_ESCAPE) {			
			if (game.gameState == STATE.Game) {
				handler.objects.clear();				
				game.gameState = STATE.Menu;				
			} else if (game.gameState == STATE.Menu) System.exit(1);
			
		}
	}
	
	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			
			if (tempObject.getID() == ID.Player) {
				// Key events for player one.
				
				if (key == KeyEvent.VK_W) keyDown[0] = false;
				if (key == KeyEvent.VK_S) keyDown[1] = false;
				if (key == KeyEvent.VK_D) keyDown[2] = false;
				if (key == KeyEvent.VK_A) keyDown[3] = false;
				
				if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}
