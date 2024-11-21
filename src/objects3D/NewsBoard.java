package objects3D;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class NewsBoard {
	boolean haveShadow = true;
	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float brown1[] = { 0.6f, 0.45f, 0.1f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	static float shadow[] = { 0.0f, 0.0f, 0.0f, 0.5f };


	public NewsBoard() {
		GL11.glPushMatrix(); 
		GL11.glPopMatrix();
	}

	public void setShadow(boolean b) {
		haveShadow = b;
	}

	public void DrawBoard(Texture news) {
		
		Cylinder cylinder = new Cylinder();
		Cube cube = new Cube();
		Sphere sphere = new Sphere();
		TexCube MyCube = new TexCube();
		 GL11.glPushMatrix(); 
		 
//		 	left bar
			 GL11.glColor3f(brown[0], brown[1], brown[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
			 GL11.glScalef(1, 1, 20);
			 cube.DrawCube();
			 GL11.glScalef(1, 1, 1/20f);
			 	GL11.glPushMatrix();
				 GL11.glTranslatef(0,1.4f,8.5f);
				 GL11.glRotatef(30,-1,0,0);
				 GL11.glScalef(0.3f,0.3f,1);
				 GL11.glColor3f(brown1[0], brown1[1], brown1[2]);
				 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown1));
				 cube.DrawCube();
				 GL11.glTranslatef(0,0f,1f);
				 GL11.glColor3f(red[0], red[1], red[2]);

				 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));

				 sphere.DrawSphere(1,10,10);

				 GL11.glPopMatrix();

				GL11.glPushMatrix(); 
				 //board
				 GL11.glTranslatef(16, 0, 10);
				 GL11.glScalef(16, 0.5f, 10);
				 GL11.glColor3f(brown1[0], brown1[1], brown1[2]);
				 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown1));
				 cube.DrawCube();

					GL11.glPushMatrix(); 
					GL11.glScalef(1/16f, 2f, 1/10f);
					GL11.glTranslatef(0, 0, 10);
					 GL11.glScalef(20, 2f, 4);
					 GL11.glColor3f(pink[0], pink[1], pink[2]);
					 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
					 cube.DrawCube();

					GL11.glPopMatrix();

					 GL11.glTranslatef(0, 0.35f, 0);
					 GL11.glScalef(10/16f, 1f, 6/10f);
					GL11.glTexParameteri(
							GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
							GL11.GL_CLAMP);
				  
					 Color.white.bind();
					    news.bind();
					    GL11.glEnable(GL11.GL_TEXTURE_2D);    
					    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 

						 MyCube.DrawTexCube(1);
					    
					    GL11.glDisable(GL11.GL_TEXTURE_2D);
					    
				GL11.glPopMatrix();

			 GL11.glTranslatef(32, 0, 0);
			 GL11.glColor3f(brown[0], brown[1], brown[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
			 GL11.glScalef(1, 1, 20);
			 cube.DrawCube();
			 GL11.glScalef(1, 1, 1/20f);

		GL11.glPushMatrix();
		GL11.glTranslatef(0,1.4f,8.5f);
		GL11.glRotatef(30,-1,0,0);
		GL11.glScalef(0.3f,0.3f,1);
		GL11.glColor3f(brown1[0], brown1[1], brown1[2]);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown1));
		cube.DrawCube();
		GL11.glTranslatef(0,0f,1f);
		GL11.glColor3f(red[0], red[1], red[2]);
		GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_DIFFUSE,  Utils.ConvertForGL(red));
		sphere.DrawSphere(1,10,10);

		GL11.glPopMatrix();
		 GL11.glPopMatrix();

		 if (haveShadow){

			 // shadow
			 GL11.glPushMatrix();
			 GL11.glTranslatef(0,0,-5.5f);
			 GL11.glRotatef(90, -1,0,0);
			 GL11.glTranslatef(0,0,-10);
			 GL11.glTexParameteri(
					 GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T,
					 GL11.GL_CLAMP);

			 GL11.glColor4f(shadow[0], shadow[1], shadow[2], shadow[3]);
			 GL11.glEnable(GL11.GL_TEXTURE_2D);
			 GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);

			 GL11.glPushMatrix();
			 GL11.glTranslatef(0,0,-10);
			 GL11.glScalef(1,0,20);
			 cube.DrawCube();
			 GL11.glPopMatrix();

			 GL11.glPushMatrix();
			 GL11.glTranslatef(15,0,-20);
			 GL11.glScalef(16,0,10);
			 cube.DrawCube();
			 GL11.glPopMatrix();

			 GL11.glPushMatrix();
			 GL11.glTranslatef(32,0,-10);
			 GL11.glScalef(1,0,20);
			 cube.DrawCube();
			 GL11.glPopMatrix();

			 GL11.glDisable(GL11.GL_TEXTURE_2D);

			 GL11.glPopMatrix();
		 }
	}

}
