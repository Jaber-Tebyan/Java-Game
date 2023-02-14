package com.jn.javagame;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;




public class Game extends Canvas implements Runnable {

	private boolean debugMode=true;
	private Thread thread;
	private boolean running;
	public Vector2 direction=new Vector2();
	public static final int WIDTH=640*2,HEIGHT=WIDTH/12*8;
	public ArrayList<GameObject> gameObjects;
	public ArrayList<Collider2D> colliders;
	public Game() {
		new Window(WIDTH, HEIGHT, "My first Game", this);
		KeyAdapter adapter=new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				float right=0;
				float left=0;
				float up=0;
				float down=0;
				if(e.getKeyCode()==KeyEvent.VK_D) {
					right=1;
				}if(e.getKeyCode()==KeyEvent.VK_A) {
					left=1;
				}if(e.getKeyCode()==KeyEvent.VK_W) {
					up=-1;
				}if(e.getKeyCode()==KeyEvent.VK_S) {
					down=1;
				}
				direction=new Vector2(right-left,up-down);
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				float right=0;
				float left=0;
				float up=0;
				float down=0;
				if(e.getKeyCode()==KeyEvent.VK_D) {
					right=0;
				}if(e.getKeyCode()==KeyEvent.VK_A) {
					left=0;
				}if(e.getKeyCode()==KeyEvent.VK_W) {
					up=-0;
				}if(e.getKeyCode()==KeyEvent.VK_S) {
					down=0;
				}
				direction=new Vector2(right-left,up-down);

			}
		};
		addKeyListener(adapter);
		gameObjects=new ArrayList<>();
		colliders=new ArrayList<>();
		GameObject g=new GameObject(gameObjects).setShape(new Ellipse2D.Double(0,0,30,30)).setColor(Color.red).setDrawType(DrawType.FILL).setPosition(new Vector2(5, 6));
		Behaviour behaviour=new Behaviour() {
			
			double st=0;
			Vector2 velocity=new Vector2();
			double gravity=9.8;
			double gravityMult=10;
			float speed=300f;
			
			@Override
			public void Update(float deltaTime) {
				velocity.addTo(Vector2.multiply(direction,speed*deltaTime));
				g.position.y+=velocity.y*deltaTime;
				g.position.x+=velocity.x*deltaTime;
				velocity.y+=gravity*deltaTime*gravityMult;
				st+=deltaTime;

				
				
			}
			
			@Override
			public void Start() {
				// TODO Auto-generated method stub
				
			}
		};
		CircleCollider2D collider2d=new CircleCollider2D(g.position,true,colliders,15) {
			@Override
			public boolean isColliding(Collider2D collider) {
				boolean flag=super.isColliding(collider);
				if(flag) {
//					System.out.println(collider);
					System.out.println("CenterPosition1: "+getCenterPosition()+"     "+"CenterPosition2: "+collider.getCenterPosition());
					System.out.println("position: "+position+"     Position2: "+collider.position);
					CircleCollider2D circleCollider2D=(CircleCollider2D) collider;
					double distance=Vector2.distanceVec2(position, circleCollider2D.position);
					System.out.println(circleCollider2D.radius+"    "+radius+"       "+distance);
					stop();
					System.out.println("Colliding");
				}
				return flag;
			}
		};
		g.setCollider(collider2d);
		g.addBehaviour(behaviour);
		GameObject g2=new GameObject(gameObjects).setShape(new Rectangle2D.Double(0,0,WIDTH,20)).setColor(Color.yellow).setDrawType(DrawType.FILL).setPosition(0,HEIGHT-100);
		Rectangle2D rect=g2.shape.getBounds2D();
		g2.setCollider(new CircleCollider2D(g2.position, true,colliders,10));
		
	}

	private void tick(float deltaTime) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject _temp=gameObjects.get(i);
			
			for (int j = 0; j < _temp.behaviours.size(); j++) {
				
				Behaviour b=_temp.behaviours.get(j);
				b.Update(deltaTime);
				
			}
			for (int j = 0; j < colliders.size(); j++) {
				for (int j2 = 0; j2 < colliders.size(); j2++) {
					if(j==j2) {
						continue;
					}
					Collider2D a=colliders.get(j);
					Collider2D b=colliders.get(j2);
					a.isColliding(b);
				}
			}
			
		}
	}
	private void render() {
		BufferStrategy bf=getBufferStrategy();
		if(bf==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g=(Graphics2D)bf.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject _temp=gameObjects.get(i);
			g.setColor(_temp.color);
			g.drawImage(_temp.getImage(), (int)_temp.position.x, (int)_temp.position.y, this);
			if(debugMode) {
				g.setColor(Color.green);
				g.setStroke(new BasicStroke(3));
				g.draw(new Rectangle2D.Double(_temp.collider.position.x,_temp.collider.position.y,_temp.collider.getBounds().x,_temp.collider.getBounds().y));
				g.setColor(Color.blue);
				
				g.fill(new Ellipse2D.Double(_temp.collider.getCenterPosition().x-5/2,_temp.collider.getCenterPosition().y-5/2,5,5));

			}
			
		}
		g.dispose();
		bf.show();
	}

	@Override
	public void run() {
		
		final int FPS=200;
		final double PERIOD=(double)1e+9/FPS;
		long beginTime=System.nanoTime();
		double deltaTimeNano=0;
		long frames=0;
		long timer=System.currentTimeMillis();
		
		while(running) {
			
			deltaTimeNano+=(System.nanoTime()-beginTime);
			
			beginTime=System.nanoTime();
			while(deltaTimeNano>=PERIOD) {

				tick((float)(1.0/FPS));
				deltaTimeNano-=PERIOD;
				
				
			}
			if(running) {
				render();
			}
			frames++;
			if(System.currentTimeMillis()-timer>=1000) {
				timer=System.currentTimeMillis();
//				System.out.println("FPS: "+frames);
				frames=0;
			}
			
			
		}
		stop();
	}

	public static void main(String[] args) {
		new Game();
	}
	public synchronized void start() {
		thread=new Thread(this);
		
		thread.start();
		running=true;
	}
	public synchronized void stop() {
		try {
			thread.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		running=false;
	}

}
