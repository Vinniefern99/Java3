package Assignment_3;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Foothill
{
   final static int MAT_SIZE = 300;

   public static void main(String[] args) throws Exception
   {
      int r, randRow, randCol;
      long startTime, stopTime;
      double defaultVal;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // non-sparse matrices
      double[][] mat, matAns;

      defaultVal = 0;

      // allocate matrices
      mat = new double[MAT_SIZE][MAT_SIZE]; 
      for (int j = 0 ; j < MAT_SIZE ; j++)
         for (int k = 0 ; k < MAT_SIZE ; k++)
            mat[j][k] = defaultVal;

      //don't need to allocate values to matAns, just initialize it
      //matMult() will give it values
      matAns = new double[MAT_SIZE][MAT_SIZE];

      // generate small% of non-default values bet 0 and 1
      smallPercent = MAT_SIZE/10. * MAT_SIZE;
      for (r = 0; r < smallPercent; r++)
      {
         //test if the position has already been modified
         while (mat[randRow = randIntInRange()][randCol = randIntInRange()] 
               != defaultVal) {}

         mat[randRow][randCol] = (double)Math.random();
      }

      System.out.println("**** Submatrices before multiplication: ****\n");
      // 5x5 submatrix in lower right
      System.out.println("5x5 submatrix in lower right "
            + "(for testing matricies with 5x5)");
      matShow(mat, MAT_SIZE - 5, 5);

      // 10x10 submatrix in lower right
      System.out.println("10x10 submatrix in lower right "
            + "(for testing matricies >= 10x10)");
      matShow(mat, MAT_SIZE - 10, 10);

      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      System.out.println("\n**** Submatrices after multiplication: ****\n");
      // 5x5 submatrix in lower right
      System.out.println("5x5 submatrix in lower right "
            + "(for testing matricies with 5x5)");
      matShow(matAns, MAT_SIZE - 5, 5);

      // 10x10 submatrix in lower right
      System.out.println("10x10 submatrix in lower right "
            + "(for testing matricies >= 10x10)");
      matShow(matAns, MAT_SIZE - 10, 10);

      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
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

      for (int i = 0; i < matC.length; i++) {
         for (int j = 0; j < matC[0].length; j++) {
            for (int k = 0; k < matA[0].length; k++) {
               matC[i][j] += (matA[i][k] * matB[k][j]);
            }
         }
      }

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

---------------Run # 1 (M = 100) --------------

 **** Submatrices before multiplication: ****

5x5 submatrix in lower right (for testing matricies with 5x5)
  0.0   0.0   0.0   0.0   0.0 
  0.6   0.0   0.0   0.0   0.5 
  0.2   0.0   0.0   0.0   0.0 
  0.7   0.0   0.0   0.0   0.7 
  0.0   0.0   0.0   0.0   0.0 

10x10 submatrix in lower right (for testing matricies >= 10x10)
  0.3   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   1.0 
  0.6   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.1   0.0   0.0   0.0   0.0   0.7   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.2   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.6   0.0   0.0   0.0   0.5 
  0.0   0.2   0.0   0.0   0.0   0.2   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.7   0.0   0.0   0.0   0.7 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 


 **** Submatrices after multiplication: ****

5x5 submatrix in lower right (for testing matricies with 5x5)
  0.2   0.5   0.0   0.4   0.1 
  0.8   0.0   0.3   0.0   0.5 
  0.2   1.2   0.2   0.4   0.0 
  0.5   0.3   0.2   0.0   0.0 
  0.0   0.0   0.1   0.0   0.0 

10x10 submatrix in lower right (for testing matricies >= 10x10)
  0.9   0.8   0.0   0.2   0.1   0.0   0.0   0.0   0.0   0.3 
  1.1   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.2   0.6 
  0.0   0.0   0.4   0.5   0.0   0.1   0.1   0.3   0.0   0.0 
  0.2   0.3   0.0   0.0   0.0   0.1   0.1   0.0   0.0   0.1 
  0.2   0.0   0.2   0.6   1.4   0.6   0.0   0.6   0.0   0.7 
  0.0   0.3   0.1   0.4   0.2   0.2   0.5   0.0   0.4   0.1 
  0.5   0.6   0.0   0.0   0.0   0.8   0.0   0.3   0.0   0.5 
  0.4   0.3   0.5   0.6   0.0   0.2   1.2   0.2   0.4   0.0 
  0.3   0.0   0.0   0.0   0.0   0.5   0.3   0.2   0.0   0.0 
  0.4   0.2   0.0   0.2   0.0   0.0   0.0   0.1   0.0   0.0 


Size = 100 Mat. Mult. Elapsed Time: 0.0191 seconds.

---------------Run # 2 (M = 5) --------------

 **** Submatrices before multiplication: ****

5x5 submatrix in lower right (for testing matricies with 5x5)
  0.0   0.0   0.1   0.0   0.0 
  0.0   0.0   0.0   0.0   0.3 
  0.8   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 

10x10 submatrix in lower right (for testing matricies >= 10x10)

 **** Submatrices after multiplication: ****

5x5 submatrix in lower right (for testing matricies with 5x5)
  0.1   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.1   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 

10x10 submatrix in lower right (for testing matricies >= 10x10)

Size = 5 Mat. Mult. Elapsed Time: 0 seconds.

-------Run #3 (M = 200) (without displaying matrices, per spec details)------

Size = 200 Mat. Mult. Elapsed Time: 0.0592 seconds.

-------Run #4 (M = 300) (without displaying matrices, per spec details)------

Size = 300 Mat. Mult. Elapsed Time: 0.1939 seconds.



// -------------------Answers to Spec Questions --------------------------

 **Time complexity of the matMult() function :

The if statement is considered constant, so we can forget about that when
computing time complexity. What we need to look at are the three for loops, 
one nested within the second, which is nested within in the third. The inner 
for loop is O(M), and so is the second one, and so is the third. So we multiply
O(M * M * M), and we get O(M^3). Because each for loop will iterate exactly M
times EACH AND EVERY time, that means Θ() is also Θ(M^3). Θ() would only 
different if there were a chance that the for loops iterated less than M
each time. But that's not the case here.

 **Answers to questions in spec:

1. What was the smallest M that gave you a non-zero time?
10 is the smallest M and it gets 0.0001 seconds.

2. What happened when you doubled M, tripled it, quadrupled it, etc?  
Give several M values and their times in a table.

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

M time table, starting with 20 and increasing by 20^n
 *M*      *Time in Seconds*
20       0.0004
400      0.5058
8000     OUT OF MEMORY

M time table, starting with 5 and increasing by 5^n
 *M*      *Time in Seconds*
5        0
25       0.0089
125      0.0234
625      2.6974


3. How large an M can you use before the program refuses to run 
(exception or run-time error due to memory overload) or it takes so 
long you can't wait for the run?
I was able to go up to 1500, which took 47.7805 seconds. I didn't want to try
to go higher and have to wait that long...

4. How did the data agree or disagree with your original time complexity estimate?



 */