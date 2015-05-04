package Assignment_1;

/*


//CIS 1C Assignment #1
//Part A - int version

import java.util.*;

//------------------------------------------------------
public class Foothill_ModelSulution
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int target = 80;
   ArrayList<Integer> dataSet = new ArrayList<Integer>();
   ArrayList<Sublist> choices = new ArrayList<Sublist>();
   int k, j, numSets, max, masterSum, newSum, bestSublist;
   boolean foundPerfect, needAlgorithm;

   dataSet.add(2); dataSet.add(12); dataSet.add(22);
   dataSet.add(5); dataSet.add(15); dataSet.add(25);
   dataSet.add(9); dataSet.add(19); dataSet.add(29);

   choices.clear();
   System.out.println("Target time: " + target);

   // dispose of easy case immediately to save lots of time
   masterSum = (int)computeMasterSum(dataSet);

   if ( target >= masterSum )
   {
      System.out.println( "Solution is entire master set with sum = "
            +  masterSum );
      showEntireVector(dataSet);
      needAlgorithm = false;
   }
   else
      needAlgorithm = true;

   if ( needAlgorithm )
   {
      choices.add( new Sublist( dataSet ) ); // the 0 set

      foundPerfect = false;
      for (k = 0, max = bestSublist = 0;
            k < dataSet.size() && !foundPerfect; k++)
      {
         // for each set so far create a new one containing that set U k
         numSets = choices.size(); // get size before we start adding more
         for (j = 0; j < numSets; j++)
         {
            newSum = choices.get(j).getSum()  +  dataSet.get(k);
            if ( newSum <= target )
            {
               choices.add( choices.get(j).addItem(k) );
               // combine algorithm with test for best
               if ( newSum > max )
               {
                  bestSublist = choices.size() - 1;
                  max = newSum;
               }
            }

            if ( newSum == target )
            {
               foundPerfect = true;
               break;
            }
         }
      }

      choices.get(bestSublist).showSublist();
   }
}

// client algorithm helper functions ---------------------------
static double computeMasterSum( ArrayList<Integer> data )
{
   int k, numSets;
   double masterSum;

   numSets = data.size();
   masterSum = 0;
   for (k = 0; k < numSets ; k++)
      masterSum += data.get(k);
   return masterSum;
}

static void showEntireVector( ArrayList<Integer> data )
{
   int k, numSets;

   numSets = data.size();
   for (k = 0; k < numSets ; k++)
      System.out.print( "  array[" + k + "] = "
            +  data.get(k)
            + ( (k == numSets-1)? "\n\n" : ", " ) );  
}
}


//--------------- Class Sublist Definition ---------------
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

int getSum() { return sum; }

public Object clone() throws CloneNotSupportedException
{
   // shallow copy
   Sublist newObject = (Sublist)super.clone();
   // deep copy
   newObject.indices = (ArrayList<Integer>)indices.clone();

   return newObject;
}

Sublist addItem( int indexOfItemToAdd )
{
   Sublist newSublist;
   
   // avoids array overrun and potential crash.
   if ( indexOfItemToAdd < 0 || indexOfItemToAdd >= originalObjects.size() )
      return null;

   try
   {
      newSublist = (Sublist)this.clone(); //  copy old values, then ...

      newSublist.sum = newSublist.sum
            + originalObjects.get(indexOfItemToAdd);
      newSublist.indices.add(indexOfItemToAdd);
      return newSublist;
   }
   catch (CloneNotSupportedException e)
   {
      return null;
   }
}

void showSublist()
{
   int k;

   System.out.println("Sublist ----------------------------- ");
   System.out.println("  sum: "+ sum);
   for ( k = 0; k < (int)indices.size(); k++)
      System.out.println("  array[" + indices.get(k) + "] = "
            + originalObjects.get( indices.get(k) )
            + ( (k == (int)indices.size() - 1)? "\n\n" : ", " ) );  
}
}


/* ---------------------- RUNS ------------------------------
Target time: 1
Sublist -----------------------------
sum: 0

Target time: 13
Sublist -----------------------------
sum: 12
array[1] = 12

Target time: 37
Sublist -----------------------------
sum: 37
array[2] = 22,
array[4] = 15

Target time: 92
Sublist -----------------------------
sum: 92
array[1] = 12,
array[2] = 22,
array[3] = 5,
array[5] = 25,
array[6] = 9,
array[7] = 19

Target time: 120
Sublist -----------------------------
sum: 119
array[0] = 2,
array[1] = 12,
array[2] = 22,
array[3] = 5,
array[4] = 15,
array[5] = 25,
array[6] = 9,
array[8] = 29

Target time: 10000
Solution is entire master set with sum = 138
array[0] = 2,   array[1] = 12,   array[2] = 22,   array[3] = 5,   array[4] = 1
5,   array[5] = 25,   array[6] = 9,   array[7] = 19,   array[8] = 29

----------------------------- */




