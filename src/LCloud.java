
import org.jbox2d.common.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.*;

class LCloud extends LBasicBody{
  //float stability = -1;
  //float MaxRotter = 0.5f;
  //float rotter;
  //boolean ConstructGround = false;
  
  LCloud(int pX, int pY, luc in_parent) {
      super(in_parent);
    MaxRotter = 0.5f;
    stability = -1;
    rotter = MaxRotter;
    //stability = random(500,1500);
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.linearDamping = 0.5f;
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 10.0f;
    fixtureDef.friction = 0.001f;
    //fixtureDef.restitution = 0.2;
    fixtureDef.filter.categoryBits = 0x0002;//0010 - I am a body
    fixtureDef.filter.maskBits = 0x0004;//0100 - I will collide with ground
    createBody(bd,fixtureDef,pX,pY);
    PhBody.setUserData(this);
    MassData md = new MassData();
    md.mass = 1;
    md.center.setZero();
    PhBody.setMassData(md);
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
