//package edu; 


import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;


class LWormPart extends LBasicBody{
 
  DistanceJoint BackJoint;
  boolean stable = false;
  LWormPart Head;
  
    LWormPart(int pX, int pY, luc in_parent) {
        super(in_parent);
        stability = 0;
     MaxRotter = 5.f;
    rotter = MaxRotter;
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    //bd.linearDamping = 0.1f;
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.1f;
    fixtureDef.friction = 0f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;

    bd.fixedRotation = true;
    bd.position.set(parent.box2d.coordPixelsToWorld(pX+parent.iPixDefaultHalfSize,pY+parent.iPixDefaultHalfSize));
    PhBody = parent.box2d.createBody(bd);
    
    CircleShape cs = new CircleShape();
    cs.m_radius = parent.box2d.scalarPixelsToWorld(parent.iPixDefaultHalfSize);
    fixtureDef.shape = cs;
    
    MassData md = new MassData();
    md.mass = 0.001f;
    md.center.setZero();
    PhBody.createFixture(fixtureDef);
    PhBody.setMassData(md);
    PhBody.setUserData(this);
  }
  
  @Override
  Vec2 getPosition() {
    Fixture fd = PhBody.getFixtureList();
    Vec2 g = parent.box2d.coordWorldToPixels(PhBody.getPosition());
    Vec2 ret = g.sub(getSize());
    return ret;
  }
  
  @Override
    Vec2 getSize() {
    Fixture fd = PhBody.getFixtureList();
    CircleShape thisSahpe =  (CircleShape) fd.getShape(); 
    float r = parent.box2d.scalarWorldToPixels(thisSahpe.getRadius());
    return new Vec2(r, r);
  }
  
  
  
  @Override
    void Display(){
    parent.noStroke();
    parent.fill(parent.color(parent.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    //ellipse(vecPosition.x,vecPosition.y,vecSize.x*2,vecSize.y*2);
    //if(stable)
      parent.rect(vecPosition.x,vecPosition.y,parent.iPixDefaultSize,parent.iPixDefaultSize);
    //else
    //  rect(vecPosition.x-1,vecPosition.y-2,iPixDefaultSize,iPixDefaultSize);
}


  @Override
    void ApplyForce()
    {
           //PhBody.applyForceToCenter(new Vec2(0,-3));
             setForcesFromBodiesAffect();

      if (BackJoint == null) return;      
      float CurrentJointLength = BackJoint.getLength();
      LWormPart wp = (LWormPart) BackJoint.getBodyB().getUserData(); 
      if(stable && !wp.stable){
        if(parent.box2d.scalarWorldToPixels(CurrentJointLength) >= getSize().x * 2)
          BackJoint.setLength(CurrentJointLength - 0.1f);
        else {
          wp.setStable();
        }
      }
      if(!stable && wp.stable){
         if(parent.box2d.scalarWorldToPixels(CurrentJointLength) <= getSize().x * 2.7)
          BackJoint.setLength(CurrentJointLength + 0.1f);
        else {
          setStable();
          wp.setUnStable();
        }     
    }
    

      
    
    }
    
    void setStable(){
      Fixture fd = PhBody.getFixtureList();
      CircleShape thisSahpe =  (CircleShape) fd.getShape();
      PhBody.destroyFixture(fd);
      //fd.setFriction(10);
    // fd.setDensity(0.5f);
          FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.5f;
    fixtureDef.friction = 10.f;
    fixtureDef.restitution = 0.2f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;
        CircleShape cs = new CircleShape();
    cs.m_radius = parent.box2d.scalarPixelsToWorld(parent.iPixDefaultHalfSize);
    fixtureDef.shape = cs;
      PhBody.createFixture(fixtureDef);
     // PhBody.setAngularDamping(1);
      stable = true;
      if (BackJoint == null) {Head.setUnStable();}
          MassData md = new MassData();
    md.mass = 0.4f;
    md.center.setZero();
        PhBody.setMassData(md);
    }
    
   void setUnStable(){
       Fixture fd = PhBody.getFixtureList();
       //      fd.setFriction(0);
     //fd.setDensity(0.1f);
      CircleShape thisSahpe =  (CircleShape) fd.getShape();
     PhBody.destroyFixture(fd);
          FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.1f;
    fixtureDef.friction = 0.f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;
        CircleShape cs = new CircleShape();
    cs.m_radius = parent.box2d.scalarPixelsToWorld(parent.iPixDefaultHalfSize);
    fixtureDef.shape = cs;
      PhBody.createFixture(fixtureDef);
      stable = false;
          MassData md = new MassData();
    md.mass = 0.1f;
    md.center.setZero();
    PhBody.setMassData(md);

    }
   

}
