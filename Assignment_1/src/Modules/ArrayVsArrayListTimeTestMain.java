package Modules;
//Main file for arrays vs. ArrayList
//CS 1C, Foothill_Main1 College, Michael Loceff, creator

import java.util.*;
import java.text.*;

//------------------------------------------------------
public class ArrayVsArrayListTimeTestMain
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   final int ARRAY_SIZE = 20000000;
   int k;
   double avg;
   
   // for timing:
   long startTime, stopTime;
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);

   int[] arrayOfInts = new int[ARRAY_SIZE];   // simple array ---
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      arrayOfInts[k] = (int)(Math.random()*100);

   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += arrayOfInts[k];
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for Time for simple array "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");

   // ArrayList using add() / get()
   ArrayList<Integer> listOfInts = new ArrayList<Integer>(ARRAY_SIZE);
   
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      listOfInts.add( (int)(Math.random()*100) );
   
   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += listOfInts.get(k);
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for Time for ArrayList add/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
   
   // ArrayList using set() / get()
   // START TIME -------------------------------
   startTime = System.nanoTime();
   for (k = 0; k < ARRAY_SIZE; k++)
      listOfInts.set(k, (int)(Math.random()*100) );
   
   for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
      avg += listOfInts.get(k);
   avg/=ARRAY_SIZE;

   System.out.println("Average: " + avg );
   // end timing
   stopTime = System.nanoTime();

   // report algorithm time
   System.out.println("\nElapsed time for Time for ArrayList set/get "
      + tidy.format((stopTime - startTime) / 1e9)
      + " seconds.\n");
}
}

/* --------------------  run --------------------

--------- 100,000 ints -----------

Average: 49.45106

Elapsed time for Time for simple array 0.0124 seconds.

Average: 49.4848

Elapsed time for Time for ArrayList add/get 0.0167 seconds.

Average: 49.42179

Elapsed time for Time for ArrayList set/get 0.0151 seconds.


--------- 20,000,000 ints ----------

Average: 49.5042728

Elapsed time for Time for simple array 0.5226 seconds.

Average: 49.49184155

Elapsed time for Time for ArrayList add/get 0.55 seconds.

Average: 49.5082519

Elapsed time for Time for ArrayList set/get 0.5425 seconds.

------------------------------------------------ */



