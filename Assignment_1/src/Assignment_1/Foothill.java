package Assignment_1;

import cs_1c.*;

import java.text.*;
import java.util.*;



/*
 * Part A - Main Foothill Client
 */
public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      System.out.println("Target time: " + target);
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
      mainLoop:
         for (k = 0 ; k < dataSet.size() ; k++)
         {  
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
                  break mainLoop;
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




/*
 * Part B - Main Foothill Client
 *
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 36755;
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
      mainLoop:
         for (k = 0 ; k < dataSet.size() ; k++)
         {  
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
                  break mainLoop;
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


 */