/*
//----------------------------------------------------------------------
//CIS 1C Assignment #1
//Part B - iTunes Version
import cs_1c.*;
import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill_ModelSulution
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int target = 180;
   ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
   ArrayList<Sublist> choices = new ArrayList<Sublist>();
   int k, j, numSets, max, arraySize, masterSum, newSum, bestSublist;
   boolean foundPerfect, needAlgorithm;

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

   // dispose of easy case immediately to save lots of time
   masterSum = (int)computeMasterSum(dataSet);
   if ( target >= masterSum )
   {
      System.out.println( "Solution is entire master set with sum = "
            +  masterSum );
      showEntireVector(dataSet);
      needAlgorithm = false;
   }
   else
      needAlgorithm = true;

   if ( needAlgorithm )
   {
      // START TIMING THE ALGORITHM
      startTime = System.nanoTime();
      choices.add( new Sublist( dataSet ) ); // this represents the 0 set

      foundPerfect = false;
      for (k = 0, max = bestSublist = 0;
            k < dataSet.size() && !foundPerfect; k++)
      {
         // for each set so far, create a new one containing that set U k
         numSets = choices.size(); // get size before we start adding more
         for (j = 0; j < numSets; j++)
         {
            newSum = choices.get(j).getSum()  +  dataSet.get(k).getTime();
            if ( newSum <= target )
            {
               choices.add( choices.get(j).addItem(k) );
               // combine algorithm with test for best
               if ( newSum > max )
               {
                  bestSublist = choices.size() - 1;
                  max = newSum;
               }
            }

            if (  newSum == target)
            {
               foundPerfect = true;
               break;
            }
         }
      }

      // END TIMING
      stopTime = System.nanoTime();

      choices.get(bestSublist).showSublist();

      // report algorithm time
      System.out.println("Algorithm Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   }
}

// client algorithm helper functions ---------------------------
static double computeMasterSum( ArrayList<iTunesEntry> data )
{
   int k, numSets;
   double masterSum;

   numSets = data.size();
   masterSum = 0;
   for (k = 0; k < numSets ; k++)
      masterSum += data.get(k).getTime();
   return masterSum;
}

static void showEntireVector( ArrayList<iTunesEntry> data )
{
   int k, numSets;

   numSets = data.size();
   for (k = 0; k < numSets ; k++)
      System.out.print( "  array[" + k + "] = "
            +  data.get(k)
            + ( (k == numSets-1)? "\n\n" : ", " ) );  
}
}

//--------------- Class Sublist Definition ---------------
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

int getSum() { return sum; }

public Object clone() throws CloneNotSupportedException
{
   // shallow copy
   Sublist newObject = (Sublist)super.clone();
   // deep copy
   newObject.indices = (ArrayList<Integer>)indices.clone();
   
   return newObject;
}

Sublist addItem( int indexOfItemToAdd )
{
   Sublist newSublist;

   // avoids array overrun and potential crash.
   if ( indexOfItemToAdd < 0 || indexOfItemToAdd >= originalObjects.size() )
      return null;

   try
   {
      newSublist = (Sublist)this.clone(); //  copy old values, then ...

      newSublist.sum = newSublist.sum
         + originalObjects.get(indexOfItemToAdd).getTime();
      newSublist.indices.add(indexOfItemToAdd);
      return newSublist;
   }
   catch (CloneNotSupportedException e)
   {
      return null;
   }
}

void showSublist()
{
   int k;
   
   System.out.println("Sublist ----------------------------- ");
   System.out.println("  sum: "+ sum);
   for ( k = 0; k < (int)indices.size(); k++)
      System.out.println("  array[" + indices.get(k) + "] = "
         + originalObjects.get( indices.get(k) )
         + ( (k == (int)indices.size() - 1)? "\n\n" : ", " ) );  
}
}

/* --------- output -----------

Target time: 180
Sublist -----------------------------
sum: 178
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58


Algorithm Elapsed Time: 0.0006 seconds.


Target time: 1201
Sublist -----------------------------
sum: 1201
array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
array[3] = Foo Fighters | All My Life |  4:23,
array[6] = Eric Clapton | Bad Love |  5:08,
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
array[9] = Reverend Gary Davis | Samson and Delilah |  3:36


Algorithm Elapsed Time: 0.0042 seconds.


Target time: 3600
Sublist -----------------------------
sum: 3600
array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
array[1] = Carrie Underwood | Quitter |  3:40,
array[2] = Rihanna | Russian Roulette |  3:48,
array[4] = Foo Fighters | Monkey Wrench |  3:50,
array[5] = Eric Clapton | Pretending |  4:43,
array[6] = Eric Clapton | Bad Love |  5:08,
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
array[8] = Howlin' Wolf | Well That's All Right |  2:55,
array[9] = Reverend Gary Davis | Samson and Delilah |  3:36,
array[11] = Roy Buchanan | Hot Cha |  3:28,
array[12] = Roy Buchanan | Green Onions |  7:23,
array[13] = Janiva Magness | I'm Just a Prisoner |  3:50,
array[14] = Janiva Magness | You Were Never Mine |  4:36,
array[15] = John Lee Hooker | Hobo Blues |  3:07,
array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02


Algorithm Elapsed Time: 0.1584 seconds.


Target time: 4300
Sublist -----------------------------
sum: 4300
array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
array[1] = Carrie Underwood | Quitter |  3:40,
array[2] = Rihanna | Russian Roulette |  3:48,
array[3] = Foo Fighters | All My Life |  4:23,
array[4] = Foo Fighters | Monkey Wrench |  3:50,
array[5] = Eric Clapton | Pretending |  4:43,
array[6] = Eric Clapton | Bad Love |  5:08,
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
array[9] = Reverend Gary Davis | Samson and Delilah |  3:36,
array[10] = Reverend Gary Davis | Twelve Sticks |  3:14,
array[11] = Roy Buchanan | Hot Cha |  3:28,
array[12] = Roy Buchanan | Green Onions |  7:23,
array[13] = Janiva Magness | I'm Just a Prisoner |  3:50,
array[14] = Janiva Magness | You Were Never Mine |  4:36,
array[15] = John Lee Hooker | Hobo Blues |  3:07,
array[17] = Snoop Dogg | That's The Homie |  5:43,
array[18] = Snoop Dogg | Gangsta Luv |  4:17


Algorithm Elapsed Time: 0.3277 seconds.

Target time: 4000
Solution is entire master set with sum = 138
array[0] = 2,   array[1] = 12,   array[2] = 22,   array[3] = 5,   array[4] = 1
5,   array[5] = 25,   array[6] = 9,   array[7] = 19,   array[8] = 29

Target time: 4000
Sublist -----------------------------
sum: 4000
array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
array[1] = Carrie Underwood | Quitter |  3:40,
array[2] = Rihanna | Russian Roulette |  3:48,
array[3] = Foo Fighters | All My Life |  4:23,
array[4] = Foo Fighters | Monkey Wrench |  3:50,
array[5] = Eric Clapton | Pretending |  4:43,
array[6] = Eric Clapton | Bad Love |  5:08,
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
array[8] = Howlin' Wolf | Well That's All Right |  2:55,
array[9] = Reverend Gary Davis | Samson and Delilah |  3:36,
array[11] = Roy Buchanan | Hot Cha |  3:28,
array[12] = Roy Buchanan | Green Onions |  7:23,
array[13] = Janiva Magness | I'm Just a Prisoner |  3:50,
array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02,
array[17] = Snoop Dogg | That's The Homie |  5:43,
array[18] = Snoop Dogg | Gangsta Luv |  4:17


Algorithm Elapsed Time: 0.3242 seconds.


------------------------------ */

