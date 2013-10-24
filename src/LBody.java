/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package edu; 
import java.util.ArrayList;
import org.jbox2d.common.*;


interface IA{ void doA();}
interface IB extends IA{void doB();}

class cC implements IA{
   public void doA(){System.out.println("i am C");}
   //public void doA(){;}
}

class cD extends cC implements IB{ 
public void doB(){System.out.println("i am D");}
public void doA(){System.out.println("i am D");}
}

interface IVisible{
    void display();
}

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

