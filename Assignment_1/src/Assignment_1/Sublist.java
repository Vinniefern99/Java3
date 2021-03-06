package Assignment_1;

import cs_1c.*;

import java.text.*;
import java.util.*;


//---------------------------------------------------------------------------
//------------------------------- PART A ------------------------------------
//---------------------------------------------------------------------------


//******    Part A - Sublist class    *******
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
       System.out.println("  array[" + indices.get(k) + "] = " 
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


/*
 * Part B - Sublist class
 *
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
         System.out.println("  array[" + indices.get(k) + "] = " 
               + originalObjects.get(indices.get(k)).toString());
   }

   // I have done the clone() for you, since you will need it inside addItem().
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



/*
 * Part C - Generic Sublist class
 *
class Sublist<E> implements Cloneable
{
   private int sum = 0;
   private E originalObjects;
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(E orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   int getSum() 
   { 
      return sum; 
   }

   ArrayList<Integer> getIndices() 
   { 
      return indices; 
   }

   Sublist<E> addItem( int indexOfItemToAdd, int amountToAdd) 
         throws CloneNotSupportedException
   { 
      Sublist<E> newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum = newSublist.sum + amountToAdd;

      return newSublist;
   }

   <F> void showSublist(int index, F toDisplay)
   { 
      System.out.println("  array[" + index + "] = "  + toDisplay);
   }

   // I have done the clone() for you, since you will need it inside addItem().
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
