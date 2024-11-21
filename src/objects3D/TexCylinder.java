package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.*;
import GraphicsObjects.Utils;

public class TexCylinder {

	
	public TexCylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments, Texture myTexture ) 
	{		
		float s, t;
		GL11.glBegin(GL11.GL_TRIANGLES);
		 for( float i = 0.0f; i < nSegments; i+= 1.0) {
			// calculate angles of the segment's two sides
			 float angle = (float) (Math.PI * i * 2.0 / nSegments);
			 float nextAngle = (float) (Math.PI * (i + 1) * 2.0 / nSegments);
			 // calculate coordinates of the segment's points
			 float x1 = (float) Math.sin(angle) * radius;
			 float y1 = (float) Math.cos(angle) * radius;
			 float x2 = (float) Math.sin(nextAngle) * radius;
			 float y2 = (float) Math.cos(nextAngle) * radius;
			 t = i / (nSegments-1);
//			 t = 1-t;
			 // calculate points of the segment
			 Point4f b1 = new Point4f(x1, y1, 0.0f, 0.0f);
			 Point4f b2 = new Point4f(x2, y2, 0.0f, 0.0f);
			 Point4f u1 = new Point4f(x1, y1, height, 0.0f);
			 Point4f u2 = new Point4f(x2, y2, height, 0.0f);
			 
//			 GL11.glTexCoord2d(t, 0);
			//set the normal vector of the lower triangle
			 GL11.glNormal3f(b2.x, b2.y, b2.z);	
			//set the three points of the lower triangle
			 GL11.glVertex3f(u2.x, u2.y, u2.z);	
			 
			 GL11.glTexCoord2d(t, 1);
			 GL11.glNormal3f(b1.x, b1.y, b1.z);
			 GL11.glVertex3f(b1.x, b1.y, b1.z); 
			 
			 GL11.glTexCoord2d(t, 1);	 
			 GL11.glNormal3f(b2.x, b2.y, b2.z);
			 GL11.glVertex3f(b2.x, b2.y, b2.z);
			 
			 GL11.glTexCoord2d(t, 1);
			//set the normal vector of the upper triangle	
			 GL11.glNormal3f(b1.x, b1.y, b1.z);	
			//set the three points of the upper triangle	
			 GL11.glVertex3f(b1.x, b1.y, b1.z);
			 
			 GL11.glTexCoord2d(t, 0);
			 GL11.glNormal3f(b2.x, b2.y, b2.z);	
			 GL11.glVertex3f(u2.x, u2.y, u2.z);
			 
			 GL11.glTexCoord2d(t, 0);		 		 
			 GL11.glNormal3f(b1.x, b1.y, b1.z);
			 GL11.glVertex3f(u1.x, u1.y, u1.z);
			 
			 
			 
			 
		 }
		 GL11.glEnd();
	}
}
