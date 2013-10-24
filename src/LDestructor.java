/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package edu; 
import java.util.ArrayList;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

/**
 *
 * @author Administrator
 */
class LDestructor extends LBody {
  Vec2 force = new Vec2();
  Vec2 impulse = new Vec2();  

  void act(){
    //ApplyForce();
    //display();
}
  
  LDestructor(Vec2 in_force, Vec2 in_impulse, luc in_parent){
      super(in_parent);
    force = in_force;
    impulse = in_impulse;
  }
  
  Vec2 getForce(Vec2 vecPoint){
    Vec2 dist = new Vec2(Luc.screenWidth/2 - vecPoint.x,  Luc.screenHeight/2 - vecPoint.y);
    Vec2 norm = new Vec2(Luc.screenWidth/2 - vecPoint.y,  Luc.screenHeight/2 - vecPoint.x);
    Vec2 normalizeDist = dist.mul(1.0f/dist.length());
    Vec2 normalizeNorm = norm.mul(-1.0f/norm.length());
    Vec2 diff = normalizeNorm.sub(normalizeDist) ;
    float f = 200;
    Vec2 vecForce = new Vec2(diff.x*f,diff.y*f );
    return vecForce;
  }
  
  Vec2 getImpulse(Vec2 vecPoint){
    Vec2 dist = new Vec2(-Luc.screenWidth/2 + vecPoint.x, - Luc.screenHeight/2 + vecPoint.y);
    Vec2 normalize = dist.mul(1.0f/dist.length());
    float v = 50.f;
    Vec2 vecImpulse = new Vec2(normalize.y*v,normalize.x*v);
    return vecImpulse;
  }
  
}
/*
class RayCastAll implements RayCastCallback {

  ArrayList <LGround> Grounds = new ArrayList <LGround>();

  public float reportFixture(Fixture A,Vec2 Av,Vec2 B,float f ) {
    Body b = A.getBody();
    Object o = b.getUserData();
    if (o == null)return true;
    if (o.getClass() == LGround.class ) {
      Grounds.add((LGround) o);
    }
    return true;
  }
}*/
