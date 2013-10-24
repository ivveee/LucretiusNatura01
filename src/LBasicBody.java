
import org.jbox2d.common.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.*;


abstract class LBasicBody extends LBody implements IVisible{
  LBasicBody(luc in_parent){super(in_parent);};
  float stability = 100000;
  
    Body PhBody;
    float MaxRotter = 10000;
    float rotter = MaxRotter;

    abstract void applyForce();
  LGround Rot;
  
  @Override
 public void display(){
    Luc.noStroke();
    Luc.fill(Luc.color(Luc.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    Luc.rect(vecPosition.x ,vecPosition.y+vecSize.y*(1-rotter/MaxRotter),vecSize.x,vecSize.y);
}
  
   void createBody(BodyDef bd,FixtureDef fixtureDef,float pX, float pY){
    bd.fixedRotation = true;
    bd.position.set(Luc.box2d.coordPixelsToWorld(pX+Luc.pixHalfSize,pY+Luc.pixHalfSize));
    PhBody = Luc.box2d.createBody(bd);
    PolygonShape PhShape = new PolygonShape();
    float bW = Luc.box2d.scalarPixelsToWorld(Luc.pixHalfSize);
    float bH = Luc.box2d.scalarPixelsToWorld(Luc.pixHalfSize);
    PhShape.setAsBox(bW, bH);
    fixtureDef.shape = PhShape;
    MassData md = new MassData();
    md.mass = 1;
    md.center.setZero();
    PhBody.createFixture(fixtureDef);
    PhBody.setMassData(md);
  }
  
  Vec2 getPosition() {
        Fixture fd = PhBody.getFixtureList();
    
    Vec2 g = Luc.box2d.coordWorldToPixels(PhBody.getPosition());
    Vec2 ret = g.sub(getSize().mul(0.5f));
    return ret;
  }

  void act(){
      display();
      applyForce();
  }
  
  Vec2 getSize() {
    Fixture fd = PhBody.getFixtureList();
    //if (fd == null) parent.println(this.getClass());
    PolygonShape thisSahpe =  (PolygonShape) fd.getShape(); 
    Vec2 v1 = Luc.box2d.coordWorldToPixels(thisSahpe.getVertex(0));
    Vec2 v2 = Luc.box2d.coordWorldToPixels(thisSahpe.getVertex(2));
    return new Vec2((v2.x-v1.x), (-v2.y+v1.y));
  }
  
  void setForcesFromBodiesAffect(){
    Vec2 SumForce = new Vec2(0,0);
    for (LBody oLForceBody: arBodiesAffect) {
      Vec2 f = oLForceBody.getForce(getPosition()); 
      stability -= (f.length());
      SumForce.addLocal(f); 
      if (stability<0){ 
              PhBody.setActive(true);
        PhBody.applyForceToCenter(SumForce);}
    }
  }
  
  void setRotter(){
    ContactEdge ce = PhBody.getContactList(); 
    while(ce != null){
      Contact c = ce.contact;
      Fixture f1 = c.getFixtureA();
      Fixture f2 = c.getFixtureB();
      Object o1 = f1.getBody().getUserData();
      Object o2 = f2.getBody().getUserData();
      if(o2.getClass() == LGround.class || o1.getClass() == LGround.class ){
        rotter-=Luc.GTimeStep;
        if(rotter <= 0){
          //ConstructGround = true;
          if(o2.getClass() == LGround.class)
            Rot = (LGround)o2;
          if(o1.getClass() == LGround.class)
            Rot = (LGround)o1;    
          Rot.rotterMass++;    
          Luc.All.removeBody(this);
        }
        break;
      }
      ce = ce.next;
    }        
  }
  
  
  @Override
    void destroy() {
      Luc.box2d.destroyBody(PhBody);
    }
  
  
}
