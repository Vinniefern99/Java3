package Assignment_1;

import cs_1c.*;
import java.text.*;
import java.util.*;

/*
//------------------- Part A Sublist - ints -------------------


class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<Integer> originalObjects;
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<Integer> orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   int getSum() 
   { 
      return sum; 
   }

   Sublist addItem( int indexOfItemToAdd ) throws CloneNotSupportedException
   { 
      Sublist newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum = newSublist.sum + originalObjects.get(indexOfItemToAdd);

      return newSublist;
   }

   void showSublist()
   { 
      for (int k = 0 ; k < indices.size() ; k++)
         System.out.println("array[" + k + "] = " 
               + originalObjects.get(indices.get(k)));
   }

   // I have done the clone() for you, since you will need it inside addItem().
   // I moved the clone function to the end, cause that's what I'm used to.
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();

      return newObject;
   }
};
*/


//------------------- Part B Sublist - iTunesEntries -------------------

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   int getSum() 
   { 
      return sum; 
   }

   Sublist addItem( int indexOfItemToAdd ) throws CloneNotSupportedException
   { 
      Sublist newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum = newSublist.sum 
            + originalObjects.get(indexOfItemToAdd).getTime();

      return newSublist;
   }

   void showSublist()
   { 
      for (int k = 0 ; k < indices.size() ; k++)
         System.out.println("array[" + k + "] = " 
               + originalObjects.get(indices.get(k)).toString());
   }

   // I have done the clone() for you, since you will need it inside addItem().
   // I moved the clone function to the end, cause that's what I'm used to.
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();

      return newObject;
   }
};

