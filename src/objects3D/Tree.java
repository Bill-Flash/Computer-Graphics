package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

public class Tree {
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
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
    public Tree() {
        GL11.glPushMatrix();
        GL11.glPopMatrix();
    }

    public void DrawTree(float height) {

        Cylinder cylinder = new Cylinder();
        Sphere sphere = new Sphere();
        GL11.glPushMatrix();

        // bole
        GL11.glColor3f(brown[0], brown[1], brown[2]);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
        cylinder.DrawCylinder(1.3f, height, 30);

        // root
        GL11.glPushMatrix();
        GL11.glTranslatef(0,0, 3f);
        GL11.glRotatef(-180,0,1,0);
        // root1
            GL11.glPushMatrix();
            GL11.glRotatef(-60,0,1,0);
            cylinder.DrawCylinder(1f, 3, 30);
            GL11.glPopMatrix();
            // root 2
        GL11.glRotatef(50, 0,0,1);
        GL11.glPushMatrix();
        GL11.glRotatef(-60,0,1,0);
        cylinder.DrawCylinder(1f, 3, 30);
        GL11.glPopMatrix();
        // root 3
        GL11.glRotatef(100, 0,0,1);
        GL11.glPushMatrix();
        GL11.glRotatef(-60,0,1,0);
        cylinder.DrawCylinder(1f, 3, 30);
        GL11.glPopMatrix();
        // root 4
        GL11.glRotatef(120, 0,0,1);
        GL11.glPushMatrix();
        GL11.glRotatef(-60,0,1,0);
        cylinder.DrawCylinder(1f, 5, 30);
        GL11.glPopMatrix();
        // root 5
        GL11.glRotatef(40, 0,0,1);
        GL11.glPushMatrix();
        GL11.glRotatef(-60,0,1,0);
        cylinder.DrawCylinder(1f, 3, 30);
        GL11.glPopMatrix();

        GL11.glPopMatrix();

        GL11.glTranslatef(0,0,height);


        // first truck
        GL11.glPushMatrix();
        GL11.glRotatef(-30,0,1,0);
        GL11.glColor3f(brown[0], brown[1], brown[2]);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
        cylinder.DrawCylinder(1f, 6, 30);
            // top green
            GL11.glPushMatrix();
                GL11.glTranslatef(0, 0, 6);
                GL11.glColor3f(dkgreen[0], dkgreen[1], dkgreen[2]);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(dkgreen));
                sphere.DrawSphere(1.1f,20,20);
            GL11.glPopMatrix();
        GL11.glPopMatrix();

        // second truck
        GL11.glPushMatrix();
        GL11.glRotatef(-100,0,0,1);
        GL11.glRotatef(-45,0,1,0);
        GL11.glColor3f(brown[0], brown[1], brown[2]);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
        cylinder.DrawCylinder(1.3f, 6, 30);
            // top green
            GL11.glPushMatrix();
                GL11.glTranslatef(0, 0, 6);
                GL11.glColor3f(dkgreen[0], dkgreen[1], dkgreen[2]);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(dkgreen));
                sphere.DrawSphere(1.3f,20,20);
            GL11.glPopMatrix();
        GL11.glPopMatrix();
//
//        // third trunk
        GL11.glPushMatrix();
        GL11.glRotatef(-230,0,0,1);
        GL11.glRotatef(-55,0,1,0);
        GL11.glColor3f(brown[0], brown[1], brown[2]);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
        cylinder.DrawCylinder(1f, 6, 30);
            // top green
            GL11.glPushMatrix();
                GL11.glTranslatef(0, 0, 6);
                GL11.glColor3f(dkgreen[0], dkgreen[1], dkgreen[2]);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(dkgreen));
                sphere.DrawSphere(1.3f,20,20);
            GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glColor3f(brown[0], brown[1], brown[2]);
        cylinder.DrawCylinder(1.3f, height, 30);
        // top green
            GL11.glPushMatrix();
                GL11.glTranslatef(0, 0, height*0.9f);
                GL11.glColor3f(dkgreen[0], dkgreen[1], dkgreen[2]);
                GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(dkgreen));
                sphere.DrawSphere(4f,20,20);
            GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}
