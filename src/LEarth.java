//package edu; 

import org.jbox2d.common.*;



class LEarth extends LBody {
    LEarth(luc in_parent){super(in_parent);}
  static Vec2 g = new Vec2(0,-9);
  
  void act(){
    //ApplyForce();
    //display();
}
  
  Vec2 getForce(Vec2 vecPoint){
    return g;
    
  }
}

