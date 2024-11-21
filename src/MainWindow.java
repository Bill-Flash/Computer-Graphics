
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.*; 

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean FightAnimation = false;
	private boolean PersistentAnimation = true;
	private boolean Earth = false;
	/**
	 * position of pointer
	 */
	float x = 400, y = 300;
	/**
	 * position of the man
	 */
	private float[] man = {500,90, 70};
	/**
	 * position of other objects
	 */
//	private float[] map = {1200,300,100};
	private List<float[]> map = new ArrayList<>();
	private Map<Integer, Boolean> exist = new HashMap<>();
	/**
	 * attack object
	 */
	private int attack = -1;
	/**
	 * angle of rotation
	 */
	float rotation = 0;
	/**
	 * time at last frame
	 */
	long lastFrame;
	/**
	 * frames per second
	 */
	int fps;
	/**
	 * last fps time
	 */
	long lastFPS;
	/**
	 * move direction and rotation
	 */
	int moveRight = 0, moveFront = 0, moveUp = 0;
	String direction = "forward";

	long myDelta = 0; //to use for animation
	float Alpha = 0; //to use for animation
	long StartTime; // beginAnimiation
	float FightTime = -100;
	float[] timer = new float[5];

	Arcball MyArcball = new Arcball();

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/**
	 * Mouse movement
	 */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball  X cord.
	float pullY = 0.0f; // arc ball  Y cord.


	//		int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project
	int OrthoNumber = 1200; // using this for screen size, making a window of 900 x 600 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project

	// basic colours
	static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
	static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

	static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
	static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

	// primary colours
	static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
	static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
	static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

	// secondary colours
	static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
	static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
	static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

	// other colours
	static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
	static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
	static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
	static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};
	static float shadow[] = {0.0f, 0.0f, 0.0f, 0.5f};

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 


	public void start() {

		StartTime = getTime();
		try {
//			Display.setDisplayMode(new DisplayMode(1200, 800));
			// for better view on my computer
			Display.setDisplayMode(new DisplayMode(900, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer


		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		rotation += 0.01f * delta;


		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();


		boolean MouseButonPressed = Mouse.isButtonDown(0);


//		  MyArcball.updateBall( (int) myDelta%100  , (int) myDelta%100  , 900, 600);
//		  System.out.println(myDelta/1000);
		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			//  System.out.println("Mouse drag mode");
//			  MyArcball.startBall( MouseX, MouseY, 1200, 800);
			MyArcball.startBall(MouseX, MouseY, 900, 600);
			dragMode = true;


		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
//			  MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
			MyArcball.updateBall(MouseX, MouseY, 900, 600);

//			  MyArcball.updateBall( delta  , delta  , 900, 600);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 10;

		}

		if (WheelPostion < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}

			System.out.println("Orth nubmer = " + OrthoNumber);

		}

		/** rest key is R*/
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		/* bad animation can be turn on or off using A key)*/


		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {

			moveRight -= 10;
			man[0] = 500 + moveRight;
			System.out.println("left");
			if (checkCollision()){
				moveRight += 10;
				man[0] = 500 + moveRight;
			}
			direction = "left";
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

			moveRight += 10;
			man[0] = 500 + moveRight;
			System.out.println("right");
			if (checkCollision()){
				moveRight -= 10;
				man[0] = 300 + moveRight;
			}
			direction = "right";
		}
//			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {

			moveFront += 10;
			man[1] = 90 + moveFront;
			System.out.println("forward");
			if (checkCollision()){
				moveFront -= 10;
				man[1] = 90 + moveFront;
			}
			direction = "forward";
		}
//			y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveFront -= 10;
			man[1] = 90 + moveFront;
			System.out.println("backward");
			if (checkCollision()){
				moveFront += 10;
				man[1] = 90 + moveFront;
			}
			direction = "backward";
		}
//			y -= 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			BadAnimation = !BadAnimation;
//			Earth=!Earth;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_J)){
			FightAnimation = true;
			attack = getCollision();
			exist.put(attack, true);
		}

		if (waitForKeyrelease) // check done to see if key is released
		{
			BadAnimation = false;
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;

				}
			}
		}

		 
		 /** to check if key is released */
		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false && !Keyboard.isKeyDown(Keyboard.KEY_A)
				 && !Keyboard.isKeyDown(Keyboard.KEY_S)&& !Keyboard.isKeyDown(Keyboard.KEY_D)
				 && !Keyboard.isKeyDown(Keyboard.KEY_W)){
				waitForKeyrelease=true;
		 }else{
			 BadAnimation = true;
				waitForKeyrelease=false;
		 }
		 
		 
		 
			

		// keep quad on the screen
