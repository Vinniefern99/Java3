//Main file for iTunes project.  See Read Me file for details
//CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class iTunesProjectMain
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
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

      System.out.println(tunesInput.getFileName());
      System.out.println(tunesInput.getNumTunes());

      // create an array of objects for our own use:
      arraySize = tunesInput.getNumTunes();
      iTunesEntry[] tunesArray = new iTunesEntry[arraySize];
      for (int k = 0; k < arraySize; k++)
         tunesArray[k] = tunesInput.getTune(k);

      // how we time our algorithms -------------------------
      Date startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // show the array, unsorted
      for (int k = 0; k < arraySize; k++)
         System.out.println(tunesArray[k]);
      System.out.println();

      //get start time
      startTime = new Date();

      // do something interesting like search or sort or build a hash-table, then...

      // how we determine the time elapsed -------------------
      stopTime = new Date();

      // show the sorted list
      for (int k = 0; k < arraySize; k++)
         System.out.println(tunesArray[k]);
      System.out.println();

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + tidy.format((stopTime.getTime() - startTime.getTime()) / 1000.)
            + " seconds.\n");
   }
}