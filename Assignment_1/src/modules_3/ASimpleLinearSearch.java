package modules_3;

//Sample timing complexity of a linear search
//CS 1C, Foothill_Main1 College, Michael Loceff

//client -----------------------------------------------------

import cs_1c.*;
import java.text.*;
import java.util.*;

//------------------------------------------------------
public class ASimpleLinearSearch
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k, arraySize, searchItem, loc;
      double totalTime, elapsedTime = 0;
      Integer arrayOfInts[];
      // to time the algorithm -------------------------
      long startTime, stopTime, samples;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      final int NUM_SAMPLES = 100;

      for (arraySize = 1000000;
            (elapsedTime < 5) 
            && (arraySize < Integer.MAX_VALUE/4) && (arraySize > 0);
            arraySize*=2 )
      {
         // allocate a pre-sorted array
         arrayOfInts = new Integer[arraySize];
         for (k = 0; k < arraySize; k++)
            arrayOfInts[k] = k;

         for (samples = 0, totalTime = 0; samples < NUM_SAMPLES; samples++)
         {
            searchItem = (int)( Math.random() * arraySize );

            startTime = System.nanoTime();
            loc = Search.linearSearch(arrayOfInts, searchItem);
            stopTime = System.nanoTime();

            totalTime += (stopTime - startTime) / 1e9;
         }
         elapsedTime = totalTime / NUM_SAMPLES;

         System.out.println("N: " + arraySize + ", Search Time: " 
               + tidy.format(elapsedTime)  + " seconds" );
      }
   }
}

class Search<E>
{
   public static < E extends Comparable<? super E > >
   int linearSearch(E[] array, E search_item)
   {
      for (int k = 0; k < array.length; k++)
         if ( search_item.compareTo(array[k]) == 0 )
            return k;   //found him!
      return -1;
   }
}