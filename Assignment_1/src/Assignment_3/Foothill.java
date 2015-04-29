package Assignment_3;

import java.text.NumberFormat;
import java.util.*;

public class Foothill
{
   //change this variable to test different sizes
   final static int MAT_SIZE = 100;

   //don't change these
   final static double DEFAULT_VAL = 0.;
   final static int TEST_MAT_SIZE = 5;

   public static void main(String[] args) throws Exception
   {
      int r, randRow, randCol;
      long startTime, stopTime;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // non-sparse matrices
      double[][] mat, matAns, mat5x5test1, mat5x5test2, mat5x5testAns;

      // Here's the manual 5x5 matrix to test algorithm  accuracy
      mat5x5test1 = new double[TEST_MAT_SIZE][TEST_MAT_SIZE]; 
      mat5x5test1[0][0] = 1.0;
      mat5x5test1[0][1] = 2.0;
      mat5x5test1[0][2] = 3.0;
      mat5x5test1[0][3] = 4.0;
      mat5x5test1[0][4] = 5.0;
      mat5x5test1[1][0] = -1.0;
      mat5x5test1[1][1] = -2.0;
      mat5x5test1[1][2] = -3.0;
      mat5x5test1[1][3] = -4.0;
      mat5x5test1[1][4] = -5.0;
      mat5x5test1[2][0] = 1.0;
      mat5x5test1[2][1] = 3.0;
      mat5x5test1[2][2] = 1.0;
      mat5x5test1[2][3] = 3.0;
      mat5x5test1[2][4] = 1.0;
      mat5x5test1[3][0] = 0.0;
      mat5x5test1[3][1] = 1.0;
      mat5x5test1[3][2] = 0.0;
      mat5x5test1[3][3] = 1.0;
      mat5x5test1[3][4] = 0.0;
      mat5x5test1[4][0] = -1.0;
      mat5x5test1[4][1] = -1.0;
      mat5x5test1[4][2] = -1.0;
      mat5x5test1[4][3] = -1.0;
      mat5x5test1[4][4] = -1.0;

      mat5x5test2 = new double[TEST_MAT_SIZE][TEST_MAT_SIZE]; 
      mat5x5test2[0][0] = 2.0;
      mat5x5test2[0][1] = 1.0;
      mat5x5test2[0][2] = 5.0;
      mat5x5test2[0][3] = 0.0;
      mat5x5test2[0][4] = 2.0;
      mat5x5test2[1][0] = 1.0;
      mat5x5test2[1][1] = 4.0;
      mat5x5test2[1][2] = 3.0;
      mat5x5test2[1][3] = 2.0;
      mat5x5test2[1][4] = 7.0;
      mat5x5test2[2][0] = 4.0;
      mat5x5test2[2][1] = 4.0;
      mat5x5test2[2][2] = 4.0;
      mat5x5test2[2][3] = 4.0;
      mat5x5test2[2][4] = 4.0;
      mat5x5test2[3][0] = 7.0;
      mat5x5test2[3][1] = 1.0;
      mat5x5test2[3][2] = -1.0;
      mat5x5test2[3][3] = -1.0;
      mat5x5test2[3][4] = -1.0;
      mat5x5test2[4][0] = 0.0;
      mat5x5test2[4][1] = 0.0;
      mat5x5test2[4][2] = 8.0;
      mat5x5test2[4][3] = -1.0;
      mat5x5test2[4][4] = -6.0;

      //this matrix will be whatever size you put in MAT_SIZE
      mat = new double[MAT_SIZE][MAT_SIZE]; 
      for (int j = 0 ; j < MAT_SIZE ; j++)
         for (int k = 0 ; k < MAT_SIZE ; k++)
            mat[j][k] = DEFAULT_VAL;

      //don't need to allocate values to matAns, just initialize it
      //matMult() will give it values
      matAns = new double[MAT_SIZE][MAT_SIZE];

      //This is the 5x5 test matrix
      mat5x5testAns = new double[TEST_MAT_SIZE][TEST_MAT_SIZE];


      // generate small% of non-default values bet 0 and 1
      smallPercent = MAT_SIZE/10. * MAT_SIZE;
      for (r = 0; r < smallPercent; r++)
      {
         randRow = randIntInRange();
         randCol = randIntInRange();

         mat[randRow][randCol] = (double)Math.random();
      }

      System.out.println("Instructor-provided 5x5 test matrix:\n");

      startTime = System.nanoTime();
      matMult(mat5x5test1, mat5x5test2, mat5x5testAns);
      stopTime = System.nanoTime();

      System.out.println(" First 5x5 matrix, n:");
      matShow(mat5x5test1, 0, 5);
      System.out.println(" Second 5x5 matrix, m:");
      matShow(mat5x5test2, 0, 5);
      System.out.println(" Product 5x5 Matrix, n x m:");
      matShow(mat5x5testAns, 0, 5);

      System.out.println("5x5 Test Mat. Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");


      System.out.println("\n\nOutput for custom matrix:\n");

      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      //Note: 10x10 display(lower right) will only work when matrix is >= 10x10
      //lower right sub-matrix before multiplication:
      matShow(mat, MAT_SIZE - 10, 10);
      //lower right sub-matrix after multiplication:
      matShow(matAns, MAT_SIZE - 10, 10);

      System.out.println("Size = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
   }

   public static void matMult( double[][] matA, double[][] matB, 
         double[][] matC)
   {
      //minimal testing as outlined in the spec.
      if (matA.length != matB.length)
      {
         System.out.println("Unable to multiply. Number of rows in first "
               + "matrix must be equal the number of rows in second matrix.");
         return;
      }

      for (int i = 0; i < matC.length; i++) 
         for (int j = 0; j < matC[0].length; j++) 
            for (int k = 0; k < matA[0].length; k++) 
               matC[i][j] += (matA[i][k] * matB[k][j]);

   }

   public static void matShow(double[][] matA, int start, int size)
   {
      int row, col;

      if (start < 0 || size < 0
            || start + size > matA[0].length
            || start + size > matA.length)
         return;

      for (row = start; row < start + size; row++)
      {
         for (col = start; col < start + size; col++)
            System.out.print( String.format("%5.1f",
                  (Double)matA[row][col]) + " " );
         System.out.println();
      }
      System.out.println();
   }

   private static int randIntInRange()
   {
      Random rand = new Random();
      return rand.nextInt(MAT_SIZE);
   }

}


/*

// -------------------------Test Runs -------------------------------

-------------Run # 1 (Instructor- provided M=5 and an M = 100) --------------

Instructor-provided 5x5 test matrix:

 First 5x5 matrix, n:
  1.0   2.0   3.0   4.0   5.0 
 -1.0  -2.0  -3.0  -4.0  -5.0 
  1.0   3.0   1.0   3.0   1.0 
  0.0   1.0   0.0   1.0   0.0 
 -1.0  -1.0  -1.0  -1.0  -1.0 

 Second 5x5 matrix, m:
  2.0   1.0   5.0   0.0   2.0 
  1.0   4.0   3.0   2.0   7.0 
  4.0   4.0   4.0   4.0   4.0 
  7.0   1.0  -1.0  -1.0  -1.0 
  0.0   0.0   8.0  -1.0  -6.0 

 Product 5x5 Matrix, n x m:
 44.0  25.0  59.0   7.0  -6.0 
-44.0 -25.0 -59.0  -7.0   6.0 
 30.0  20.0  23.0   6.0  18.0 
  8.0   5.0   2.0   1.0   6.0 
-14.0 -10.0 -19.0  -4.0  -6.0 

5x5 Test Mat. Mult. Elapsed Time: 0 seconds.


Output for custom matrix:

  0.0   0.0   0.0   0.0   0.0   0.0   0.6   0.0   0.0   0.0 
  0.0   0.0   0.3   0.9   0.0   0.0   0.0   0.3   0.1   0.0 
  0.0   0.0   0.0   0.0   0.5   0.0   0.0   0.0   0.0   0.6 
  0.0   0.0   0.5   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.2   0.0   0.0   0.0 
  0.1   0.0   0.0   0.0   0.0   0.0   0.5   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.2   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.5 

  0.1   0.0   0.0   0.0   0.0   0.1   0.3   0.0   0.7   0.0 
  0.5   1.4   1.0   0.5   0.2   0.0   0.0   0.0   0.6   0.4 
  0.7   0.0   1.0   0.0   0.6   0.3   0.3   0.1   0.7   0.4 
  0.0   0.1   0.0   0.1   0.4   0.2   0.4   0.1   0.3   0.9 
  0.0   0.0   0.5   0.0   0.0   0.0   0.0   0.0   0.4   0.0 
  0.6   0.1   0.7   0.6   0.4   0.3   0.2   0.8   0.4   0.0 
  0.2   1.7   0.3   0.7   0.1   0.5   0.3   0.0   0.8   0.0 
  0.2   0.4   0.0   0.7   0.0   0.0   0.1   0.0   0.2   0.1 
  0.3   0.1   1.2   0.7   0.2   0.0   0.0   0.0   0.1   0.0 
  0.8   0.5   0.1   0.1   0.2   0.0   0.0   0.3   1.2   0.3 

Size = 100 Mat. Mult. Elapsed Time: 0.0197 seconds.


-------Run #2 (M = 200) (without displaying matrices, per spec details)------

Size = 200 Mat. Mult. Elapsed Time: 0.0592 seconds.


-------Run #3 (M = 300) (without displaying matrices, per spec details)------

Size = 300 Mat. Mult. Elapsed Time: 0.1939 seconds.



// -------------------Answers to Spec Questions --------------------------

 **Time complexity of the matMult() function :

The if statement is considered constant, so we can forget about that when
computing time complexity. What we need to look at are the three for loops, 
one nested within the second, which is nested within in the third. The inner 
for loop is O(M), and so is the second one, and so is the third. So we multiply
O(M * M * M), and we get O(M^3). Because each for loop will iterate exactly M
times EACH AND EVERY time, that means O() is also Theta(M^3). Theta() would only 
different if there were a chance that the for loops iterated less than M
each time. But that's not the case here.

I noticed that run times differed on separate machines. My home computer
was a slower than my work computer.

 **Answers to questions in spec:

1. What was the smallest M that gave you a non-zero time?
10 is the smallest M and it gets 0.0001 seconds.

2. What happened when you doubled M, tripled it, quadrupled it, etc?  
Give several M values and their times in a table.

(runs on my home computer)
M time table in factors of 20
 *M*      *Time in Seconds*
20       0.0004
40       0.0048
60       0.0197
80       0.0205
100      0.0202

M time table in factors of 100
 *M*     *Time in Seconds*
100      0.0196 
200      0.0524
300      0.1832
400      0.5064
500      1.0483
600      2.2182
700      3.8047
800      5.9566
900      8.6754
1000     12.0664
1100     16.8258
1200     21.9245
1300     31.2198 
1400     37.5636
1500     47.7805
1600     59.4802

Doubling M each time:
 *M*      *Time in Seconds*
100      0.0196
200      0.0524
400      0.5064
800      5.9566
1600     59.4802

Squaring M each time:
 *M*      *Time in Seconds*
20       0.0004
400      0.5058

Squaring M each time:
 *M*      *Time in Seconds*
5        0
25       0.0089
125      0.0234
625      2.6974

(runs on my work computer)
Doubling M each time:
 *M*      *Time in Seconds*
100      0.0199
200      0.0353
400      0.1788
800      4.5272
1600     74.138
3200

Doubling M each time:
 *M*      *Time in Seconds*
2        0
4        0
8        0
16       0.0003
32       0.0018
64       0.0158
128      0.0388
256      0.0484
512      0.5096
1024     13.989
2048     172.7487

Doubling M each time:
 *M*      *Time in Seconds*
10       0.0001
20       0.0005
40       0.0028
80       0.0145
160      0.0353
320      0.1004 
640      1.0513
1280     34.2569  


3. How large an M can you use before the program refuses to run 
(exception or run-time error due to memory overload) or it takes so 
long you can't wait for the run?
I was able to go up to 2048, which took 172.7487 seconds. I didn't want to try
to go higher and have to wait that long...

4. How did the data agree or disagree with your original time complexity estimate?
The tests are disagreeing with my estimates of time complexity, and my
guess is that I cannot go high enough to get an accurate measurement without
the memory crashing. 


 */