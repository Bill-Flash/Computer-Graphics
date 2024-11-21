package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{		
		GL11.glBegin(GL11.GL_TRIANGLES);
		 for( float i = 0.0f; i < nSegments; i+= 1.0) {
			 // for the rectangle, then divide into 2 triangles
			 float angle = (float) (Math.PI * i * 2.0f / nSegments);
			 float nextA = (float) (Math.PI * (i + 1) * 2.0f / nSegments);
			 
			 
			 // get the sin & cosine
			 float x1 = (float) ((float) radius * Math.sin(angle));
			 float y1 = (float) ((float) radius * Math.cos(angle));
			 float x2 = (float) ((float) radius * Math.sin(nextA));
			 float y2 = (float) ((float) radius * Math.cos(nextA));
			 
			 // get 4 points
			 Point4f v1 = new Point4f(x1, y1, height, 0.0f);
			 Point4f v2 = new Point4f(x2, y2, height, 0.0f);
			 Point4f v3 = new Point4f(x1, y1, 0.0f, 0.0f);
			 Point4f v4 = new Point4f(x2, y2, 0.0f, 0.0f);
			 

			 
			 
			 // the top triangle, with two high points
			 // for the normal part1
//			 Vector4f normal1 = v2.MinusPoint(v1).cross(v3.MinusPoint(v1)).Normal();			 
			 Vector4f normal1 = v1.MinusPoint(new Point4f(0.0f, 0.0f, height, 0.0f)).Normal();
			 GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
			 GL11.glVertex3f(v1.x, v1.y, v1.z);
//			 Vector4f normal2 = v1.MinusPoint(v2).cross(v3.MinusPoint(v2)).Normal();
//			 GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
			 GL11.glVertex3f(v2.x, v2.y, v2.z);
//			 Vector4f normal3 = v2.MinusPoint(v3).cross(v1.MinusPoint(v3)).Normal();
//			 GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
			 GL11.glVertex3f(v3.x, v3.y, v3.z);

			 // the bottom triangle, with two low points
			 // for the normal part2
//			 Vector4f normal2 = v3.MinusPoint(v4).cross(v2.MinusPoint(v4)).Normal();
//			 GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
//			 Vector4f normal4 = v2.MinusPoint(v4).cross(v3.MinusPoint(v4)).Normal();
			 Vector4f normal4 = v4.MinusPoint(new Point4f(0.0f, 0.0f, 0.0f, 0.0f)).Normal();

			 GL11.glNormal3f(normal4.x, normal4.y, normal4.z);
			 GL11.glVertex3f(v4.x, v4.y, v4.z);			 
//			 GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
			 GL11.glVertex3f(v2.x, v2.y, v2.z);
//			 GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
			 GL11.glVertex3f(v3.x, v3.y, v3.z);

			 
			 
			 // top face
			 // the normal line for top faces
			 Vector4f normal5 = v1.MinusPoint(new Point4f(0, 0, height, 0)).cross(v2.MinusPoint(new Point4f(0, 0, height, 0))).Normal();
			 GL11.glNormal3f(normal5.x, normal5.y, normal5.z);
			 GL11.glVertex3f(x1, y1, height);
			 GL11.glVertex3f(x2, y2, height);
			 GL11.glVertex3f(0, 0, height);

			 // bottom face
			 // the normal line for bottom faces
			 Vector4f normal6 = v3.MinusPoint(new Point4f(0, 0, 0, 0)).cross(v4.MinusPoint(new Point4f(0, 0, 0, 0))).Normal();
			 GL11.glNormal3f(normal6.x, normal6.y, normal6.z);
			 GL11.glVertex3f(x1, y1, 0);
			 GL11.glVertex3f(x2, y2, 0);
			 GL11.glVertex3f(0, 0, 0);
			 
			 
		 }
		 GL11.glEnd();
	}
}
