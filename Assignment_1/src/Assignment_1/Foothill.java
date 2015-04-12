package Assignment_1;

import cs_1c.*;
import java.text.*;
import java.util.*;

//------------------- Part A Client - ArrayList of ints -------------------

public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, masterSum, tempNumsets;
      boolean foundPerfect = false;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      //test if target is larger than the sum of all elements in the master set.
      masterSum = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
         masterSum = masterSum + dataSet.get(k);
      if (masterSum < target)
      {
         System.out.println("Sum of all elements in master set is too small.");
         System.out.println("Add more elements or try a smaller target.");
         System.exit(0);
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
               int newPossibleSum = choices.get(j).getSum() + dataSet.get(k);

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
               
      
      //for (k = 0 ; k < choices.size() ; k++)
      //{
      //   choices.get(k).showSublist();
      //   System.out.println(k); 
      //   System.out.println(choices.get(k).getSum());
      //   System.out.println();
     // }
         
      //System.out.println(choices.get(kBest).getSum());

      choices.get(kBest).showSublist();
   }
}


/*

//----------------- Part B Client - ArrayList of ItunesEntries ------

public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      //I'm using a temChoice to make a copy of choices.
      //Used in the for loop below
      ArrayList<Sublist> tempChoices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, arraySize, masterSum;
      boolean foundPerfect;

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

      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //adds all possible sublists to the Array List choices.
      mainLoop:
         for (k = 0 ; k < dataSet.size() ; k++)
         {  
            tempChoices.clear();
            for (j = 0 ; j < choices.size() ; j++)
               tempChoices.add((Sublist)choices.get(j).clone());

            for (j = 0 ; j < tempChoices.size() ; j++)
            {
               int newPossibleSum = tempChoices.get(j).getSum() + dataSet.get(k).getTime();

               if (newPossibleSum <= target)
               {
                  choices.add(choices.get(j).addItem(dataSet.get(k).getTime()));
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


      if (!foundPerfect)
         for (k = 0 ; k < choices.size() ; k++)
            if (choices.get(k).getSum() > masterSum)
               kBest = k;

      choices.get(kBest).showSublist();
   }
}

 */


