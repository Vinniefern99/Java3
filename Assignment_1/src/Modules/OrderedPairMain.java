package Modules;
import java.util.*;

public class OrderedPairMain
{

   public static void main (String[] args)
   {
      OrderedPair<Integer, Integer> intPr;
      OrderedPair<String, Double> mixedPr;
      
      intPr = new OrderedPair<Integer, Integer>(3, -59);
      mixedPr = new OrderedPair<String, Double>("teach", 21.96);
      
      System.out.println("Individual pairs: " + intPr + mixedPr);
      
      // we can't create arrays of generics, but we can create collections
      ArrayList<OrderedPair<String, Double> > mixedPrArray 
         = new ArrayList<OrderedPair<String, Double> >();
      
      // build the mixed pair array and show
      for (int k = 0; k < 10; k++)
      {
         mixedPrArray.add( 
            new OrderedPair<String, Double>("CS " + k , k/10.)
            );
      }
      System.out.println("Mixed Pair Array: "); 
      for (int k = 0; k < 10; k++)
         System.out.println( mixedPrArray.get(k));
   }
}


