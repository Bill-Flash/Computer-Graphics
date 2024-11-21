package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	
	public TexCube() {

	}
	
	// Implement using notes  and looking at TexSphere
	public void DrawTexCube(float length) 
 {
		// It has 8 vertices in total.
//		Point4f vertices[] = {	new Point4f(-1.0f,-1.0f,-1.0f,0.0f),
//								new Point4f(-1.0f,-1.0f,1.0f,0.0f),
//								new Point4f(-1.0f,1.0f,-1.0f,0.0f),
//								new Point4f(-1.0f,1.0f,1.0f,0.0f),
//								new Point4f(1.0f,-1.0f,-1.0f,0.0f),
//								new Point4f(1.0f,-1.0f,1.0f,0.0f),
//								new Point4f(1.0f,1.0f,-1.0f,0.0f),
//								new Point4f(1.0f,1.0f,1.0f,0.0f) };
		
		Point4f vertices[] = {	new Point4f(-length,-length,-length,0.0f),
				new Point4f(-length,-length,length,0.0f),
				new Point4f(-length,length,-length,0.0f),
				new Point4f(-length,length,length,0.0f),
				new Point4f(length,-length,-length,0.0f),
				new Point4f(length,-length,length,0.0f),
				new Point4f(length,length,-length,0.0f),
				new Point4f(length,length,length,0.0f) };
		// the connection constructs per face using every 4 vertices
		int[][] faces = {	{ 0, 1, 3, 2},
							{0, 2, 6, 4},
							{0, 1, 5, 4},
							{1, 5, 7, 3},
							{5, 4, 6, 7},
							{2, 3, 7, 6}};
		
		GL11.glBegin(GL11.GL_QUADS);
		for(int face = 0; face < 6; face++) {// per face - 6
			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]); 
			Vector4f w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();
			
			// for s, t
			GL11.glTexCoord2f(0, 0);
		 	GL11.glNormal3f(normal.x, normal.y, normal.z);
		 	
			GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
		}	// per face - 6
		GL11.glEnd();

	 
		
		

	}
	
	
	
}
 
	/*
	 
	 
}

	*/
	 