package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Utils;

public class GymTool {
		// secondary colours
		static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
		static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

		// other colours
		static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
		static float shadow[] = { 0.0f, 0.0f, 0.0f, 0.5f };

	public GymTool() {
		GL11.glPushMatrix(); 
		GL11.glPopMatrix();
	}
	
	public void DrawTool(float height) {
		
		Cylinder cylinder = new Cylinder();
		Sphere sphere = new Sphere();
		Cube cube = new Cube();
		 GL11.glPushMatrix(); 
		 
//		 	left bar
			 GL11.glColor3f(yellow[0], yellow[1], yellow[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(yellow));
			 cylinder.DrawCylinder(1, height, 10);


				GL11.glPushMatrix(); 
				GL11.glColor3f(orange[0], orange[1], orange[2]);
				 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				GL11.glTranslatef(0, 0, height);
				sphere.DrawSphere(0.8f, 10, 10);
				GL11.glPopMatrix();

				GL11.glPushMatrix(); 
					GL11.glTranslatef(0, 0, height-2);
					GL11.glRotatef(90, 0, 1, 0);
					 GL11.glColor3f(cyan[0], cyan[1], cyan[2]);
					 GL11.glMaterial(  GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(cyan));
					
					cylinder.DrawCylinder(0.5f, 15, 10);
				GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glTranslatef(7.5f,0f,2.5f);
			GL11.glRotatef(90,0,1,0);
			GL11.glTexParameteri(
					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T,
					GL11.GL_CLAMP);

			GL11.glColor4f(shadow[0], shadow[1], shadow[2], shadow[3]);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);

			GL11.glScalef(0,1,7.5f);
			cube.DrawCube();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();

//			right bar
			GL11.glTranslatef(15, 0, 0);
			GL11.glColor3f(yellow[0], yellow[1], yellow[2]);
			GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(yellow));
			cylinder.DrawCylinder(1, height, 10);
			
			GL11.glPushMatrix(); 
			GL11.glColor3f(orange[0], orange[1], orange[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			GL11.glTranslatef(0, 0, height);
			sphere.DrawSphere(0.8f, 10, 10);
			GL11.glPopMatrix();
			 
		 GL11.glPopMatrix();
		
	}

}
