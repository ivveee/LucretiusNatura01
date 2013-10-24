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
     maxRotter = 5.f;
    rotter = maxRotter;
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    //bd.linearDamping = 0.1f;
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.1f;
    fixtureDef.friction = 0f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;

    bd.fixedRotation = true;
    bd.position.set(Luc.box2d.coordPixelsToWorld(pX+Luc.pixHalfSize,pY+Luc.pixHalfSize));
    myBody = Luc.box2d.createBody(bd);
    
    CircleShape cs = new CircleShape();
    cs.m_radius = Luc.box2d.scalarPixelsToWorld(Luc.pixHalfSize);
    fixtureDef.shape = cs;
    
    MassData md = new MassData();
    md.mass = 0.001f;
    md.center.setZero();
    myBody.createFixture(fixtureDef);
    myBody.setMassData(md);
    myBody.setUserData(this);
  }
  
  @Override
  Vec2 getPosition() {
    Fixture fd = myBody.getFixtureList();
    Vec2 g = Luc.box2d.coordWorldToPixels(myBody.getPosition());
    Vec2 ret = g.sub(getSize());
    return ret;
  }
  
  @Override
    Vec2 getSize() {
    Fixture fd = myBody.getFixtureList();
    CircleShape thisSahpe =  (CircleShape) fd.getShape(); 
    float r = Luc.box2d.scalarWorldToPixels(thisSahpe.getRadius());
    return new Vec2(r, r);
  }
  
  
  
  @Override
  public void display(){
    Luc.noStroke();
    Luc.fill(Luc.color(Luc.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    //ellipse(vecPosition.x,vecPosition.y,vecSize.x*2,vecSize.y*2);
    //if(stable)
      Luc.rect(vecPosition.x,vecPosition.y,Luc.pixSize,Luc.pixSize);
    //else
    //  rect(vecPosition.x-1,vecPosition.y-2,iPixDefaultSize,iPixDefaultSize);
}


 // @Override
    void applyForce()
    {
           //PhBody.applyForceToCenter(new Vec2(0,-3));
             setForcesFromBodiesAffect();
      if (BackJoint == null) return;

      //parent.text(f.length(),100,100);
      float CurrentJointLength = BackJoint.getLength();
      LWormPart wp = (LWormPart) BackJoint.getBodyB().getUserData(); 
            
      Vec2 f = new Vec2();
      BackJoint.getReactionForce(Luc.GTimeStep, f);
      if (f.length()>2.f) {
          Luc.box2d.world.destroyJoint(BackJoint);
          DistanceJoint current =  BackJoint;
          LWormPart wpNext = wp;
          while(current != null){
              wpNext = (LWormPart) current.getBodyB().getUserData();      
              current = wpNext.BackJoint;
          }
          Head = wpNext.Head;
          wpNext.Head = wp;
          return;}
      
      if(stable && !wp.stable){
        if(Luc.box2d.scalarWorldToPixels(CurrentJointLength) >= getSize().x * 2.3)
          BackJoint.setLength(CurrentJointLength - 0.1f);
        else {
          wp.setStable();
        }
      }
      if(!stable && wp.stable){
         if(Luc.box2d.scalarWorldToPixels(CurrentJointLength) <= getSize().x * 2.9)
          BackJoint.setLength(CurrentJointLength + 0.1f);
        else {
          setStable();
          wp.setUnStable();
        }     
    }
    

      
    
    }
    
    void setStable(){
      Fixture fd = myBody.getFixtureList();
      CircleShape thisSahpe =  (CircleShape) fd.getShape();
      myBody.destroyFixture(fd);
      //fd.setFriction(10);
    // fd.setDensity(0.5f);
          FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.5f;
    fixtureDef.friction = 10.f;
    fixtureDef.restitution = 0.2f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;
        CircleShape cs = new CircleShape();
    cs.m_radius = Luc.box2d.scalarPixelsToWorld(Luc.pixHalfSize);
    fixtureDef.shape = cs;
      myBody.createFixture(fixtureDef);
     // PhBody.setAngularDamping(1);
      stable = true;
      if (BackJoint == null) {Head.setUnStable();}
          MassData md = new MassData();
    md.mass = 0.4f;
    md.center.setZero();
        myBody.setMassData(md);
    }
    
   void setUnStable(){
       Fixture fd = myBody.getFixtureList();
       //      fd.setFriction(0);
     //fd.setDensity(0.1f);
      CircleShape thisSahpe =  (CircleShape) fd.getShape();
     myBody.destroyFixture(fd);
          FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 0.1f;
    fixtureDef.friction = 0.f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0006;
        CircleShape cs = new CircleShape();
    cs.m_radius = Luc.box2d.scalarPixelsToWorld(Luc.pixHalfSize);
    fixtureDef.shape = cs;
      myBody.createFixture(fixtureDef);
      stable = false;
          MassData md = new MassData();
    md.mass = 0.1f;
    md.center.setZero();
    myBody.setMassData(md);

    }
   

}
