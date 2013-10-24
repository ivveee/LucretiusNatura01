//package edu; 

import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;



class LLeaf extends LBasicBody {

  //float MaxRotter = 5.f;

  LLeaf(int pX, int pY,luc in_parent) {
           super(in_parent);
     MaxRotter = 5.f;
     rotter = MaxRotter;
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    //bd.linearDamping = 0.1f;
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 3.0f;
    fixtureDef.friction = 0.8f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0004;
    createBody(bd,fixtureDef,pX,pY);
    PhBody.setUserData(this);
    if (stability>0)
            PhBody.setActive(false);
  }



  void applyForce() {
    //Wind.GetForce();
    setForcesFromBodiesAffect();
    setRotter();
  }

public void display(){
    Luc.noStroke();
    Luc.fill(Luc.color(Luc.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    Luc.rect(vecPosition.x ,vecPosition.y+vecSize.y*(1-rotter/MaxRotter),vecSize.x,vecSize.y);
}

}

