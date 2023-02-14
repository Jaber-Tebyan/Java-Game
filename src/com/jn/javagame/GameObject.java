package com.jn.javagame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {

	public Shape shape;
	public Color color;
	public Vector2 position;
	public Collider2D collider;
	
	public ArrayList<Behaviour> behaviours=new ArrayList<>();
	DrawType drawType;
	
	public GameObject(ArrayList<GameObject> objects) {
		objects.add(this);
	}

	public GameObject setShape(Shape shape) {
		this.shape=shape;
		return this;
	}
	public GameObject setPosition(Vector2 position) {
		this.position=position;
		return this;
	}
	public GameObject setDrawType(DrawType drawType) {
		this.drawType=drawType;
		return this;
	}
	public GameObject setColor(Color color) {
		this.color=color;
		return this;
	}
	public GameObject addBehaviour(Behaviour behaviour) {
		this.behaviours.add(behaviour);
		behaviour.gameObject=this;
		return this;
	}
	public BufferedImage getImage() {
		BufferedImage bf=new BufferedImage((int)shape.getBounds2D().getWidth(), (int)shape.getBounds2D().getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g=(Graphics2D)bf.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		if(drawType==DrawType.DRAW) {
			g.draw(shape);
		}
		else if(drawType==DrawType.FILL) {
			g.fill(shape);
		}
		return bf;
	}
	public GameObject setCollider(Collider2D collider) {
		this.collider=collider;
		collider.gameObject=this;
		return this;
	}

	public GameObject setPosition(int i, int j) {
		
		return setPosition(new Vector2(i,j));
	}

}
enum DrawType{
	FILL,DRAW
}
