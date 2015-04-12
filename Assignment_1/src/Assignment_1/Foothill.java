package Assignment_1;

import cs_1c.*;
import java.text.*;
import java.util.*;

/*
//------------------------------------------------------
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



public class Foothill
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      //I'm using a temChoice to make a copy of choices.
      //Used in the for loop below
      ArrayList<Sublist> tempChoices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, masterSum;
      boolean foundPerfect = false;

      masterSum = 0;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //setting the first empty set to the kBest
      kBest = 0;

      //adds all possible sublists to the Array List choices.
      mainLoop:
         for (k = 0 ; k < dataSet.size() ; k++)
         {  
            tempChoices.clear();
            for (j = 0 ; j < choices.size() ; j++)
               tempChoices.add((Sublist)choices.get(j).clone());

            for (j = 0 ; j < tempChoices.size() ; j++)
            {
               int newPossibleSum = tempChoices.get(j).getSum() + dataSet.get(k);

               if (newPossibleSum <= target)
               {
                  choices.add(choices.get(j).addItem(dataSet.get(k)));
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

