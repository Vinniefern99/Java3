package modules_2;

import cs_1c.*;
import java.util.*;

public class FHLinkedListSecondMain
{
   public static void main (String[] args) throws CloneNotSupportedException
   {
      int k;
      final int INT_ARRAY_SIZE = 10000;
      double avg;
      
      String[] words = {"When", "Harry", "Met", "Bullitt", 
         "X", "Wild", "Beast", "X", 
         "X", "Men", "Nikita", "X", "Back", "To", 
         "The", "Future", "X", "House", "Good", "Wife"};
      String[] capturedStrings = new String[100];
      
      FHlinkedList<String> 
         myStrings = new FHlinkedList<String>(),
         myStrings2 = new FHlinkedList<String>(),
         myStrings3;
      FHlinkedList<Integer> 
         myInts = new FHlinkedList<Integer>(),
         myInts2;
         
      LinkedList<Integer> myIntsColl = new LinkedList<Integer>();
      //FHlinkedList<Integer> myIntsColl = new FHlinkedList<Integer>();
      
      // testing add() on Strings
      for (k = 0; k < words.length; k++)
      {
         myStrings.add(words[k]);
         myStrings2.add(new String(words[k]));
      }
      
      // testing add(index, ...) on ints
      for (k = 0; k < INT_ARRAY_SIZE; k++)
         myInts.add( myInts.size()/2, (int)(Math.random()*100) );

      // testing get() on ints
      for (k = 0, avg = 0.; k < myInts.size(); k++)
         avg += myInts.get(k);
      avg/=myInts.size();
      System.out.println("Average: " + avg );

      // testing set() on ints
      for (k = 0; k < myInts.size(); k++)
         myInts.set(k, (int)(Math.random()*100) );
      
      for (k = 0, avg = 0.; k < myInts.size(); k++)
         avg += myInts.get(k);
      avg/=myInts.size();
      System.out.println("Average: " + avg );
      
      // testing equals
      if (myStrings.equals(myStrings2))
         System.out.println("\nTest as equal -- GOOD");
      else
         System.out.println("Test as unequal -- ERROR");

      myStrings2.set(2, "Hurt Locker");
      if (myStrings.equals(myStrings2))
         System.out.println("Test as equal -- ERROR");
      else
         System.out.println("Test as unequal -- GOOD");      

      if (myStrings.equals(myInts))
         System.out.println("Test as equal -- ERROR");
      else
         System.out.println("Test as unequal -- GOOD");
      
      // testing clone
      myStrings3 = (FHlinkedList<String>)myStrings.clone();
      if (myStrings.equals(myStrings3))
         System.out.println("clone should test equal -- GOOD");
      else
         System.out.println("clone tests unequal -- ERROR");
      
      myStrings3.set(3, "The Kennedys");
      System.out.println("shallow changes after clone");
      for (k = 0; k < 4; k++)
         System.out.println(k + ": " + myStrings.get(k) + " / "
               + myStrings3.get(k) ); 
      
      // testing add(index, ...) on Strings
      System.out.println("\nOriginal String ArrayList:"); 
      for (k = 0; k < myStrings.size(); k++)
         System.out.print(k + ": " + myStrings.get(k) + " / ");
      System.out.println(); 
      
      myStrings.add(2, "Broken Heart");
      
      System.out.println("Item added in position 2:"); 
      for (k = 0; k < myStrings.size(); k++)
         System.out.print(k + ": " + myStrings.get(k) + " / ");
      System.out.println(); 
      
      // testing remove(index) and remove(object)
      System.out.println("\nTesting remove(index) and remove(object).");
      for (k = 0; k < 6; k++)
         System.out.print(k + ": " + myInts.get(k) + " / ");
      System.out.println();
      
      myInts.remove(3);
      myInts.remove( myInts.get(0) );
      
      System.out.println("0th and 3rd items should be gone.");
      for (k = 0; k < 6; k++)
         System.out.print(k + ": " + myInts.get(k) + " / ");
      System.out.println();
      
      // testing clear() and addAll()
      myInts.clear();
      System.out.println("\nNew size (should be 0): " + myInts.size() );
      for (k = 0; k < 10; k++)
         myIntsColl.add(k);
      myInts2 = new FHlinkedList<Integer>();
      myInts2.addAll(myIntsColl);

      System.out.println("\naddAll() -- should be identical");
      for (k = 0; k < myIntsColl.size(); k++)
         System.out.print(k + ": " + myIntsColl.get(k) + " / " );
      System.out.println();
      for (k = 0; k < myInts2.size(); k++)
         System.out.print(k + ": " + myInts2.get(k) + " / ");
      System.out.println();
      
      // testing indexOf()...
      myInts2.add(6,2);
      System.out.println();
      for (k = 0; k < myInts2.size(); k++)
         System.out.print(k + ": " + myInts2.get(k) + " / ");
      System.out.println();
      System.out.println("\nindexOf() and lastIndexOf()");
      System.out.println("index of 2: " + myInts2.indexOf(2));    
      System.out.println("last index of 2: " + myInts2.lastIndexOf(2));  
      System.out.println();
      
      // testing contains()
      System.out.println("String List contains Bullitt? (should be T): " 
            + myStrings.contains("Bullitt") );
      System.out.println("Int List contains 93? (should be F): " 
            + myInts2.contains(93) ); 
      
      // testing exceptions -----------------
      System.out.println();
      // set()
      try
      {
         myInts.set(20, 20);
      }
      catch(Exception e)
      {
         System.out.println("set() - caught!  -- " + e);
      }
   
      // add()
      try
      {
         myInts.add(20, 20);
      }
      catch(Exception e)
      {
         System.out.println("add() - caught!  -- " + e);
      }
    
      // get()
      try
      {
         myInts.get(20);
      }
      catch(Exception e)
      {
         System.out.println("get() - caught!  -- " + e);
      }
      
      System.out.println("\nTesting toArray()");
      String[] y = myStrings.toArray(capturedStrings);
      
      for (k = 0; capturedStrings[k] != null; k++)
         System.out.print(k + ": " + capturedStrings[k] + " / ");
      System.out.println();
      for (k = 0; y[k] != null; k++)
         System.out.print(k + ": " + y[k] + " / ");
  }
}

