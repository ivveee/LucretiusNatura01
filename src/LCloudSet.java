
import java.util.Iterator;
import java.util.ArrayList;
import org.jbox2d.common.*;

class LCloudSet extends LBody{

ArrayList <LCloud> Clouds;
Vec2 SumForce = new Vec2(0,0); 
float stability = 1;
float mass;

void act(){
    ApplyForce();
    //display();
}

LCloudSet(ArrayList <LCloud> in_Clouds,luc in_parent){
    super(in_parent);
  mass = 200;//in_Clouds.length();
  Clouds = in_Clouds;
  for (Iterator<LCloud> activeIt = Clouds.iterator();activeIt.hasNext();) {
     LCloud Cloud = activeIt.next();
     Cloud.myBody.getFixtureList().getFilterData().categoryBits=0x0010;
     Cloud.arBodiesAffect.clear();
     Cloud.arBodiesAffect.add(this);
   }
}


void display(){
  for (Iterator<LCloud> activeIt = Clouds.iterator();activeIt.hasNext();) {
     LCloud Cloud = activeIt.next();
     //Cloud.Display();
   }
}
    
void ApplyForce(){  
  mass++;
    SumForce.set(0,0);
   Vec2 pos = new Vec2(0,0);
   //float OneByCloudSize = 1.0f/((float)Clouds.size());
   for (Iterator<LCloud> activeIt = Clouds.iterator();activeIt.hasNext();) {
     LCloud Cloud = activeIt.next();
     pos.addLocal(Cloud.getPosition());
    }    
    pos.mulLocal(1.0f/((float)Clouds.size()));
    
  for (LBody oLForceBody: arBodiesAffect) {
      Vec2 f = oLForceBody.getForce(pos); 
      stability -= (luc.sqrt(f.x*f.x + f.y*f.y));
      if (stability<0) {
        SumForce.addLocal(f);
      }
    }
  SumForce.addLocal(LEarth.g.negate());//Cloud flys
   for (Iterator<LCloud> activeIt = Clouds.iterator();activeIt.hasNext();) {
     LCloud Cloud = activeIt.next();
     if (mass>300){
       int i = (int) Luc.random(1,Clouds.size());
       if (i==1){ 
         Cloud.arBodiesAffect.remove(this);
         Cloud.arBodiesAffect.addAll(arBodiesAffect);
         Cloud.myBody.getFixtureList().getFilterData().categoryBits=0x0002;
         activeIt.remove();
       }
     }
    }
}

Vec2 getForce(Vec2 input)
{

  return SumForce;
}

  

}
