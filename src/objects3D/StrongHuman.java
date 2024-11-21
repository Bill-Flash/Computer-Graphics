package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class StrongHuman extends Human {

	static float orange[] = { 0.8f, 0.2f, 0.3f, 1.0f, 1.0f };

	public StrongHuman() {
		super();
	}
	
	public void DrawHuman(float delta,boolean GoodAnimation, Texture pant, Texture shoe, Texture head, Texture cloth)
	 { 
		float theta = (float) (delta * 2 * Math.PI);
		  float LimbRotation;
		 if (GoodAnimation)
		 {
			 LimbRotation = (float) Math.cos(theta)*45;
		 }else
		 {
			 LimbRotation =0;
		 } 
		  
		Sphere sphere= new Sphere();
		Cylinder cylinder= new Cylinder();
		TexSphere texSphere= new TexSphere();


		 GL11.glPushMatrix(); 
		 
		 {
			  GL11.glTranslatef(0.0f,0.5f,0.0f);
	            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
	            GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			  // a globe pant for human

				GL11.glTexParameteri(
						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
						GL11.GL_CLAMP);
			  
				 Color.white.bind();
				    pant.bind();
				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
				  
				    texSphere.DrawTexSphere(0.5f, 32, 32, pant);
				    
				    GL11.glDisable(GL11.GL_TEXTURE_2D);
		            GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
		            GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
			 
			 //waist

		            GL11.glPushMatrix();{
			 			
			 			
		        //  chest
			 GL11.glColor3f(green[0], green[1], green[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
			 GL11.glPushMatrix(); {
		            GL11.glTranslatef(0.0f,0.7f,0.0f);
		            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
		            GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
		            
					GL11.glTexParameteri(
							GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
							GL11.GL_CLAMP);
				  
					 Color.white.bind();
					    cloth.bind();
					    GL11.glEnable(GL11.GL_TEXTURE_2D);    
					    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
					  
					    texSphere.DrawTexSphere(0.5f, 32, 32, cloth);
					    
					    GL11.glDisable(GL11.GL_TEXTURE_2D);

			            GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
			            GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
		            

		            // neck
		       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		           GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(0.0f,0.0f, 0.0f);
		                // CCW if positive from the rotated axis
		                GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
//		                                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f); 
		                cylinder.DrawCylinder(0.15f,0.7f,32);


		                // head
		           	 GL11.glColor3f(pink[0], pink[1], pink[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,1.0f);
//		                    GL11.glScalef(0.8f, 0.8f, 1.0f);
		                    GL11.glRotatef(Math.abs((LimbRotation)/3f), 1.0f, 0.0f, 0.0f);
		                    GL11.glRotatef(100, 0.0f, 0.8f, 1.3f);
		                    //head rotate, 1.5 times/per up and down
		                    GL11.glTexParameteri(
		    						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
		    						GL11.GL_CLAMP);
		    			  
		    				 Color.white.bind();
		    				    head.bind();
		    				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
		    				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
		    				  
		    				    texSphere.DrawTexSphere(0.5f, 32, 32, head);
		    				    
		    				    GL11.glDisable(GL11.GL_TEXTURE_2D);
			                    GL11.glRotatef(-100, 0.0f, 0.8f, 1.3f);
		                    //hair1
		                    GL11.glColor3f(spot[0], spot[1], spot[2]);
				               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(spot));
				                GL11.glPushMatrix(); {
				                    GL11.glTranslatef(0.0f,0.0f,0.0f);
				                    GL11.glScalef(0.1f, 0.1f, 1.0f);
				                    cylinder.DrawCylinder(0.15f,0.7f,32);
				                    //hair2
				                    GL11.glColor3f(spot[0], spot[1], spot[2]);
						               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(spot));
						                GL11.glPushMatrix(); {
						                    GL11.glTranslatef(0.0f,0.0f,0.0f);
						                    GL11.glRotatef(60.0f, 0.0f, 1.0f, 0.0f);
						                    cylinder.DrawCylinder(0.15f,1.3f,32); 
						                    
						                    GL11.glPopMatrix();}
						                //hair 3
						                GL11.glColor3f(spot[0], spot[1], spot[2]);
							               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(spot));
							                GL11.glPushMatrix(); {
							                    GL11.glTranslatef(0.0f,0.0f,0.0f);
							                    GL11.glRotatef(-60.0f, 0.0f, 1.0f, 0.0f);
							                    cylinder.DrawCylinder(0.15f,1.3f,32); 
							                    
							                    GL11.glPopMatrix();}
				                    GL11.glPopMatrix();}
				                    //nose
					           	 GL11.glColor3f(cyan[0], cyan[1], cyan[2]);
					               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(cyan));
					                GL11.glPushMatrix(); {
					                    GL11.glTranslatef(0.0f,0.4f,0.0f);
					                    GL11.glScalef(1.25f, 1.25f, 1.2f);
					                    sphere.DrawSphere(0.13f, 32, 32); 
					                    
					                    
					                    //left ear
					                    GL11.glColor3f(pink[0], pink[1], pink[2]);
							               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
							                GL11.glPushMatrix(); {
							                    GL11.glTranslatef(0.22f,-0.3f,0.1f);
							                    GL11.glScalef(1f, 1f, 0.83f);
							                    GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
							                    cylinder.DrawCylinder(0.15f, 0.2f, 32); 
							                    GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
							                    
							                    
							                    
							                    //left eye
							                    GL11.glColor3f(orange[0], orange[1], orange[2]);
									               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
									                GL11.glPushMatrix(); {
									                    GL11.glTranslatef(-0.06f,0.24f,0.05f);
									                    GL11.glScalef(1f, 1f, 0.8f);
									                    sphere.DrawSphere(0.14f, 32, 32); 
									                    
									                    				                    
									                    GL11.glPopMatrix();}
							                    GL11.glPopMatrix();}
							                //to nose
							                //right ear
						                    GL11.glColor3f(pink[0], pink[1], pink[2]);
								               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
								                GL11.glPushMatrix(); {
								                    GL11.glTranslatef(-0.22f,-0.3f,0.1f);
								                    GL11.glScalef(1f, 1f, 0.83f);
								                    GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
								                    cylinder.DrawCylinder(0.15f, 0.2f, 32); 
								                    GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
								                    
								                    
								                    
								                    //right eye
								                    GL11.glColor3f(orange[0], orange[1], orange[2]);
										               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
										                GL11.glPushMatrix(); {
										                    GL11.glTranslatef(0.06f,0.24f,0.05f);
										                    GL11.glScalef(1f, 1f, 0.8f);
										                    sphere.DrawSphere(0.14f, 32, 32); 
										                    
										                    				                    
										                    GL11.glPopMatrix();}
								                    GL11.glPopMatrix();}
								                //to nose
								                //mouth
							                    GL11.glColor3f(red[0], red[1], red[2]);
									               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
									                GL11.glPushMatrix(); {
									                    GL11.glTranslatef(0.0f,0.0f,-0.2f);
									                    GL11.glScalef(1f, 1f, 0.5f);
									                    sphere.DrawSphere(0.13f, 32, 32); 
									                    
									                    				                    
									                    GL11.glPopMatrix();}
					                    GL11.glPopMatrix();}
				                
		                    GL11.glPopMatrix();
		                } GL11.glPopMatrix();
		                //to chest


		                // left shoulder
		           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.5f,0.4f,0.0f);
		                    sphere.DrawSphere(0.25f, 32, 32); 
		                    

		                    // left arm
		               	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.0f);
		                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);  
		                        GL11.glRotatef(90.5f,0.0f,1.0f,0.0f);  
		                        GL11.glRotatef(-40.5f,0.0f,0.0f,1.0f); 

		                        
		                        // the separate angle
		                        GL11.glRotatef(LimbRotation-10,0.0f,1.0f,0.0f);
