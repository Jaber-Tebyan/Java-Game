package com.jn.javagame;

public abstract class Behaviour {
	public Behaviour() {
		this.gameObject=gameObject;
	}
	public GameObject gameObject;
	public abstract void Start();
	public abstract void Update(float deltaTime);
	
}
