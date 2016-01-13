package Assignment_1;

import cs_1c.*;
import java.text.*;
import java.util.*;



//******    Part A - Main Foothill_Main1 Client    *******
public class Foothill_Main1
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

      System.out.println("Target int: " + target);
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
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

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
               break;
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


/* ---------------------- Test Runs for Part A ---------------------------

Target int: 72
Sublist -------------------
  Sum: 72
  array[0] = 2
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[6] = 9
  array[7] = 19


Target int: 13
Sublist -------------------
  Sum: 12
  array[1] = 12


Target int: 7900000
Sublist -------------------
   Sum: 138
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[7] = 19
  array[8] = 29


Target int: 0
Sublist -------------------
  Sum: 0


Target int: 46
Sublist -------------------
  Sum: 46
  array[2] = 22
  array[4] = 15
  array[6] = 9


Target int: 8
Sublist -------------------
  Sum: 7
  array[0] = 2
  array[3] = 5


Target int: 18
Sublist -------------------
  Sum: 17
  array[1] = 12
  array[3] = 5


Target int: 120
Sublist -------------------
  Sum: 119
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[8] = 29


Target int: 104
Sublist -------------------
  Sum: 104
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[7] = 19

-------------------------------------------------------------------------- */





/*


//---------------------------------------------------------------------------
//------------------------------- PART B ------------------------------------
//---------------------------------------------------------------------------


//******    Part B - Sublist class    ******
class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   int getSum() 
   { 
      return sum; 
   }

   Sublist addItem( int indexOfItemToAdd ) throws CloneNotSupportedException
   { 
      Sublist newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum = newSublist.sum 
            + originalObjects.get(indexOfItemToAdd).getTime();

      return newSublist;
   }

   void showSublist()
   { 
      for (int k = 0 ; k < indices.size() ; k++)
         System.out.println("  array[" + indices.get(k) + "] = " 
               + originalObjects.get(indices.get(k)).toString());
   }

   // I have done the clone() for you, since you will need it inside addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();

      return newObject;
   }
};


//******    Part B - Main Foothill_Main1 Client    ******
public class Foothill_Main1
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
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
      //since algorithm doesn't run, there will be no time displayed
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
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

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
               break;
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


/* ---------------------- Test Runs for Part B ---------------------------

Target time: 3600
Sublist -------------------
  Sum: 3600
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[14] = Janiva Magness | You Were Never Mine |  4:36
  array[15] = John Lee Hooker | Hobo Blues |  3:07
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02

Algorithm Elapsed Time: 0.0591 seconds.


Target time: 2600
Sublist -------------------
  Sum: 2600
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50

Algorithm Elapsed Time: 0.0221 seconds.


Target time: 520000000
Sublist -------------------
  Sum: 22110
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[14] = Janiva Magness | You Were Never Mine |  4:36
  array[15] = John Lee Hooker | Hobo Blues |  3:07
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02
  array[17] = Snoop Dogg | That's The Homie |  5:43
  array[18] = Snoop Dogg | Gangsta Luv |  4:17
  array[19] = The Rubyz | Ladies and Gentleman |  3:21
  array[20] = The Rubyz | Watch the Girl |  3:12
  array[21] = Veggie Tales | Donuts for Benny |  3:04
  array[22] = Veggie Tales | Our Big Break |  1:09
  array[23] = Berliner Philharmoniker | Brahms: Symphony No. 1 in C Minor Op. 68
 |  13:59
  array[24] = Berliner Philharmoniker | Brahms: Symphony No. 4 in E Minor Op. 98
 |  13:20
  array[25] = Yo-yo Ma | Bach: Suite for Cello No. 1 in G Major Prelude |  2:21
  array[26] = Yo-yo Ma | Simple Gifts |  2:34
  array[27] = Ry Cooter | Alimony |  2:55
  array[28] = Ry Cooter | France Chance |  2:48
  array[29] = Aaron Watson | The Road |  3:24
  array[30] = Terra Incognita | Clone |  4:58
  array[31] = Terra Incogni | Lizard Skin |  4:30
  array[32] = Blue Record | Bullhead's Psalm |  1:19
  array[33] = Blue Record | Ogeechee Hymnal |  2:35
  array[34] = Mastadon | Oblivion |  5:48
  array[35] = Mastadon | The Bit |  4:55
  array[36] = Sean Kingston | Fire Burning |  3:59
  array[37] = Sean Kingston | My Girlfriend |  3:24
  array[38] = T-Pain | Take Your Shirt Off |  3:48
  array[39] = Lil Jon | Give It All U Got |  3:38
  array[40] = Jay-Z | What We Talkin' About |  4:03
  array[41] = Jay-Z | Empire State of Mind |  4:36
  array[42] = Snoop Dog | Think About It |  3:37
  array[43] = Snoop Dog | Lil' Crips |  3:15
  array[44] = Jeff Golub | Shuffleboard |  3:30
  array[45] = Jeff Golub | Goin' On |  5:56
  array[46] = Jeff Golub | Fish Fare |  4:59
  array[47] = Caraivana | Noites Cariocas |  4:12
  array[48] = Caraivana | Tico-Tico No Fuba |  2:27
  array[49] = John Patitucci | Monk/Trane |  7:14
  array[50] = John Patitucci | Sonny Side |  7:25
  array[51] = Nina Simone | Pirate Jenny |  6:42
  array[52] = Nina Simone | The Other Woman |  3:06
  array[53] = Nina Simone | Feeling Good |  2:57
  array[54] = John Coltrane | A Love Supreme Part 1 |  7:42
  array[55] = John Coltrane | In a Sentimental Mood |  4:16
  array[56] = AOL Dejando Huellas | Dime Si te Vas Con El |  3:24
  array[57] = AOL Dejando Huella | Te Amo Tanto |  3:12
  array[58] = McCoy Tyner | Blues On the Corner |  6:07
  array[59] = McCoy Tyner | Afro Blue |  12:22
  array[60] = Kanye West | Stronger |  5:11
  array[61] = Kanye West | Good Life |  3:27
  array[62] = Steely Dan | Black Cow |  5:10
  array[63] = Steely Dan | Kid Charlemagne |  4:38
  array[64] = Steely Dan | Haitian Divorce |  5:51
  array[65] = Herbie Hancock | Nefertiti |  7:31
  array[66] = Herbie Hancock | Rockit |  5:25
  array[67] = Herbie Hancock | Chameleon |  15:41
  array[68] = Return to Forever | Medieval Overture |  5:13
  array[69] = Suzanne Vega | Luka |  3:51
  array[70] = Suzanne Vega | Small Blue Thing |  3:55
  array[71] = Bonnie Raitt | Something to Talk About |  3:47
  array[72] = Bonnie Raitt | I Can't Make You Love Me |  5:31
  array[73] = Natalie Cole | This Will Be |  2:51
  array[74] = Natalie Cole | Unforgettable |  3:31
  array[75] = Jet | Timothy |  4:20
  array[76] = Jet | Rip It Up |  3:20
  array[77] = Was (Not Was) | Where Did Your Heart Go? |  5:47
  array[78] = Was (Not Was) | I Blew Up The United States |  3:50


Target time: 510
Sublist -------------------
  Sum: 510
  array[3] = Foo Fighters | All My Life |  4:23
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[22] = Veggie Tales | Our Big Break |  1:09

Algorithm Elapsed Time: 0.0012 seconds.


Target time: 7
Sublist -------------------
  Sum: 0

Algorithm Elapsed Time: 0 seconds.


Target time: 0
Sublist -------------------
  Sum: 0

Algorithm Elapsed Time: 0 seconds.


Target time: 212
Sublist -------------------
  Sum: 211
  array[74] = Natalie Cole | Unforgettable |  3:31

Algorithm Elapsed Time: 0.0015 seconds.


Target time: 2467
Sublist -------------------
  Sum: 2467
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23

Algorithm Elapsed Time: 0.0186 seconds.

---------------------------------------------------------------------------*/




