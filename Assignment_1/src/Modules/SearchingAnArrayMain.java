package Modules;
// Main file for iTunes project - search example.
// CIS CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class SearchingAnArrayMain
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
      iTunesEntry[] tunesArray = new iTunesEntry[arraySize];
      for (int k = 0; k < arraySize; k++)
         tunesArray[k] = tunesInput.getTune(k);

      // 5 titles we will search for in the larger iTunes list of 80 tunes
      String searchTitle;
      final int NUM_SEARCHES = 5000000;
      String[] someTitles = 
      { 
         "Russian Roulette", 
         "Brahms: Symphony No. 1 in C Minor Op. 68",
         "Give It All U Got",
         "A Love Supreme Part 1",
         "Something to Talk About"
      };
      int numTitles = someTitles.length;

      System.out.println("Doing " + NUM_SEARCHES + " searches in array having "
         + arraySize + " iTunes." );
      
      //get start time
      startTime = System.nanoTime();
      
      // we will do NUM_SEARCHES searches
      for (int attempt = 0; attempt < NUM_SEARCHES; attempt++)
      {
         searchTitle = someTitles[ attempt % numTitles ];
         for (int k = 0; k < arraySize; k++)
         {
            if (tunesArray[k].getTitle().equals(searchTitle))
            {
               // only print out to see that the searches work
               // then we remove for timing
               // System.out.println("found " + searchTitle + " by " 
               //    + tunesArray[k].getSArtist() );
               break;   // found
            }
         }
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

Doing 5000000 searches in array having 79 iTunes.

Algorithm Elapsed Time: 2.9033 seconds

---------

Doing 500000 searches in array having 79 iTunes.

Algorithm Elapsed Time: 0.3116 seconds.

---------

Doing 50000 searches in array having 79 iTunes.

Algorithm Elapsed Time: 0.0385 seconds.

---------

Doing 5000 searches in array having 79 iTunes.

Algorithm Elapsed Time: 0.0086 seconds.

---------

Doing 500 searches in array having 79 iTunes.

Algorithm Elapsed Time: 0.0019 seconds.

---------------------------------------- */