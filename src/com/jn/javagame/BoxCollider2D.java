package com.jn.javagame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BoxCollider2D extends Collider2D{

	public BoxCollider2D(Vector2 position, boolean isTrigger,ArrayList<Collider2D> colliders,Vector2 boundingBox) {
		super(position, isTrigger,colliders);
		this.boundingBox=boundingBox;

	}

	public Vector2 boundingBox;



	@Override
	public boolean isColliding(Collider2D collider) {
		CircleCollider2D circle=(CircleCollider2D)collider;
		if(circle!=null) {
			boolean flag=false;
			return flag;
		}
		BoxCollider2D box=(BoxCollider2D) collider;
		if(box!=null) {
			boolean flag=false;
			return flag;
		}
		
		return false;
	}

	@Override
	public Vector2 getBounds() {
		
		return boundingBox;
	}

	@Override
	public Vector2 getCenterPosition() {
		return Vector2.addVec2(position, Vector2.divide(boundingBox,2));
	}

}
