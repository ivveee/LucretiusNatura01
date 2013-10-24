//package edu; 
import java.util.ArrayList;
import org.jbox2d.common.*;

interface IVisible{
    void display();
}
//Кроме того всё то, что само по себе существует, 
//Действует или само, иль подвержено действию будет, 
//Иль будет тем, где вещам находиться и двигаться можно. 
interface IIsPhysical{
    Vec2 getPosition();
    Vec2 getSize();
    float getMass();
    float applyForce();
    float applyImpulse();  
}

abstract class LBody {
//Главное, к новым словам прибегать мне нередко придётся 
//При нищете языка и наличии новых понятий.
  luc Luc; 
  LBody(luc in_parent){Luc = in_parent;}
  ArrayList <LBody> arBodiesAffect = new ArrayList<>();
  Vec2 getForce(Vec2 input){return null;}
  Vec2 getImpulse(Vec2 input){return null;} 
  void destroy(){};
  abstract void act();//{;}
}

