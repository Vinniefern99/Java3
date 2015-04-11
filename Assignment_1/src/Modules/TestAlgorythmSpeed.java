package Modules;
// Example of timing a simple sort algorithm
import java.util.*;
import java.text.*;
import cs_1c.*;

public class TestAlgorythmSpeed
{
   public static void main (String[] args) throws CloneNotSupportedException
   {
      final int ARRAY_SIZE = 2000;
      int k;
      
      // for formatting output neatly
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // allocate the array
      Integer[] arrayOfInts = new Integer[ARRAY_SIZE];

      // put a random int in every array location
      for (k = 0; k < ARRAY_SIZE; k++)
         arrayOfInts[k] = (int)(Math.random() * ARRAY_SIZE);

      // how we time our algorithms -------------------------
      long startTime, stopTime; 
      startTime = System.nanoTime();

      // sort using a home made bubble sort
      FoothillSort.arraySort(arrayOfInts);

      // how we determine the time elapsed -------------------
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
         + tidy.format( (stopTime - startTime) / 1e9)
         + " seconds.");
   }
}