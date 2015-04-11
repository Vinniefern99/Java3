package Modules;

//Main file for iTunes project - insertion example.
//CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class InsertIntoArrayMain
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // to time the algorithm -------------------------
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // how we read the data from files
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");
      int arraySize;

      // how we test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }

      // create an array of objects for our own use:
      arraySize = tunesInput.getNumTunes();

      // we add 1 to make room for an insertion
      iTunesEntry[] tunesArray = new iTunesEntry[arraySize + 1];
      for (int k = 0; k < arraySize; k++)
         tunesArray[k] = tunesInput.getTune(k);

      // 5 positions we will "insert at" in the larger iTunes list of 80 tunes
      int writePosition;
      final int NUM_INSERTIONS  = 500;
      int[] somePositions = {3, 67, 20, 15, 59  };

      int numPositions = somePositions.length;

      System.out.println("Doing " + NUM_INSERTIONS  + " insertions in array having "
            + arraySize + " iTunes." );


      //get start time
      startTime = System.nanoTime();

      // we will do NUM_INSERTIONS insertions, throwing away tunes that run off the top
      for (int attempt = 0; attempt < NUM_INSERTIONS; attempt++)
      {
         writePosition = somePositions[ attempt % numPositions ];

         // move everything up one 
         for (int k = arraySize; k > writePosition; k--)
            tunesArray[k] = tunesArray[k-1];

         // now put a new tune into the free position
         tunesArray[writePosition].setArtist("Amerie");
         tunesArray[writePosition].setTitle("Outro");
         tunesArray[writePosition].setTime(63);
      }

      // how we determine the time elapsed -------------------
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9)
            + " seconds.\n");
   }
}

/* ---------------- Runs -------------------

Doing 5000000 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.2352 seconds.

---------

Doing 500000 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.0327 seconds.

---------

Doing 50000 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.0125 seconds.

---------

Doing 5000 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.0072 seconds.

---------

Doing 500 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.0007 seconds.

---------

Doing 50 insertions in array having 79 iTunes.

Algorithm Elapsed Time: 0.0001 seconds.

---------------------------------------- */
