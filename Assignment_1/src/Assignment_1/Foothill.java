package Assignment_1;

import cs_1c.*;
import java.text.*;
import java.util.*;

//---------------------------------------------------------------------------
//------------------------------- PART A ------------------------------------
//---------------------------------------------------------------------------


//******    Part A - Main Foothill Client    *******
public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 62;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      System.out.println("Target int: " + target);
      System.out.println("Sublist -------------------");

      //test if target is larger than the sum of all elements in master set.
      masterSum = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
         masterSum = masterSum + dataSet.get(k);
      if (masterSum < target)
      {
         //display entire dataSet as solution and exit program.
         System.out.println("   Sum: " + masterSum);
         for (k = 0 ; k < dataSet.size() ; k++)
            System.out.println("  array[" + k + "] = " + dataSet.get(k));
         return;
      }

      //create an empty sublist and add it as first element in choices.
      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() + dataSet.get(k);

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               //System.out.println(choices.size()-1);
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
            }
         }
      }

      //if we didn't find a perfect match, find the largest sublist
      max = 0;
      if (!foundPerfect)
         for (k = 0 ; k < choices.size() ; k++)
            if (choices.get(k).getSum() > max)
            {
               max = choices.get(k).getSum();
               kBest = k;
            }

      System.out.println("  Sum: " + choices.get(kBest).getSum());
      choices.get(kBest).showSublist();

   }
}

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



/* ---------------------- Test Runs for Part A ---------------------------

Target int: 72
Sublist -------------------
  Sum: 72
  array[0] = 2
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[6] = 9
  array[7] = 19

  
Target int: 13
Sublist -------------------
  Sum: 12
  array[1] = 12


Target int: 7900000
Sublist -------------------
   Sum: 138
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[7] = 19
  array[8] = 29


Target int: 0
Sublist -------------------
  Sum: 0
  
  
Target int: 46
Sublist -------------------
  Sum: 46
  array[2] = 22
  array[4] = 15
  array[6] = 9


-------------------------------------------------------------------------- */






 /*

//---------------------------------------------------------------------------
//------------------------------- PART B ------------------------------------
//---------------------------------------------------------------------------


//******    Part B - Main Foothill Client    ******
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, arraySize;
      int masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime; 

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }

      // load the dataSet ArrayList with the iTunes:
      arraySize = tunesInput.getNumTunes();
      for (k = 0; k < arraySize; k++)
         dataSet.add(tunesInput.getTune(k));

      choices.clear();
      System.out.println("Target time: " + target);
      System.out.println("Sublist -------------------");

      //test if target is larger than the sum of all elements in the master set.
      masterSum = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
         masterSum = masterSum + dataSet.get(k).getTime();
      if (masterSum < target)
      {
         //display entire dataSet as solution and exit program.
         System.out.println("  Sum: " + masterSum);
         for (k = 0 ; k < dataSet.size() ; k++)
            System.out.println("  array[" + k + "] = " 
                  + dataSet.get(k).toString());
         return;
      }

      //create an empty sublist and add it as first element in choices.
      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      // START TIME -------------------------------
      startTime = System.nanoTime();

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() 
                  + dataSet.get(k).getTime();

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
            }
         }
      }

      //if we didn't find a perfect match, find the largest sublist
      max = 0;
      if (!foundPerfect)
         for (k = 0 ; k < choices.size() ; k++)
            if (choices.get(k).getSum() > max)
            {
               max = choices.get(k).getSum();
               kBest = k;
            }

      // end timing
      stopTime = System.nanoTime();

      System.out.println("  Sum: " + choices.get(kBest).getSum());
      choices.get(kBest).showSublist();

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9)
            + " seconds.\n");

   }
}

//******    Part B - Sublist class    ******
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

 


//---------------------------------------------------------------------------
//------------------------------- PART C ------------------------------------
//---------------------------------------------------------------------------


// ******    Part C - Main Foothill Client with Ints    ******
public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist<Integer>> choices = new ArrayList<Sublist<Integer>>();
      int k, j, numSets, max, kBest, masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      System.out.println("Target int: " + target);
      System.out.println("Sublist -------------------");

      //test if target is larger than the sum of all elements in master set.
      masterSum = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
         masterSum = masterSum + dataSet.get(k);
      if (masterSum < target)
      {
         //display entire dataSet as solution and exit program.
         System.out.println("   Sum: " + masterSum);
         for (k = 0 ; k < dataSet.size() ; k++)
            System.out.println("  array[" + k + "] = " + dataSet.get(k));
         return;
      }

      //create an empty sublist and add it as first element in choices.
      Sublist<Integer> newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() + dataSet.get(k);

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k, dataSet.get(k)));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               //System.out.println(choices.size()-1);
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
            }
         }
      }

      //if we didn't find a perfect match, find the largest sublist
      max = 0;
      if (!foundPerfect)
         for (k = 0 ; k < choices.size() ; k++)
            if (choices.get(k).getSum() > max)
            {
               max = choices.get(k).getSum();
               kBest = k;
            }

      System.out.println("  Sum: " + choices.get(kBest).getSum());

      ArrayList<Integer> tempIndices = choices.get(kBest).getIndices();

      for (k = 0 ; k < tempIndices.size() ; k++)
         choices.get(kBest).showSublist(tempIndices.get(k), 
               dataSet.get(tempIndices.get(k)));


   }
}




//******    Part C - Main Foothill Client with iTunesEntrys    ******
public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist<iTunesEntry>> choices = new 
            ArrayList<Sublist<iTunesEntry>>();

      int k, j, numSets, max, kBest, arraySize;
      int masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime; 

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }

      // load the dataSet ArrayList with the iTunes:
      arraySize = tunesInput.getNumTunes();
      for (k = 0; k < arraySize; k++)
         dataSet.add(tunesInput.getTune(k));

      choices.clear();
      System.out.println("Target time: " + target);
      System.out.println("Sublist -------------------");

      //test if target is larger than sum of all elements in the master set.
      //since algorithm doesn't run, there will be no time displayed
      masterSum = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
         masterSum = masterSum + dataSet.get(k).getTime();
      if (masterSum < target)
      {
         //display entire dataSet as solution and exit program.
         System.out.println("  Sum: " + masterSum);
         for (k = 0 ; k < dataSet.size() ; k++)
            System.out.println("  array[" + k + "] = " 
                  + dataSet.get(k).toString());
         return;
      }

      //create an empty sublist and add it as first element in choices.
      Sublist<iTunesEntry> newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      // START TIME -------------------------------
      startTime = System.nanoTime();

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() 
                  + dataSet.get(k).getTime();

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k, dataSet.get(k).getTime()));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
            }
         }
      }

      //if we didn't find a perfect match, find the largest sublist
      max = 0;
      if (!foundPerfect)
         for (k = 0 ; k < choices.size() ; k++)
            if (choices.get(k).getSum() > max)
            {
               max = choices.get(k).getSum();
               kBest = k;
            }

      // end timing
      stopTime = System.nanoTime();

      System.out.println("  Sum: " + choices.get(kBest).getSum());

      ArrayList<Integer> tempIndices = choices.get(kBest).getIndices();

      for (k = 0 ; k < tempIndices.size() ; k++)
         choices.get(kBest).showSublist(tempIndices.get(k), 
               dataSet.get(tempIndices.get(k)).toString());

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9)
            + " seconds.\n");

   }
}

//******    Part C - Generic Sublist class   ******
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
