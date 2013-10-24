
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
abstract public class LComplexBody extends LBody {
    
    LComplexBody(luc in_parent){super(in_parent);}
    
   ArrayList <LBody> ar = new ArrayList<>();  
   
    @Override
    abstract void act();
}
