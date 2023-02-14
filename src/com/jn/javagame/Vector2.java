package com.jn.javagame;


public class Vector2 {

	public double x;
	public double y;
	public Vector2(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Vector2() {
		this.x=0;
		this.y=0;
	}
	public float getMagnitude() {
		return (float)Math.sqrt(x*x+y*y);
	}
	public float getSquareMagnitude() {
		return (float)(x*x+y*y);
	}
	public static Vector2 divide(Vector2 a,float b) {
		return new Vector2(a.x/b,a.y/b);
	}
	public static Vector2 subtractVec2(Vector2 a,Vector2 b) {
		return new Vector2(b.x-a.x,b.y-a.y);
	}
	public static Vector2 addVec2(Vector2 a,Vector2 b) {
		return new Vector2(b.x+a.x,b.y+a.y);
	}
	public static double distanceVec2(Vector2 a,Vector2 b) {
		return (float)Math.sqrt((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
	}
	public static Vector2 multiply(Vector2 a,float b) {
		return new Vector2(a.x*b,a.y*b);
	}
	public static double squareDistanceVec2(Vector2 a,Vector2 b) {
		return (float) ((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
	}
	public void addTo(Vector2 b) {
		this.x+=b.x;
		this.y+=b.y;
	}
	public void subtractTo(Vector2 b) {
		this.x-=b.x;
		this.y-=b.y;
	}
	public double distance(Vector2 b) {
		return distanceVec2(this, b);
		
	}
	public double squareDistance(Vector2 b) {
		return squareDistanceVec2(this, b);
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
