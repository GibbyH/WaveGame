package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick () {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render (Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject (GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject (GameObject object) {
		this.objects.remove(object);
	}
	
	public void clearEnemys () {
		float tempX = 0.0f;
		float tempY = 0.0f;
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if (tempObject.getID() == ID.Player) {
				tempX = tempObject.getX();
				tempY = tempObject.getY();
			}
		}
		objects.clear();
		addObject(new Player(tempX, tempY, ID.Player, this));
	}
}
