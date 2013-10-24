
import org.jbox2d.common.*;
class LWind extends LComplexBody {
  float ForceModule;// units per second
  float ForceAngle;
  float AngleDeviation = luc.PI*1.5f;
  float ForcePhase = luc.PI/2f;//radian per second
  Vec2 pos;
  int x;int y; int w; int h;
  float tStart;
  //float ForcePhaseDeviation
  LWind(float in_ForceModule, float in_ForceAngle, int in_x, int in_y, int in_w, int in_h,luc in_parent){
      super(in_parent);
    tStart = Luc.GTime;
    ForceModule = in_ForceModule;
    ForceAngle = in_ForceAngle;
    x = in_x;
    y = in_y;
    w = in_w;
    h = in_h;
  }
  @Override
  void act(){
    //ApplyForce();
    display();
}
 
  @Override
  Vec2 getForce(Vec2 vecPoint){
   // if(cos(t))
   if (!IsAffected(vecPoint)) return new Vec2(0,0);
   float CurrentAngle= ForceAngle + Luc.random(-AngleDeviation/2.f,AngleDeviation/2.f);
   float CurrentForcePhase = ForcePhase*(Luc.GTime-tStart);
   float CurrentForce = ForceModule * (luc.sin(CurrentForcePhase)+1.f)/2.f; 
    Vec2 f = new Vec2(CurrentForce*luc.sin(CurrentAngle),CurrentForce*luc.cos(CurrentAngle));
    return f;
  }
  
  void display(){
    //noFill();
    //stroke(0,0,0);
    //rect(x,y,x+w,y+h);
  }
  
  boolean IsAffected(Vec2 vecPoint){
    return ((vecPoint.x > x && vecPoint.x < (x+w)) && (vecPoint.y > y && vecPoint.y < (y+h)) );
  }
}