//		if (x < 0)
//			x = 0;
//		if (x > 1200)
//			x = 1200;
//		if (y < 0)
//			y = 0;
//		if (y > 800)
//			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	private boolean checkCollision() {
		Iterator iterator = map.iterator();
		while (iterator.hasNext()){
			float[] x = (float[]) iterator.next();
			float dist = (x[0]-man[0])*(x[0]-man[0])+(x[1]-man[1])*(x[1]-man[1]);
			float required = (x[2]+man[2])*(x[2]+man[2]);
			if (dist<=required)
				return true;
		}
		return false;
	}

	private int getCollision() {
		Iterator iterator = map.iterator();
		while (iterator.hasNext()){
			float[] x = (float[]) iterator.next();
			float dist = (x[0]-man[0])*(x[0]-man[0])+(x[1]-man[1])*(x[1]-man[1]);
			float required = (x[2]+man[2]+5)*(x[2]+man[2]+5);
			if (dist<=required){
				int index = (int) x[3];
				iterator.remove();
				System.out.println(index);
				return index;
			}
		}
		return attack;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
//		MyArcball.startBall(0, 0, 1200,800); 
		MyArcball.startBall(0, 0, 900,600); 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		// change the sun's position
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(10000f).put(-1000).put(0).flip();
//		lightPos.put(10000f).put(1000f).put(1000).put(1).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(-1000f).put(0).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();
		
		FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
		lightPos5.put(-300f).put(400f).put(100f).put(1).flip();

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
//		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, Utils.ConvertForGL(spot)); 
		 GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange 

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
		 
		 // blue
		 GL11.glEnable(GL11.GL_LIGHT4); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT4, GL11.GL_DIFFUSE, Utils.ConvertForGL(blue));
		 GL11.glLight(GL11.GL_LIGHT4, GL11.GL_SPECULAR, lightPos5);

		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		   GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture

		//two trees
		map.add(new float[]{1200, 350, 100, 1});
		map.add(new float[]{1700, 350, 100, 2});
		//water
		map.add(new float[]{300, 0, 100, 3});
		//board
		map.add(new float[]{400, 2600, 400, 4});
		//strong man
		map.add(new float[]{1090, 1000, 300, 5});


		//exist animation done
		exist.put(1,false);
		exist.put(2,false);
		exist.put(3,false);
		exist.put(4,false);
		exist.put(5,false);