/*
 * 
 

//---------------------------------------------------------------------------
//CS 1C Assignment #1
//Part C - generic Version

import cs_1c.*;
import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill_ModelSulution
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int target = 4000;
   ArrayList<iTunesEntry> dataSetTunes = new ArrayList<iTunesEntry>();
   ArrayList<Sublist<iTunesEntry>> choicesTunes
      = new ArrayList<Sublist<iTunesEntry>>();
   ArrayList<Integer> dataSetInt = new ArrayList<Integer>();
   ArrayList<Sublist<Integer>> choicesInt
      = new ArrayList<Sublist<Integer>>();
   int k, j, numSets, max, arraySize, masterSum, newSum, bestSublist,
      tuneTime, intVal;
   boolean foundPerfect, needAlgorithm;
   
   
   // int --------------------------------------------------------------

   dataSetInt.add(2); dataSetInt.add(12); dataSetInt.add(22);
   dataSetInt.add(5); dataSetInt.add(15); dataSetInt.add(25);
   dataSetInt.add(9); dataSetInt.add(19); dataSetInt.add(29);
   
   choicesInt.clear();
   System.out.println("Target time: " + target);
   
   // dispose of easy case immediately to save lots of time
   masterSum = computeMasterSumInt(dataSetInt);
   if ( target >= masterSum )
   {
      System.out.println( "Solution is entire master set with sum = "
            +  masterSum );
      showEntireVectorInt(dataSetInt);
      needAlgorithm = false;
   }
   else
      needAlgorithm = true;
   
   if ( needAlgorithm )
   {
      choicesInt.add( new Sublist<Integer>( dataSetInt ) ); //  0 set

      foundPerfect = false;
      for (k = 0, max = bestSublist = 0;
            k < dataSetInt.size() && !foundPerfect; k++)
      {
         // for each set so far create a new one containing that set U k
         numSets = choicesInt.size(); // size before we start adding more
         for (j = 0; j < numSets; j++)
         {
            intVal = dataSetInt.get(k);
            newSum = choicesInt.get(j).getSum() + intVal;
            if ( newSum <= target )
            {
               choicesInt.add( choicesInt.get(j).addItem(k, intVal) );
               // combine algorithm with test for best
               if ( newSum > max )
               {
                  bestSublist = choicesInt.size() - 1;
                  max = newSum;
               }
            }
            
            if ( newSum == target )
            {
               foundPerfect = true;
               break;
            }
         }
      }

      choicesInt.get(bestSublist).showSublist();     
   }
   
   // iTunes -----------------------------------------------------------
   
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

   // load the dataSetTunes ArrayList with the iTunes:
   arraySize = tunesInput.getNumTunes();
   for (k = 0; k < arraySize; k++)
      dataSetTunes.add(tunesInput.getTune(k));

   choicesTunes.clear();
   System.out.println("Target time: " + target);
   
   // dispose of easy case immediately to save lots of time
   masterSum = computeMasterSumTune(dataSetTunes);
   if ( target >= masterSum )
   {
      System.out.println( "Solution is entire master set with sum = "
            +  masterSum );
      showEntireVectorTune(dataSetTunes);
      needAlgorithm = false;
   }
   else
      needAlgorithm = true;
   

   if ( needAlgorithm )
   {
      // START TIMING THE ALGORITHM
      startTime = System.nanoTime();
      choicesTunes.add( new Sublist<iTunesEntry>( dataSetTunes ) ); //  0 set

      foundPerfect = false;
      for (k = 0, max = bestSublist = 0;
            k < dataSetTunes.size() && !foundPerfect; k++)
      {
         // for each set so far create a new one containing that set U k
         numSets = choicesTunes.size(); // size before we start adding more
         for (j = 0; j < numSets; j++)
         {
            tuneTime = dataSetTunes.get(k).getTime();
            newSum = choicesTunes.get(j).getSum() + tuneTime;
            if ( newSum <= target )
            {
               choicesTunes.add( choicesTunes.get(j).addItem(k, tuneTime) );
               // combine algorithm with test for best
               if ( newSum > max )
               {
                  bestSublist = choicesTunes.size() - 1;
                  max = newSum;
               }
            }
            
            if ( newSum == target )
            {
               foundPerfect = true;
               break;
            }
         }
      }

      // END TIMING
      stopTime = System.nanoTime();

      choicesTunes.get(bestSublist).showSublist();

      // report algorithm time
      System.out.println("Algorithm Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   }
}

// client algorithm iTunes helper functions ---------------------------
static int computeMasterSumTune( ArrayList<iTunesEntry> data )
{
   int k, numSets;
   int masterSum;

   numSets = data.size();
   masterSum = 0;
   for (k = 0; k < numSets ; k++)
      masterSum += data.get(k).getTime();
   return masterSum;
}

static void showEntireVectorTune( ArrayList<iTunesEntry> data )
{
   int k, numSets;

   numSets = data.size();
   for (k = 0; k < numSets ; k++)
      System.out.print( "  array[" + k + "] = "
            +  data.get(k)
            + ( (k == numSets-1)? "\n\n" : ", " ) );  
}

// client algorithm Integer helper functions ---------------------------
static int computeMasterSumInt( ArrayList<Integer> data )
{
   int k, numSets;
   int masterSum;

   numSets = data.size();
   masterSum = 0;
   for (k = 0; k < numSets ; k++)
      masterSum += data.get(k);
   return masterSum;
}

static void showEntireVectorInt( ArrayList<Integer> data )
{
   int k, numSets;

   numSets = data.size();
   for (k = 0; k < numSets ; k++)
      System.out.print( "  array[" + k + "] = "
            +  data.get(k)
            + ( (k == numSets-1)? "\n\n" : ", " ) );  
}
}

//--------------- Class Sublist Definition ---------------
class Sublist<E> implements Cloneable
{
private int sum = 0;
private ArrayList<E> originalObjects;
private ArrayList<Integer> indices;

// constructor creates an empty Sublist (no indices)
public Sublist(ArrayList<E> orig)
{
   sum = 0;
   originalObjects = orig;
   indices = new ArrayList<Integer>();
}

int getSum() { return sum; }

public Object clone() throws CloneNotSupportedException
{
   // shallow copy
   Sublist<E> newObject = (Sublist<E>)super.clone();
   // deep copy
   newObject.indices = (ArrayList<Integer>)indices.clone();
   
   return newObject;
}

Sublist<E> addItem( int indexOfItemToAdd, int valOfNewItem )
{
   Sublist<E> newSublist;

   // avoids array overrun and potential crash.
   if ( indexOfItemToAdd < 0 || indexOfItemToAdd >= originalObjects.size() )
      return null;

   try
   {
      newSublist = (Sublist<E>)this.clone(); //  copy old values, then ...

      newSublist.sum = newSublist.sum
         + valOfNewItem;
      newSublist.indices.add(indexOfItemToAdd);
      return newSublist;
   }
   catch (CloneNotSupportedException e)
   {
      return null;
   }
}

void showSublist()
{
   int k;
   
   System.out.println("Sublist ----------------------------- ");
   System.out.println("  sum: "+ sum);
   for ( k = 0; k < (int)indices.size(); k++)
      System.out.println("  array[" + indices.get(k) + "] = "
         + originalObjects.get( indices.get(k) )
         + ( (k == (int)indices.size() - 1)? "\n\n" : ", " ) );  
}
}

/* --------- output -----------

Target time: 130
Sublist -----------------------------
sum: 129
array[0] = 2,
array[1] = 12,
array[2] = 22,
array[3] = 5,
array[4] = 15,
array[5] = 25,
array[7] = 19,
array[8] = 29


Target time: 130
Sublist -----------------------------
sum: 79
array[32] = Blue Record | Bullhead's Psalm |  1:19


Algorithm Elapsed Time: 0 seconds.



Target time: 4000
Solution is entire master set with sum = 138
array[0] = 2,   array[1] = 12,   array[2] = 22,   array[3] = 5,   array[4] = 1
5,   array[5] = 25,   array[6] = 9,   array[7] = 19,   array[8] = 29

Target time: 4000
Sublist -----------------------------
sum: 4000
array[0] = Carrie Underwood | Cowboy Casanova |  3:56,
array[1] = Carrie Underwood | Quitter |  3:40,
array[2] = Rihanna | Russian Roulette |  3:48,
array[3] = Foo Fighters | All My Life |  4:23,
array[4] = Foo Fighters | Monkey Wrench |  3:50,
array[5] = Eric Clapton | Pretending |  4:43,
array[6] = Eric Clapton | Bad Love |  5:08,
array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58,
array[8] = Howlin' Wolf | Well That's All Right |  2:55,
array[9] = Reverend Gary Davis | Samson and Delilah |  3:36,
array[11] = Roy Buchanan | Hot Cha |  3:28,
array[12] = Roy Buchanan | Green Onions |  7:23,
array[13] = Janiva Magness | I'm Just a Prisoner |  3:50,
array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02,
array[17] = Snoop Dogg | That's The Homie |  5:43,
array[18] = Snoop Dogg | Gangsta Luv |  4:17


Algorithm Elapsed Time: 0.3242 seconds.


.----------------------------- */




*/