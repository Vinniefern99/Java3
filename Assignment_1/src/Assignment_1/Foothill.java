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

      masterSum = 0;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      Sublist newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //setting the first empty set to the best
      kBest = 0;

      //System.out.println(dataSet.size());

      //adds all possible sublists to the Array List choices.
      mainLoop:
         for (k = 0 ; k < dataSet.size() ; k++)
         {
            ArrayList<Sublist> tempChoices = new ArrayList<Sublist>();
            tempChoices = (ArrayList<Sublist>)choices.clone();
            
            for (j = 0 ; j < choices.size() ; j++)
            {
               //System.out.println(k);
               int newPossibleSum = choices.get(j).getSum() + dataSet.get(k);
               //System.out.println(newPossibleSum);
               if (newPossibleSum <= target)
               {
                  //System.out.println("test");
                  choices.add(choices.get(j).addItem(dataSet.get(k)));
               }

               if (newPossibleSum == target)
               {
                  masterSum = target;
                  foundPerfect = true;
                  break mainLoop;
               }

               //System.out.println(choices.size());

            }
         }


      //System.out.println(choices.size());

      

      //choices.get(6).showSublist();

      //System.out.println(kBest);
      choices.get(kBest).showSublist();
   }
}