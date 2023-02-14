package com.jn.javagame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class CircleCollider2D extends Collider2D {

	public CircleCollider2D(Vector2 position, boolean isTrigger,ArrayList<Collider2D> colliders,float radius) {
		super(position, isTrigger,colliders);
		this.radius=radius;
	}

	public float radius;




	@Override
	public boolean isColliding(Collider2D collider) {
		CircleCollider2D circle=(CircleCollider2D)collider;
		if(circle!=null) {
			
			double squaredDistance=Vector2.squareDistanceVec2(circle.getCenterPosition(), getCenterPosition());
			
			
//			System.out.println("FirstCircleRadius: "+circle.radius+"     "+"secondCircleRadius: "+radius+"    addition: "+(radius+circle.radius));
//			System.out.println("SquaredDistance: "+squaredDistance+"    "+"Distance: "+Vector2.distanceVec2(circle.position, position)+"     "+"RadiusPow: "+((circle.radius+radius)*(circle.radius+radius)));
			boolean flag=(circle.radius+radius)*(circle.radius+radius)>=squaredDistance;
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
		return new Vector2(radius*2,radius*2);
	}

	@Override
	public Vector2 getCenterPosition() {
		return Vector2.addVec2(position,new Vector2(radius,radius));
	}
	
	
}