/*



//---------------------------------------------------------------------------
//------------------------------- PART C ------------------------------------
//---------------------------------------------------------------------------


//******    Part C - Generic Sublist class   ******
class Sublist<E> implements Cloneable
{
   private int sum = 0;
   private E originalObjects;
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(E orig) 
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   int getSum() 
   { 
      return sum; 
   }

   ArrayList<Integer> getIndices() 
   { 
      return indices; 
   }

   Sublist<E> addItem( int indexOfItemToAdd, int amountToAdd) 
         throws CloneNotSupportedException
         { 
      Sublist<E> newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum = newSublist.sum + amountToAdd;

      return newSublist;
         }

   <F> void showSublist(int index, F toDisplay)
   { 
      System.out.println("  array[" + index + "] = "  + toDisplay);
   }

   // I have done the clone() for you, since you will need it inside addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();

      return newObject;
   }
};


// ******    Part C - Main Foothill_Main1 Client with Ints    ******
public class Foothill_Main1
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist<Integer>> choices = new ArrayList<Sublist<Integer>>();
      int k, j, numSets, max, kBest, masterSum, tempNumsets, newPossibleSum;
      boolean foundPerfect = false;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      System.out.println("Target int: " + target);
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
      Sublist<Integer> newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() + dataSet.get(k);

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k, dataSet.get(k)));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               //System.out.println(choices.size()-1);
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
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

      ArrayList<Integer> tempIndices = choices.get(kBest).getIndices();

      for (k = 0 ; k < tempIndices.size() ; k++)
         choices.get(kBest).showSublist(tempIndices.get(k), 
               dataSet.get(tempIndices.get(k)));

   }
}


/* ---------------------- Test Runs for Part C (ints) -----------------------

Target int: 72
Sublist -------------------
  Sum: 72
  array[0] = 2
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[6] = 9
  array[7] = 19


Target int: 744
Sublist -------------------
   Sum: 138
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[7] = 19
  array[8] = 29


Target int: 0
Sublist -------------------
  Sum: 0


Target int: 18
Sublist -------------------
  Sum: 17
  array[1] = 12
  array[3] = 5


Target int: 62
Sublist -------------------
  Sum: 62
  array[2] = 22
  array[4] = 15
  array[5] = 25


Target int: 754
Sublist -------------------
   Sum: 138
  array[0] = 2
  array[1] = 12
  array[2] = 22
  array[3] = 5
  array[4] = 15
  array[5] = 25
  array[6] = 9
  array[7] = 19
  array[8] = 29

---------------------------------------------------------------------------*/
/*

//******    Part C - Main Foothill_Main1 Client with iTunesEntrys    ******
public class Foothill_Main1
{
   // -------  main --------------
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      int target = 3600;
      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      ArrayList<Sublist<iTunesEntry>> choices = new 
            ArrayList<Sublist<iTunesEntry>>();

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

      //test if target is larger than sum of all elements in the master set.
      //since algorithm doesn't run, there will be no time displayed
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
      Sublist<iTunesEntry> newSublist = new Sublist(dataSet);
      choices.add(newSublist);
      numSets = 1;

      // START TIME -------------------------------
      startTime = System.nanoTime();

      //adds all possible sublists to choices.
      kBest = 0;
      for (k = 0 ; k < dataSet.size() ; k++)
      {  
         if (foundPerfect)
            break;

         tempNumsets = numSets;

         for (j = 0 ; j < tempNumsets ; j++)
         {             
            newPossibleSum = choices.get(j).getSum() 
                  + dataSet.get(k).getTime();

            if (newPossibleSum <= target)
            {
               choices.add(choices.get(j).addItem(k, dataSet.get(k).getTime()));
               numSets++;
            }

            if (newPossibleSum == target)
            {
               kBest = choices.size()-1;
               foundPerfect = true;
               break;
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

      ArrayList<Integer> tempIndices = choices.get(kBest).getIndices();

      for (k = 0 ; k < tempIndices.size() ; k++)
         choices.get(kBest).showSublist(tempIndices.get(k), 
               dataSet.get(tempIndices.get(k)).toString());

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9)
            + " seconds.\n");

   }
}


/* ----------------- Test Runs for Part C (iTunesEntrys) ------------------

Target time: 3600
Sublist -------------------
  Sum: 3600
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[14] = Janiva Magness | You Were Never Mine |  4:36
  array[15] = John Lee Hooker | Hobo Blues |  3:07
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02

Algorithm Elapsed Time: 0.063 seconds.


Target time: 76124355
Sublist -------------------
  Sum: 22110
  array[0] = Carrie Underwood | Cowboy Casanova |  3:56
  array[1] = Carrie Underwood | Quitter |  3:40
  array[2] = Rihanna | Russian Roulette |  3:48
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[5] = Eric Clapton | Pretending |  4:43
  array[6] = Eric Clapton | Bad Love |  5:08
  array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
  array[8] = Howlin' Wolf | Well That's All Right |  2:55
  array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
  array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
  array[11] = Roy Buchanan | Hot Cha |  3:28
  array[12] = Roy Buchanan | Green Onions |  7:23
  array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
  array[14] = Janiva Magness | You Were Never Mine |  4:36
  array[15] = John Lee Hooker | Hobo Blues |  3:07
  array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02
  array[17] = Snoop Dogg | That's The Homie |  5:43
  array[18] = Snoop Dogg | Gangsta Luv |  4:17
  array[19] = The Rubyz | Ladies and Gentleman |  3:21
  array[20] = The Rubyz | Watch the Girl |  3:12
  array[21] = Veggie Tales | Donuts for Benny |  3:04
  array[22] = Veggie Tales | Our Big Break |  1:09
  array[23] = Berliner Philharmoniker | Brahms: Symphony No. 1 in C Minor Op. 68
 |  13:59
  array[24] = Berliner Philharmoniker | Brahms: Symphony No. 4 in E Minor Op. 98
 |  13:20
  array[25] = Yo-yo Ma | Bach: Suite for Cello No. 1 in G Major Prelude |  2:21
  array[26] = Yo-yo Ma | Simple Gifts |  2:34
  array[27] = Ry Cooter | Alimony |  2:55
  array[28] = Ry Cooter | France Chance |  2:48
  array[29] = Aaron Watson | The Road |  3:24
  array[30] = Terra Incognita | Clone |  4:58
  array[31] = Terra Incogni | Lizard Skin |  4:30
  array[32] = Blue Record | Bullhead's Psalm |  1:19
  array[33] = Blue Record | Ogeechee Hymnal |  2:35
  array[34] = Mastadon | Oblivion |  5:48
  array[35] = Mastadon | The Bit |  4:55
  array[36] = Sean Kingston | Fire Burning |  3:59
  array[37] = Sean Kingston | My Girlfriend |  3:24
  array[38] = T-Pain | Take Your Shirt Off |  3:48
  array[39] = Lil Jon | Give It All U Got |  3:38
  array[40] = Jay-Z | What We Talkin' About |  4:03
  array[41] = Jay-Z | Empire State of Mind |  4:36
  array[42] = Snoop Dog | Think About It |  3:37
  array[43] = Snoop Dog | Lil' Crips |  3:15
  array[44] = Jeff Golub | Shuffleboard |  3:30
  array[45] = Jeff Golub | Goin' On |  5:56
  array[46] = Jeff Golub | Fish Fare |  4:59
  array[47] = Caraivana | Noites Cariocas |  4:12
  array[48] = Caraivana | Tico-Tico No Fuba |  2:27
  array[49] = John Patitucci | Monk/Trane |  7:14
  array[50] = John Patitucci | Sonny Side |  7:25
  array[51] = Nina Simone | Pirate Jenny |  6:42
  array[52] = Nina Simone | The Other Woman |  3:06
  array[53] = Nina Simone | Feeling Good |  2:57
  array[54] = John Coltrane | A Love Supreme Part 1 |  7:42
  array[55] = John Coltrane | In a Sentimental Mood |  4:16
  array[56] = AOL Dejando Huellas | Dime Si te Vas Con El |  3:24
  array[57] = AOL Dejando Huella | Te Amo Tanto |  3:12
  array[58] = McCoy Tyner | Blues On the Corner |  6:07
  array[59] = McCoy Tyner | Afro Blue |  12:22
  array[60] = Kanye West | Stronger |  5:11
  array[61] = Kanye West | Good Life |  3:27
  array[62] = Steely Dan | Black Cow |  5:10
  array[63] = Steely Dan | Kid Charlemagne |  4:38
  array[64] = Steely Dan | Haitian Divorce |  5:51
  array[65] = Herbie Hancock | Nefertiti |  7:31
  array[66] = Herbie Hancock | Rockit |  5:25
  array[67] = Herbie Hancock | Chameleon |  15:41
  array[68] = Return to Forever | Medieval Overture |  5:13
  array[69] = Suzanne Vega | Luka |  3:51
  array[70] = Suzanne Vega | Small Blue Thing |  3:55
  array[71] = Bonnie Raitt | Something to Talk About |  3:47
  array[72] = Bonnie Raitt | I Can't Make You Love Me |  5:31
  array[73] = Natalie Cole | This Will Be |  2:51
  array[74] = Natalie Cole | Unforgettable |  3:31
  array[75] = Jet | Timothy |  4:20
  array[76] = Jet | Rip It Up |  3:20
  array[77] = Was (Not Was) | Where Did Your Heart Go? |  5:47
  array[78] = Was (Not Was) | I Blew Up The United States |  3:50


Target time: 86
Sublist -------------------
  Sum: 79
  array[32] = Blue Record | Bullhead's Psalm |  1:19

Algorithm Elapsed Time: 0.0005 seconds.


Target time: 976
Sublist -------------------
  Sum: 976
  array[3] = Foo Fighters | All My Life |  4:23
  array[4] = Foo Fighters | Monkey Wrench |  3:50
  array[6] = Eric Clapton | Bad Love |  5:08
  array[8] = Howlin' Wolf | Well That's All Right |  2:55

Algorithm Elapsed Time: 0.0009 seconds.


---------------------------------------------------------------------------*/




