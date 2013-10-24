import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

class LWood extends LBasicBody {
    //float stability;
LWood(int pX, int pY,luc in_body){
    super(in_body);
      rotter = maxRotter;
    //stability = 50000;//random(500,1500);
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    //bd.linearDamping = 0.1f;
    myBody = Luc.box2d.createBody(bd);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 1.1f;
    fixtureDef.friction = 10.0f;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits = 0x0004;
    createBody(bd,fixtureDef,pX,pY);
    myBody.setUserData(this);
        MassData md = new MassData();
    md.mass = 10f;
    md.center.setZero();
    myBody.setMassData(md);
    if (stability>0)
            myBody.setActive(false);
}

public void display(){
    Luc.noStroke();
    Luc.fill(Luc.color(Luc.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    Luc.rect(vecPosition.x ,vecPosition.y,vecSize.x,vecSize.y);
}


  void applyForce() {
      setForcesFromBodiesAffect();
  }
  
}
