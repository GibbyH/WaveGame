package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private Thread thread;	
	private boolean running = false;	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public int diff = 0;
	public static boolean paused = false;
	public static final int WIDTH = 1600, HEIGHT = WIDTH / 12 * 9;
	public enum STATE {
		Menu,
		Select,
		Help,
		Game,
		Pause,
		End
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game () {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(this, handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Let's bulild a Game!", this);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		
		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 -32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		} else {
			for ( int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
		
	}
	
	public synchronized void start () {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop () {
		
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run () {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
	 	double delta = 0;
		long timer = System.currentTimeMillis();
		int frames= 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) 
				 render();
			frames++;
			 
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		 }
		 stop();
	}
	
	private void tick () {
		
		if (gameState == STATE.Game) {
			if (!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
				
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					
					handler.objects.clear();
					gameState = STATE.End;
					for ( int i = 0; i < 10; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}			
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
			handler.tick();
			menu.tick();			
		}		 
	}
	
	private void render () {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if (paused) {
			g.drawString("Paused", 200, 200);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp (float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}			
	}
	
	public static void main (String[] args) {
		new Game();
	}
}