//		map.add(new float[]{1200, 300, 100});


	}

	 

	public void changeOrth() {

		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
//		  GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000); 
		 GL11.glOrtho(900 -  OrthoNumber , OrthoNumber, (600 - (OrthoNumber  * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);

		  GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		 	
		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
		 
		 //	if(MouseOnepressed)
		 //	{
		  
		 	MyArcball.getMatrix(CurrentMatrix);
		 //	}
		 	
		    GL11.glLoadMatrix(CurrentMatrix);
		 	
	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 */
	public void renderGL() { 
		changeOrth();

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		 myDelta =   getTime() - StartTime; 
		 float delta =((float) myDelta)/10000/2f; 

		  // code to aid in animation
		 float theta = (float) (delta * 2 * Math.PI);
		 float thetaDeg = delta * 360;
		 float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		 float posn_y  = (float) Math.sin(theta);
		// 500 400 90
		GLU.gluLookAt(500+moveRight, 400, 0+90+moveFront, 500+moveRight, 400+100, -350+moveFront,  0, 1, 0);

		//project 1 camera
//		if(delta%3 <= 0.5) {
//			float a = delta%3;
//			 GLU.gluLookAt(200, 300, 2000, 30+a*100, 400-a*100, 1800,  0, 1, 0);
//		}else if(delta%3 <= 1.1){
//			float a = (float) (delta%3 - 0.5);
//			 GLU.gluLookAt(200, 300, 2000, 250, 500-a*300, 1800,  0, 1, 0);
//		}else if(delta%3 <= 2.3) {
//			float a = (float) (delta%3 - 1.1);
//			 GLU.gluLookAt(500, 300, 100, 250, 340+a*100, 1000,  0, 1, 0);
//		}else if(delta%3 <= 2.5){
//			float a = (float) (delta%3 - 2.3);
//			 GLU.gluLookAt(500, 300, 100, 250+posn_x*100, 460+posn_y*100, 1000,  0, 1, 0);
//		}else if(delta%3 <= 3){
//			 GLU.gluLookAt(300, 400, 0, 290, 410, -200,  0, 1, 0);
//		}
			
//			System.out.println(delta);
		  /*
		   * This code draws a grid to help you view the human models movement 
		   *  You may change this code to move the grid around and change its starting angle as you please 
		   */
		if(DRAWGRID){
			GL11.glPushMatrix();
			Grid MyGrid = new Grid();
			GL11.glTranslatef(600, 400, 0); 
			GL11.glScalef(200f, 200f,  200f); 
			MyGrid.DrawGrid();
			GL11.glPopMatrix();
		}
		// ring road on the center
		

		Cylinder cylinder = new Cylinder();
		TexCylinder MyCylinder = new TexCylinder();
		TexCube MyCube = new TexCube();
		TexSphere globe = new TexSphere();
		GymTool tool = new GymTool();
		NewsBoard board = new NewsBoard();
		Tree tree = new Tree();
		

		// road
		GL11.glPushMatrix();
		GL11.glTranslatef(300, 255, 0);
		GL11.glScalef(90f, 50f, 90f);
		GL11.glColor3f(blue[0], blue[1], blue[2]);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_DIFFUSE, Utils.ConvertForGL(blue));
		cylinder.DrawCylinder(1, 1, 20);
		GL11.glColor3f(grey[0], grey[1], grey[2]);
		cylinder.DrawCylinder(5, 1, 20);
		GL11.glColor3f(white[0], white[1], white[2]);
		cylinder.DrawCylinder(5.2f, 1, 20);
		GL11.glColor3f(grey[0], grey[1], grey[2]);
		cylinder.DrawCylinder(7, 1, 20);

		// water
		GL11.glPushMatrix();
			GL11.glColor3f(blue[0], blue[1], blue[2]);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glRotatef(delta*1000, 0, 0, 1);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
			Color.white.bind();
			water.bind();
			MyCylinder.DrawCylinder(0.2f, 4, 20, water);
			GL11.glPopMatrix();
		GL11.glPopMatrix();

		// road 2
		GL11.glPushMatrix();
		GL11.glTranslatef(900, 255, -100);
		Color.gray.bind();
//		GL11.glColor3f(grey[0], grey[1], grey[2]);
		GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(0,1, 0);
		GL11.glVertex3f(0,0,0);
		GL11.glVertex3f(0,0,300);
		GL11.glVertex3f(1000,0,300);
		GL11.glVertex3f(1000,0,0);
		GL11.glEnd();
		GL11.glPopMatrix();
		
		// grass land
		GL11.glPushMatrix();
		GL11.glTranslatef(300, 75, 0);
		GL11.glScalef(490f, 90f, 490f);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		Color.white.bind();
		grass.bind();
		for(int j = 0; j < 3; j++) {
			GL11.glPushMatrix();
			for(int i = 0; i < 3; i++) {
				GL11.glPushMatrix();
				
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(4, 0, 4);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(0, 0, -4);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(-4, 0, 4);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(-4, 0, -8);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(4, 0, 0);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(4, 0, 0);
				
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(-8, 0, 4);
				MyCube.DrawTexCube(2);
				GL11.glTranslatef(0, 0, 4);
				MyCube.DrawTexCube(2);
				GL11.glPopMatrix();
				
				GL11.glTranslatef(0, 0, 12);
			}
			GL11.glPopMatrix();
			GL11.glTranslatef(12, 0, 0);
		}
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();
		GL11.glTranslatef(300, 345, 0);
		GL11.glScalef(90f, 90f, 90f);
		// decoration on the center
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_CLAMP);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		Color.white.bind();
		texture.bind();
		GL11.glTranslatef(0, 2, 0);
		if (exist.get(3)){
			if (timer[2] == 0.0f)
				timer[2] = delta;
			if (-(timer[3-1]-delta) < 0.15){
				GL11.glTranslatef((timer[2]-delta)*6, 0, 0);
			}else if(-(timer[2]-delta+0.15f)*8 <= 2){
				float tempX = 0.15f*6;
				GL11.glTranslatef((timer[2]-delta)*6, (timer[3-1]-delta+0.15f)*8, 0);
			}else{
				float tempX = (2/-(timer[2]-delta+0.15f)*8);
				GL11.glTranslatef(-2.43f, -2, 0);
			}
		}
		GL11.glRotatef(-thetaDeg, 1.0f, 0.0f, 0.0f);
		globe.DrawTexSphere(1, 100, 200, texture);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		
		// gym
		GL11.glPushMatrix();

		GL11.glTranslatef(0, 200, 1000);
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glScalef(25, 25, 25);
		tool.DrawTool(15);
		
		GL11.glTranslatef(18, 0, 0);
		tool.DrawTool(20);
		
		GL11.glTranslatef(18, 0, 0);
		tool.DrawTool(23);
		GL11.glPopMatrix();

		// trees
		GL11.glPushMatrix();
		GL11.glTranslatef(1200, 200, 350);
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glScalef(25, 25, 25);

		GL11.glPushMatrix();
		if (exist.get(1)){
			if (timer[0] == 0.0f)
				timer[0] = delta;
			if (-(timer[1-1]-delta)*400 < 60){
				GL11.glRotatef(-(timer[0]-delta)*400,1, 0, 0);
			}else{
				float tempX = (2/-(timer[0]-delta+0.15f)*8);
				GL11.glRotatef(60,1, 0, 0);
			}
		}
		tree.DrawTree(10);
		GL11.glPopMatrix();
		GL11.glTranslatef(20, 0, 0);
		if (exist.get(2)){
			if (timer[1] == 0.0f)
				timer[1] = delta;
			if (-(timer[1]-delta)*400 < 60){
				GL11.glRotatef(-(timer[1]-delta)*400,1, 0, 0);
			}else{
				float tempX = (2/-(timer[1]-delta+0.15f)*8);
				GL11.glRotatef(60,1, 0, 0);
			}
		}
		tree.DrawTree(15);

		GL11.glPopMatrix();
		
		// board for 2021
		GL11.glPushMatrix(); 
		
		GL11.glTranslatef(200, 400, 2000);
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glRotatef(30, 0, 0, -1);
		GL11.glScalef(25, 25, 25);
		if (exist.get(4)){
			if (timer[4-1] == 0.0f)
				timer[4-1] = delta;
			if (-(timer[4-1]-delta)*400 < 90){
				GL11.glRotatef(-(timer[4-1]-delta)*400,1, 0, 0);
			}else{
				GL11.glRotatef(90,1, 0, 0);
				board.setShadow(false);
			}
		}
		board.DrawBoard(sky);
		
		GL11.glPopMatrix();
		

		// walking people
		GL11.glPushMatrix();
		Sphere sphere = new Sphere();
		Human MyHuman = new Human();
		WalkingHuman MyHuman1 = new WalkingHuman();
		StrongHuman MyHuman2 = new StrongHuman();
		GL11.glTranslatef(300, 400, 0);
		GL11.glScalef(90f, 90f,  90f); 
		GL11.glPushMatrix();
		GL11.glTranslatef(200f/90f, 0,0);
		GL11.glTranslatef((float)moveRight/90f, (float)moveUp/90f, (float)moveFront/90f);
//		if(!BadAnimation)
//		{
			// insert your animation code to correct the position for the human rotating 
			// CW, if the value is negative, also at the same time the orientation of the robot
			// should be correct
			// A human is walking slowly and confidently
//			 GL11.glTranslatef(posn_x*4f, 0.0f, posn_y*4f);
//			 GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
//			 GL11.glRotatef(-thetaDeg, 0.0f, 1.0f, 0.0f);

	
		 
//		}else
//		{ 
			
			//bad animation  version 
//			 GL11.glTranslatef(posn_x*3.0f, 0.0f, posn_y*3.0f);
			 
//		}
		switch (direction){
			case "forward":
				GL11.glRotatef(180, 0,1,0);
				break;
			case "backward":
				break;
			case "left":
				GL11.glRotatef(90, 0,1,0);
				break;
			case "right":
				GL11.glRotatef(90, 0,-1,0);
				break;
		}
		if (FightAnimation){
			FightTime = delta * 4;
			MyHuman.setFightAnimationn(true, FightTime);
			FightAnimation = false;
		}else if( delta*4-FightTime < 3){
			MyHuman.setFightAnimationn(true, FightTime);
		}

		MyHuman.DrawHuman(4*delta,BadAnimation, home, shoe, head, cloth); // give a delta for the Human object ot be animated

		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T,
				GL11.GL_CLAMP);


		GL11.glColor4f(shadow[0], shadow[1], shadow[2], shadow[3]);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		GL11.glPushMatrix();
		float temp = (float) Math.cos((float) (delta * 2 * Math.PI) * 4);
		//shadow move
		if (BadAnimation){
			GL11.glTranslatef(0.5f,-1.5f,temp);
		}else {
			GL11.glTranslatef(0.5f,-1.5f,0);
		}
		GL11.glScalef(1, 0, 1.3f);
		sphere.DrawSphere(0.3f,10,10);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		if (BadAnimation){
			GL11.glTranslatef(-0.5f,-1.5f,-temp);
		}else {
			GL11.glTranslatef(-0.5f,-1.5f,0);
		}
		GL11.glScalef(1, 0, 1.3f);
		sphere.DrawSphere(0.3f,10,10);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0,-1.5f,0);
		GL11.glScalef(1.5f, 0, 1.1f);
		sphere.DrawSphere(0.5f,10,10);
		GL11.glPopMatrix();

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();

		thetaDeg /=2;
		posn_x = (float) Math.cos(theta/2); // same as your circle code in your notes 
		posn_y  = (float) Math.sin(theta/2);
		 
		GL11.glTranslatef(-posn_x*6f, 0.0f, posn_y*6f);
		GL11.glRotatef(-180, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(thetaDeg, 0.0f, 1.0f, 0.0f);
		MyHuman1.DrawHuman(delta,PersistentAnimation, water1, water1, head, basket); // give a delta for the Human object ot be animated

		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T,
				GL11.GL_CLAMP);

		GL11.glColor4f(shadow[0], shadow[1], shadow[2], shadow[3]);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f,-1.5f,posn_x);
		GL11.glScalef(1, 0, 1.3f);
		sphere.DrawSphere(0.3f,10,10);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f,-1.5f,-posn_x);
		GL11.glScalef(1, 0, 1.3f);
		sphere.DrawSphere(0.3f,10,10);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0,-1.5f,0);
		GL11.glScalef(1.5f, 0, 1.1f);
		sphere.DrawSphere(0.5f,10,10);
		GL11.glPopMatrix();

		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glPopMatrix();
		
		// a exercise man
		GL11.glPushMatrix();
		GL11.glTranslatef(280, 250, 1000); 
		GL11.glScalef(90f, 90f,  90f); 
		GL11.glTranslatef(9,3,0);
		posn_x = (float) Math.cos(theta)/3;
		GL11.glTranslatef(0.0f, -posn_x, 0);
		GL11.glRotatef(20, 1, 0, 0);
		if (exist.get(5)){
			PersistentAnimation = false;
			if (timer[5-1] == 0.0f)
				timer[5-1] = delta;
			if (-(timer[5-1]-delta)*120 < 60){
				GL11.glTranslatef(0, (timer[5-1]-delta)*1.5f, -(timer[5-1]-delta)*9);
				GL11.glRotatef((timer[5-1]-delta)*120, -1,0,0);
			}else{
				GL11.glTranslatef(0, -0.5f*1.5f, 0.5f*9);
				GL11.glRotatef(60, 1,0,0);
			}
		}
		MyHuman2.DrawHuman(delta,PersistentAnimation, water1, water1, head, basket);

		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(280, 260, 1000);
		GL11.glScalef(90,90,90);
		GL11.glTranslatef(9,0,0);
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T,
				GL11.GL_CLAMP);
		GL11.glColor4f(shadow[0], shadow[1], shadow[2], shadow[3]);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);

		GL11.glScalef(1.3f,0,1);
		sphere.DrawSphere(1, 10,10);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();


		
		/*
		 * This code puts the earth code in which is larger than the human so it appears to change the scene 
		 */
		if(Earth)
		{
			//Globe in the centre of the scene 
			GL11.glPushMatrix();
//			 TexSphere MyGlobe = new TexSphere();
//			TexCube MyGlobe = new TexCube();
			TexCylinder MyGlobe = new TexCylinder();
			GL11.glTranslatef(500, 500, 500 ); 
			// scale it to the sigsn and it is flat
			GL11.glScalef(10f, 10f,  10f);
			GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
//			GL11.glRotatef(-10, 0.0f, 1.0f, 0.0f);
			 
			GL11.glTexParameteri(
					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
					GL11.GL_REPEAT);
		  
			 Color.white.bind();
			    home.bind();
			    GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_LINEAR);
		        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   
	  
//		 	MyGlobe.DrawTexSphere(8f, 100, 100, texture); 
//			MyGlobe.DrawTexCube(); 
		    MyGlobe.DrawCylinder(10, 10, 200, texture);
			GL11.glPopMatrix();
		}
		
	}
 
	
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	Texture texture;
	Texture home;
	Texture shoe;
	Texture head;
	Texture cloth;
	Texture sky;
	Texture grass;
	Texture water;
	Texture basket;
	Texture water1;

	 
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {
	         
	  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
	  home = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/home.png"));
	  shoe = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/dots.png"));
	  head = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/head.png"));
	  cloth = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cloth.png"));
	  sky = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2021.png"));
	  grass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/grass.png"));
	  water = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/water.png"));
	  basket = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/basket.png"));
	  water1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/water1.png"));
	  System.out.println("Texture loaded okay ");
	}
}
