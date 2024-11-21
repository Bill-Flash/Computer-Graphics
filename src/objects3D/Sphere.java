package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		//the increments for the parametric equations
		float inctheta = (float) ((2.0f*Math.PI)/nSlices);
		float incphi = (float) ((Math.PI)/nSegments);
		
		//two loops to set slices and segments
		GL11.glBegin(GL11.GL_QUADS);
		for(float theta = (float) -Math.PI; theta<Math.PI; theta+=inctheta) {
			for(float phi = (float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi) {
			// rending a Sphere with 4 points (latitude, longitude) [Quads]
				// a
				float rphi = (float) (radius * Math.cos(phi));//the other circle
				float z = (float) (radius * Math.sin(phi));
				float x = (float) (rphi * Math.cos(theta));
				float y = (float) (rphi * Math.sin(theta));
				
				// b (the point higher than a
				float rphi1 = (float) (radius * Math.cos(phi+incphi));
				float z1 = (float) (radius * Math.sin(phi+incphi));
				float x1 = (float) (rphi1 * Math.cos(theta));
				float y1 = (float) (rphi1 * Math.sin(theta));
				
				// c (the point at the right of a
				float x2 = (float) (rphi * Math.cos(theta+inctheta));
				float y2 = (float) (rphi * Math.sin(theta+inctheta));
				
				// d (the point higher than c
				float x3 = (float) (rphi1 * Math.cos(theta+inctheta));
				float y3 = (float) (rphi1 * Math.sin(theta+inctheta));
				
				//the points on the sphere
				Point4f p = new Point4f(x, y, z, 0.0f);
				Point4f p1 = new Point4f(x1, y1, z1, 0.0f);
				Point4f p2 = new Point4f(x2, y2, z, 0.0f);
				Point4f p3 = new Point4f(x3, y3, z1, 0.0f);
				
				// the order of vertices matters!
				// a
				Vector4f normal = p.MinusPoint(new Point4f(0.0f, 0.0f, 0.0f, 0.0f)).Normal();
				GL11.glNormal3f(normal.x, normal.y, normal.z);
				GL11.glVertex3f(x, y, z);
				// b
				Vector4f normal1 = p1.MinusPoint(new Point4f(0.0f, 0.0f, 0.0f, 0.0f)).Normal();
				GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
				GL11.glVertex3f(x1, y1, z1);
				// d
				Vector4f normal3 = p3.MinusPoint(new Point4f(0.0f, 0.0f, 0.0f, 0.0f)).Normal();
				GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
				GL11.glVertex3f(x3, y3, z1);
				// c
				Vector4f normal2 = p2.MinusPoint(new Point4f(0.0f, 0.0f, 0.0f, 0.0f)).Normal();
				GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
				GL11.glVertex3f(x2, y2, z);

			}
		}
		GL11.glEnd();
		
	}
}

 