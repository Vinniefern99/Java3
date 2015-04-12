package Assignment_1;

import java.util.*;

public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      int k, j, numSets, max, kBest, masterSum;
      boolean foundPerfect;
     
      numSets = 0;
      kBest = 0;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);
      
      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);

      //adds all possible sublists to the Array List choices.
      mainLoop:
         for (k = 0 ; k < dataSet.size()-1 ; k++)
            for (j = 0 ; j < choices.size()-1 ; j++)
            {
               System.out.println("hi");
               int newPossibleSum = choices.get(j).getSum() + dataSet.get(k);
               if (newPossibleSum <= target)
               {
                  choices.add(choices.get(j).addItem(dataSet.get(k)));
                  numSets++;
                  
                  if (newPossibleSum > kBest)
                     kBest = newPossibleSum;
               }

               if (newPossibleSum == target)
               {
                  kBest = target;
                  break mainLoop;
               }

            }

         choices.get(kBest).showSublist();
   }
}