//		                        GL11.glRotatef(90.5f,1.0f,0.0f,0.0f); 
//		                        GL11.glRotatef(95.5f,0.0f,0.0f,1.0f);  
		                        cylinder.DrawCylinder(0.15f,0.7f,32);


		                        // left elbow
		                   	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.75f);
		                            // push the elbow down
			                        GL11.glRotatef(80.5f,0.0f,0.0f,1.0f);
			                        GL11.glRotatef(60.5f,-1.0f,0.0f,0.0f);
//			                        GL11.glRotatef(40.5f,0.0f,1.0f,0.0f);
			                        GL11.glRotatef(0.6f*LimbRotation-60,-1.0f,0.0f,0.0f); 
		                            sphere.DrawSphere(0.2f, 32, 32); 
		                            
		                            //left forearm
		                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.0f);
		                                               GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
//		                                GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
		                                cylinder.DrawCylinder(0.1f,0.7f,32);

		                                // left hand
		                           	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                                GL11.glPushMatrix(); {
		                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                    sphere.DrawSphere(0.2f, 32, 32); 
		                                    
		                                    
		                                 // left hand thumb
				                           	 GL11.glColor3f(red[0], red[1], red[2]);
				                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
				                                GL11.glPushMatrix(); {
				                                    GL11.glTranslatef(0.0f,-0.15f,0.05f);
				                                    GL11.glScalef(1.0f, 1.2f, 1.0f);
				                                    sphere.DrawSphere(0.1f, 32, 32); 
				                                    


				                                } GL11.glPopMatrix();
				                                // to hand
				                                // left hand index finger
					                           	 GL11.glColor3f(red[0], red[1], red[2]);
					                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
					                                GL11.glPushMatrix(); {
					                                    GL11.glTranslatef(0.0f,-0.1f,0.1f);
					                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
					                                    sphere.DrawSphere(0.1f, 32, 32); 
					                                    


					                                } GL11.glPopMatrix();
					                                // to hand
					                                // left hand middle finger
						                           	 GL11.glColor3f(red[0], red[1], red[2]);
						                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
						                                GL11.glPushMatrix(); {
						                                    GL11.glTranslatef(0.0f,-0.05f,0.13f);
						                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
						                                    sphere.DrawSphere(0.1f, 32, 32); 
						                                    


						                                } GL11.glPopMatrix();
						                                // to hand
						                                // left hand ring finger
							                           	 GL11.glColor3f(red[0], red[1], red[2]);
							                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
							                                GL11.glPushMatrix(); {
							                                    GL11.glTranslatef(0.0f,0.02f,0.14f);
							                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
							                                    sphere.DrawSphere(0.1f, 32, 32); 
							                                    


							                                } GL11.glPopMatrix();
							                                // to hand
							                                // left hand little finger
								                           	 GL11.glColor3f(red[0], red[1], red[2]);
								                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
								                                GL11.glPushMatrix(); {
								                                    GL11.glTranslatef(0.0f,0.1f,0.1f);
								                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
								                                    sphere.DrawSphere(0.1f, 32, 32); 
								                                    


								                                } GL11.glPopMatrix();
		                                } GL11.glPopMatrix();
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix ();
		                } GL11.glPopMatrix ();
		                // to chest

		                // right shoulder
			           	 GL11.glColor3f(blue[0],blue[1], blue[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(-0.5f,0.4f,0.0f);
			                    sphere.DrawSphere(0.25f, 32, 32); 
		           

		                    // right arm
				               	 GL11.glColor3f(orange[0], orange[1], orange[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                    GL11.glPushMatrix(); {
				                        GL11.glTranslatef(0.0f,0.0f,0.0f);
				                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
				                        GL11.glRotatef(-90.5f,0.0f,1.0f,0.0f); 
				                        GL11.glRotatef(40.5f,0.0f,0.0f,1.0f); 
				                        

//				                        
//				                        
//				                        GL11.glRotatef(-LimbRotation,1.0f,0.0f,0.0f);  
				                        GL11.glRotatef(-LimbRotation+10,0.0f,1.0f,0.0f); 
//				                        GL11.glRotatef(-25.5f,0.0f,0.0f,1.0f);  
				                        cylinder.DrawCylinder(0.15f,0.7f,32);


				                        // right elbow
					                   	 GL11.glColor3f(blue[0], blue[1], blue[2]);
					                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
					                        GL11.glPushMatrix(); {
					                            GL11.glTranslatef(0.0f,0.0f,0.75f);
					                            sphere.DrawSphere(0.2f, 32, 32); 
					                            // push the elbow down

						                        GL11.glRotatef(80.5f,0.0f,0.0f,-1.0f);
						                        GL11.glRotatef(60.5f,-1.0f,0.0f,0.0f);
						                        
//						                        GL11.glRotatef(20.5f,0.0f,0.0f,1.0f);

						                        GL11.glRotatef(0.6f*LimbRotation-60,-1.0f,0.0f,0.0f); 
//						                        GL11.glRotatef(-0.6f*LimbRotation-60,1.0f,0.0f,0.0f); 
					                            
					                            //right forearm
						                       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
						                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
						                            GL11.glPushMatrix(); {
						                                GL11.glTranslatef(0.0f,0.0f,0.0f);
						                                               GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
						                             //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
						                                cylinder.DrawCylinder(0.1f,0.7f,32);

						                                // right hand
							                           	 GL11.glColor3f(blue[0], blue[1], blue[2]);
							                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
							                                GL11.glPushMatrix(); {
							                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
							                                    sphere.DrawSphere(0.2f, 32, 32); 
							                                    
							                                    
								                                 // right hand thumb
										                           	 GL11.glColor3f(red[0], red[1], red[2]);
										                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
										                                GL11.glPushMatrix(); {
										                                    GL11.glTranslatef(0.0f,-0.15f,0.05f);
										                                    GL11.glScalef(1.0f, 1.2f, 1.0f);
										                                    sphere.DrawSphere(0.1f, 32, 32); 
										                                    


										                                } GL11.glPopMatrix();
										                                // to hand
										                                // right hand index finger
											                           	 GL11.glColor3f(red[0], red[1], red[2]);
											                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
											                                GL11.glPushMatrix(); {
											                                    GL11.glTranslatef(0.0f,-0.1f,0.1f);
											                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
											                                    sphere.DrawSphere(0.1f, 32, 32); 
											                                    


											                                } GL11.glPopMatrix();
											                                // to hand
											                                // right hand middle finger
												                           	 GL11.glColor3f(red[0], red[1], red[2]);
												                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
												                                GL11.glPushMatrix(); {
												                                    GL11.glTranslatef(0.0f,-0.05f,0.13f);
												                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
												                                    sphere.DrawSphere(0.1f, 32, 32); 
												                                    


												                                } GL11.glPopMatrix();
												                                // to hand
												                                // right hand ring finger
													                           	 GL11.glColor3f(red[0], red[1], red[2]);
													                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
													                                GL11.glPushMatrix(); {
													                                    GL11.glTranslatef(0.0f,0.02f,0.14f);
													                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
													                                    sphere.DrawSphere(0.1f, 32, 32); 
													                                    


													                                } GL11.glPopMatrix();
													                                // to hand
													                                // right hand little finger
														                           	 GL11.glColor3f(red[0], red[1], red[2]);
														                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
														                                GL11.glPushMatrix(); {
														                                    GL11.glTranslatef(0.0f,0.1f,0.1f);
														                                    GL11.glScalef(1.0f, 1.0f, 1.0f);
														                                    sphere.DrawSphere(0.1f, 32, 32); 
														                                    


														                                } GL11.glPopMatrix();
							                                } GL11.glPopMatrix();
							                            } GL11.glPopMatrix();
							                        } GL11.glPopMatrix();
							                    } GL11.glPopMatrix ();
							                } GL11.glPopMatrix ();
							                //chest
		            }GL11.glPopMatrix();
		            //waist


		            } GL11.glPopMatrix();


		            // pelvis

		            // left hip
		       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
		               
		                sphere.DrawSphere(0.25f, 32, 32); 


		                // left high leg
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                
		                    
		                    GL11.glRotatef((LimbRotation/1.3f)+90,1.0f,0.0f,0.0f); 
		                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
		                    cylinder.DrawCylinder(0.15f,0.7f,32);


		                    // left knee
		               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.75f);
		                        // constrain the angle of knee in case accident
		                        if (LimbRotation>0) {
		                        	GL11.glRotatef(-LimbRotation/1.5f, 1.0f, 0.0f, 0.0f);
		                        }
		                        sphere.DrawSphere(0.25f, 32, 32); 

		                        //left low leg
		                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.0f);
			                        if (LimbRotation>0) {
			                        	GL11.glRotatef(-LimbRotation*0.1f, 1.0f, 0.0f, 0.0f);
			                        }
		                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                            cylinder.DrawCylinder(0.15f,0.7f,32);

		                            // left foot
		                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                sphere.DrawSphere(0.3f, 32, 32);  
		                                
		                                
		                             // left shoe
				                       	 GL11.glColor3f(red[0], red[1], red[2]);
				                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
				                            GL11.glPushMatrix(); {
				                                GL11.glTranslatef(0.0f,-0.2f,0.14f);
				                                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
				                                GL11.glScalef(0.9f, 0.9f, 1.2f);
//				            					
				                				GL11.glTexParameteri(
				                						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				                						GL11.GL_CLAMP);
				                			  
				                				 Color.white.bind();
				                				    shoe.bind();
				                				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
				                				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
				                				  
				                				    texSphere.DrawTexSphere(0.3f, 32, 32, shoe);
				                				    GL11.glDisable(GL11.GL_TEXTURE_2D);

				                				    
					                              GL11.glTranslatef(0.0f,0.0f,-0.2f);
					                              GL11.glScalef(1.1f, 1.2f, 0.83f);
					              				GL11.glTexParameteri(
					            						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
					            						GL11.GL_CLAMP);
					            			  
					            				 Color.white.bind();
					            				    shoe.bind();
					            				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
					            				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
					            				  
					            				    texSphere.DrawTexSphere(0.3f, 32, 32, shoe);
					            				    GL11.glDisable(GL11.GL_TEXTURE_2D);

				                                
				                            } GL11.glPopMatrix();
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix();
		                } GL11.glPopMatrix();
		            } GL11.glPopMatrix();

		            // pelvis


		            // right hip
			       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
			           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			            GL11.glPushMatrix(); {
			                GL11.glTranslatef(0.5f,-0.2f,0.0f);
			               
			                sphere.DrawSphere(0.25f, 32, 32); 


			             // right high leg
				           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
				               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                GL11.glPushMatrix(); {
				                    GL11.glTranslatef(0.0f,0.0f,0.0f);
				                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
				                
				                    
				                    GL11.glRotatef((-LimbRotation/1.3f)+90,1.0f,0.0f,0.0f); 
				                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
				                    cylinder.DrawCylinder(0.15f,0.7f,32);


				                    // right knee
				               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                    GL11.glPushMatrix(); {
				                        GL11.glTranslatef(0.0f,0.0f,0.75f);
				                        // constrain the angle of knee in case accident
				                        if (LimbRotation<0) {
				                        	GL11.glRotatef(LimbRotation/1.5f, 1.0f, 0.0f, 0.0f);
				                        }
				                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
				                        sphere.DrawSphere(0.25f, 32, 32); 

				                        //right low leg
				                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
				                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                        GL11.glPushMatrix(); {
				                            GL11.glTranslatef(0.0f,0.0f,0.0f);
					                        if (LimbRotation<0) {
					                        	GL11.glRotatef(LimbRotation*0.1f, 1.0f, 0.0f, 0.0f);
					                        }
				                           // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
				                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
				                            cylinder.DrawCylinder(0.15f,0.7f,32);

				                            // right foot
				                       	 GL11.glColor3f(blue[0], blue[1], blue[2]);
				                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				                            GL11.glPushMatrix(); {
				                                GL11.glTranslatef(0.0f,0.0f,0.75f);
				                                sphere.DrawSphere(0.3f, 32, 32); 
				                                
				                                
					                             // right shoe
							                       	 GL11.glColor3f(red[0], red[1], red[2]);
							                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
							                            GL11.glPushMatrix(); {
							                                GL11.glTranslatef(0.0f,-0.2f,0.14f);
							                                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
							                                GL11.glScalef(0.9f, 0.9f, 1.2f);
							                				GL11.glTexParameteri(
							                						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
							                						GL11.GL_CLAMP);
							                			  
							                				 Color.white.bind();
							                				    shoe.bind();
							                				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
							                				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
							                				  
							                				    texSphere.DrawTexSphere(0.3f, 32, 32, shoe);
							                				    GL11.glDisable(GL11.GL_TEXTURE_2D);

							                				    
								                              GL11.glTranslatef(0.0f,0.0f,-0.2f);
								                              GL11.glScalef(1.1f, 1.2f, 0.83f);
									              				GL11.glTexParameteri(
									            						GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
									            						GL11.GL_CLAMP);
									            			  
									            				 Color.white.bind();
									            				    shoe.bind();
									            				    GL11.glEnable(GL11.GL_TEXTURE_2D);    
									            				    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST); 
									            				  
									            				    texSphere.DrawTexSphere(0.3f, 32, 32, shoe);
									            				    GL11.glDisable(GL11.GL_TEXTURE_2D);

							                                
							                                
							                            } GL11.glPopMatrix(); 
				                            } GL11.glPopMatrix();
				                        } GL11.glPopMatrix();
				                    } GL11.glPopMatrix();
				                } GL11.glPopMatrix();
				            } GL11.glPopMatrix();

				            // pelvis


				          
		        
		        } GL11.glPopMatrix();
		         
		    } 

		

		
	 }


}
