package Modules;
import java.util.*;

public class UsingIteratorsMain
{
   public static void main (String[] args)
   {
      String[] words = {"When", "Harry", "Met", "Sally", 
         "X", "Wild", "Beast", "X", 
         "La", "Femme", "Nikita", "X", "Back", "To", 
         "The", "Future", "X"};
      
      ArrayList<String> myStrings = new ArrayList<String>();
      int k;
      ListIterator<String> p;
 
      // load up the list
      for (k = 0; k < words.length; k++)
         myStrings.add(words[k]);
     
      // print the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         System.out.print( p.next() + " " ); 
      System.out.println();
     
      // replace the "X"s with "AA"
      for (p = myStrings.listIterator(); p.hasNext();  )
         if ( p.next().equals("X") )
            p.set("AA");

      // print the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         System.out.print( p.next() + " " ); 
      System.out.println();

      // remove the "AA"s from the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         if ( p.next().equals("AA") )
            p.remove();

      // print the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         System.out.print( p.next() + " " ); 
      System.out.println();

      // print the list backwards (remember that p is
      // now at the end from the last loop
      while (p.hasPrevious())
         System.out.print( p.previous() + " ");
      System.out.println();
     
      // insert "24" into the list in position 3
      myStrings.add(3, "24");
 
      // print the list
      for (p = myStrings.listIterator(); p.hasNext();  )
         System.out.print( p.next() + " " ); 
      System.out.println();
   }
}