/* -------------------- run ----------------------------

Average: 49.0808
Average: 49.1052

Test as equal -- GOOD
Test as unequal -- GOOD
Test as unequal -- GOOD
clone should test equal -- GOOD
shallow changes after clone
0: When / When
1: Harry / Harry
2: Met / Met
3: Bullitt / The Kennedys

Original String ArrayList:
0: When / 1: Harry / 2: Met / 3: Bullitt / 4: X / 5: Wild / 6: Beast / 7: X / 8:
 X / 9: Men / 10: Nikita / 11: X / 12: Back / 13: To / 14: The / 15: Future / 16
: X / 17: House / 18: Good / 19: Wife / 
Item added in position 2:
0: When / 1: Harry / 2: Broken Heart / 3: Met / 4: Bullitt / 5: X / 6: Wild / 7:
 Beast / 8: X / 9: X / 10: Men / 11: Nikita / 12: X / 13: Back / 14: To / 15: Th
e / 16: Future / 17: X / 18: House / 19: Good / 20: Wife / 

Testing remove(index) and remove(object).
0: 49 / 1: 37 / 2: 37 / 3: 18 / 4: 74 / 5: 13 / 
0th and 3rd items should be gone.
0: 37 / 1: 37 / 2: 74 / 3: 13 / 4: 33 / 5: 51 / 

New size (should be 0): 0

addAll() -- should be identical
0: 0 / 1: 1 / 2: 2 / 3: 3 / 4: 4 / 5: 5 / 6: 6 / 7: 7 / 8: 8 / 9: 9 / 
0: 0 / 1: 1 / 2: 2 / 3: 3 / 4: 4 / 5: 5 / 6: 6 / 7: 7 / 8: 8 / 9: 9 / 

0: 0 / 1: 1 / 2: 2 / 3: 3 / 4: 4 / 5: 5 / 6: 2 / 7: 6 / 8: 7 / 9: 8 / 10: 9 / 

indexOf() and lastIndexOf()
index of 2: 2
last index of 2: 6

String List contains Bullitt? (should be T): true
Int List contains 93? (should be F): false

set() - caught!  -- java.lang.IndexOutOfBoundsException
add() - caught!  -- java.lang.IndexOutOfBoundsException
get() - caught!  -- java.lang.IndexOutOfBoundsException

Testing toArray()
0: When / 1: Harry / 2: Broken Heart / 3: Met / 4: Bullitt / 5: X / 6: Wild / 7:
 Beast / 8: X / 9: X / 10: Men / 11: Nikita / 12: X / 13: Back / 14: To / 15: Th
e / 16: Future / 17: X / 18: House / 19: Good / 20: Wife / 
0: When / 1: Harry / 2: Broken Heart / 3: Met / 4: Bullitt / 5: X / 6: Wild / 7:
 Beast / 8: X / 9: X / 10: Men / 11: Nikita / 12: X / 13: Back / 14: To / 15: Th
e / 16: Future / 17: X / 18: House / 19: Good / 20: Wife / 

------------------------------------------------------- */