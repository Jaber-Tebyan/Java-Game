package com.jn.javagame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Collider2D {
	public boolean isTriiger;
	public GameObject gameObject;
	public Collider2D(Vector2 position,boolean isTrigger,ArrayList<Collider2D> colliders) {
		this.position=position;
		this.isTriiger=isTrigger;
		colliders.add(this);
	}
	Vector2 position;
	
	
	
	public abstract Vector2 getCenterPosition();
	public abstract boolean isColliding(Collider2D collider);
	public abstract Vector2 getBounds();

}
