package modules_2;

import java.util.*;
import cs_1c.*;

public class FHLinkedListMain
{
   public static void main (String[] args)
   {
      int k;
      FHlinkedList<Integer> fhList = new FHlinkedList<Integer>();

      fhList.addLast(1);
      fhList.addLast(2);
      fhList.addLast(3);
      fhList.addLast(4);
      fhList.addLast(5);
      fhList.addLast(6);

      fhList.addFirst(0);
      fhList.addFirst(-1);
      fhList.addFirst(-2);
      fhList.addFirst(-3);

      fhList.set( fhList.size()/2, -99);
      
      System.out.println( "List size: " + fhList.size() );
      System.out.println( "First val: " + fhList.get(0) );
      System.out.println( "Middle val: " + fhList.get(fhList.size()/2) );
      System.out.println( "Last val: " + fhList.get( fhList.size() - 1) );

      System.out.println( "List size: " + fhList.size() );
      for (k = 0; k < 4; k++)
      {
         System.out.println( fhList.getFirst() + " " + fhList.getLast() );
         fhList.removeFirst();
      }
      System.out.println("\n" + fhList.size() );
      for (k = 0; k < 12; k++)
      {
         try
         {
            System.out.println( fhList.getFirst() + " " + fhList.removeLast() );
         }
         catch ( NoSuchElementException e)
         {
            System.out.println("Caught Ex. at k = " + k + ".  Empty list.");
         }
      }
      System.out.println( "List size: " + fhList.size() );
   }
}

/* -------------------- run ----------------------------

List size: 10
First val: -3
Middle val: -99
Last val: 6
List size: 10
-3 6
-2 6
-1 6
0 6

6
1 6
1 5
1 4
1 3
1 -99
1 1
Caught Ex. at k = 6.  Empty list.
Caught Ex. at k = 7.  Empty list.
Caught Ex. at k = 8.  Empty list.
Caught Ex. at k = 9.  Empty list.
Caught Ex. at k = 10.  Empty list.
Caught Ex. at k = 11.  Empty list.
List size: 0


------------------------------------------------------ */