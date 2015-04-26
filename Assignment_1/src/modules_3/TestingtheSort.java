package modules_3;

//Main file for iTunes project - bubble sort example.
//CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class TestingtheSort
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int k, arraySize;
   double elapsedTime;
   Integer[] arrayOfInts;
   // to time the algorithm -------------------------
   long startTime, stopTime;
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);

   elapsedTime = 0;
   for (arraySize = 100; elapsedTime < 10; arraySize*=2)
   {
      // allocate array and stuff will values
      arrayOfInts = new Integer[arraySize];
      for (k = 0; k < arraySize; k++)
         arrayOfInts[k] = (int)(Math.random() * arraySize);

      startTime = System.nanoTime();

      // sort using a home made bubble sort (in Foothill_Sort.h)
      FoothillSort.arraySort(arrayOfInts);

      stopTime = System.nanoTime();
      elapsedTime = (stopTime - startTime) / 1e9;
      
      System.out.println("Array Size: " + arraySize
         + " Sort time: "
         + tidy.format(elapsedTime)
         + " seconds.");
   } 
}
}

/* ----------------- RUN --------------------------

Array Size: 100 Sort time: 0.0011 seconds.
Array Size: 200 Sort time: 0.0024 seconds.
Array Size: 400 Sort time: 0.0053 seconds.
Array Size: 800 Sort time: 0.0062 seconds.
Array Size: 1600 Sort time: 0.0062 seconds.
Array Size: 3200 Sort time: 0.0259 seconds.
Array Size: 6400 Sort time: 0.111 seconds.
Array Size: 12800 Sort time: 0.4626 seconds.
Array Size: 25600 Sort time: 1.9608 seconds.
Array Size: 51200 Sort time: 8.1652 seconds.
Array Size: 102400 Sort time: 33.4978 seconds..

----------------------------------------------- */