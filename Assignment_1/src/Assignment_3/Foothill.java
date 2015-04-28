package Assignment_3;


import java.text.NumberFormat;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;

import cs_1c.*;

public class Foothill
{
   final static int MAT_SIZE = 400;

   final static int MIN_SIZE_FOR_10X10_DISPLAY = 10;
   final static int MIN_SIZE_FOR_5X5_DISPLAY = 5;

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

      defaultVal = 0.;

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
         randRow = randIntInRange();
         randCol = randIntInRange();

         mat[randRow][randCol] = (double)Math.random();
      }

      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      System.out.println("** Part A **\n");

      if (MAT_SIZE >= MIN_SIZE_FOR_10X10_DISPLAY)
      {
         // 10x10 submatrix in lower right
         System.out.println("10x10 submatrix(lower right) before multiplication:");
         matShow(mat, MAT_SIZE - 10, 10);

         // 10x10 submatrix in lower right
         System.out.println("10x10 submatrix(lower right) after multiplication:");
         matShow(matAns, MAT_SIZE - 10, 10);
      }

      if (MAT_SIZE >= MIN_SIZE_FOR_5X5_DISPLAY)
      {
         // 5x5 submatrix in lower right
         System.out.println("5x5 submatrix(lower right) before multiplication:");
         matShow(mat, MAT_SIZE - 5, 5);

         System.out.println("5x5 submatrix(lower right) after multiplication:");
         matShow(matAns, MAT_SIZE - 5, 5);
      }

      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");


      System.out.println("\n\n** Part B **\n");


      //System.out.println(matAns[397][399]);

      SparseMatWMult sparseMat, sparseMatAns;

      sparseMat = new SparseMatWMult(MAT_SIZE, MAT_SIZE, defaultVal);
      sparseMatAns = new SparseMatWMult(MAT_SIZE, MAT_SIZE, defaultVal);

      for (r = 0; r < smallPercent; r++)
      {
         randRow = randIntInRange();
         randCol = randIntInRange();

         sparseMat.set(randRow, randCol, (double)Math.random());
      }

      sparseMat.showSubSquare(10, 10);
      sparseMatAns.matMult(sparseMat, sparseMat);

      sparseMatAns.showSubSquare(10, 10);

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

// -------------------Part B --------------------------

class SparseMatWMult extends SparseMat<Double>
{
   // constructor ??

   public SparseMatWMult(int numRows, int numCols, Double defaultVal)
   {
      super(numRows, numCols, defaultVal);
      // TODO Auto-generated constructor stub
   }

   // multiply:
   public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
   {
      ListIterator<MatNode> iter;


      for (int j = 0; j < this.rowSize; j++)
      {
         for (int k = 0 ; k < this.rowSize ; k++)
         {
            double totalForCurrNode = defaultVal;

            for (int l = 0; l < this.rowSize ; l++)
            {
               if (matA.get(j,l) != this.defaultVal &&
                     matB.get(l, k) != this.defaultVal)
                  totalForCurrNode += matA.get(j,l) * matB.get(l, k);
            }  

            if (totalForCurrNode != defaultVal)
               this.set(j, k, totalForCurrNode);
         }
      }

      return true;

   }
}


//--------------- Class SparseMat Definition ---------------
class SparseMat<E> implements Cloneable
{
   // protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;

      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }

      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode)super.clone();
         return (Object) newObject;
      }
   }

   static public final int MIN_SIZE = 1;
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList < FHlinkedList< MatNode > > rows;

   public int getRowSize() { return rowSize; }
   public int getColSize() { return colSize; }

   // constructor creates an empty Sublist (no indices)
   public SparseMat( int numRows, int numCols, E defaultVal)
   {
      if ( numRows < MIN_SIZE || numCols < MIN_SIZE || defaultVal == null)
         throw new IllegalArgumentException();

      rowSize = numRows;
      colSize = numCols;
      allocateEmptyMatrix();
      this.defaultVal = defaultVal;
   }

   protected void allocateEmptyMatrix()
   {
      int row;
      rows = new FHarrayList < FHlinkedList< MatNode > >();
      for (row = 0; row < rowSize; row++)
         rows.add( new FHlinkedList< MatNode >());   // add a blank row
   }

   public void clear()
   {
      int row;

      for (row = 0; row < rowSize; row++)
         rows.get(row).clear();
   }

   // optional method
   public Object clone() throws CloneNotSupportedException
   {
      int row;
      ListIterator<MatNode> iter;
      FHlinkedList < MatNode > newRow;

      // shallow copy
      SparseMat<E> newObject = (SparseMat<E>)super.clone();

      // create all new lists for the new object
      newObject.allocateEmptyMatrix();

      // deep stuff
      for (row = 0; row < rowSize; row++)
      {
         newRow = newObject.rows.get(row);
         for (
               iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ;
               iter.hasNext() ;
               // iterate in loop body
               )
         {
            newRow.add( (MatNode) iter.next().clone() );
         }
      }

      return newObject;
   }

   protected boolean valid(int row, int col)
   {
      if (row >= 0 && row < rowSize && col >= 0 && col < colSize)
         return true;
      return false;
   }

   public boolean set(int row, int col, E x)
   {
      if (!valid(row, col))
         return false;

      ListIterator<MatNode> iter;

      // iterate along the row, looking for column col
      for (
            iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ;
            iter.hasNext() ;
            // iterate in loop body
            )
      {
         if ( iter.next().col == col )
         {
            if ( x.equals(defaultVal) )
               iter.remove();
            else
               iter.previous().data = x;
            return true;
         }
      }

      // not found
      if ( !x.equals(defaultVal) )
         rows.get(row).add( new MatNode(col, x) );
      return true;
   }

   public E get(int row, int col)
   {
      if (!valid(row, col))
         throw new IndexOutOfBoundsException();

      ListIterator<MatNode> iter;

      // iterate along the row, looking for column col
      for (
            iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ;
            iter.hasNext() ;
            // iterate in loop body
            )
      {
         if ( iter.next().col == col )
            return iter.previous().data;
      }
      // not found
      return defaultVal;
   }

   public void showSubSquare(int start, int size)
   {
      int row, col;

      if (start < 0 || size < 0
            || start + size > rowSize
            || start + size > colSize )
         return;

      for (row = start; row < start + size; row++)
      {
         for (col = start; col < start + size; col++)
            System.out.print( String.format("%5.1f",
                  (Double)get(row, col)) + " " );
         System.out.println();
      }
      System.out.println();
   }
}




/*

// -------------------------Test Runs -------------------------------

---------------Run # 1 (M = 100) --------------

10x10 submatrix(lower right) before multiplication:
  0.0   0.0   0.0   0.0   0.0   0.0   0.2   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.2   0.0   0.0   0.6   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.9   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.5   0.0   0.2   0.0   0.0   0.2 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.4   0.9   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.9 
  0.8   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 

10x10 submatrix(lower right) after multiplication:
  0.0   0.0   0.8   0.6   0.7   0.4   0.4   0.0   0.0   0.5 
  0.0   0.1   0.4   0.0   0.0   0.0   0.0   0.4   0.2   0.0 
  0.0   0.0   0.8   0.0   0.2   0.5   0.0   0.0   0.0   0.0 
  0.0   0.2   0.0   0.0   0.7   0.0   0.2   0.0   0.0   0.0 
  0.7   0.0   0.8   0.0   0.0   0.5   0.0   0.0   0.6   0.6 
  0.2   0.0   0.0   0.0   0.0   0.0   0.0   0.5   0.0   0.4 
  0.0   0.0   0.0   0.0   0.0   0.5   0.2   0.5   0.5   0.0 
  0.0   0.1   0.0   0.0   0.4   0.6   0.1   0.8   0.3   0.5 
  0.7   0.0   0.2   0.1   0.0   0.4   0.2   0.6   0.0   0.0 
  0.0   0.0   0.1   0.7   0.1   0.0   0.1   0.0   0.0   0.0 

5x5 submatrix(lower right) before multiplication:
  0.0   0.2   0.0   0.0   0.2 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.9 
  0.0   0.0   0.0   0.0   0.0 

5x5 submatrix(lower right) after multiplication:
  0.0   0.0   0.5   0.0   0.4 
  0.5   0.2   0.5   0.5   0.0 
  0.6   0.1   0.8   0.3   0.5 
  0.4   0.2   0.6   0.0   0.0 
  0.0   0.1   0.0   0.0   0.0 


Size = 100 Mat. Mult. Elapsed Time: 0.0155 seconds.


---------------Run # 2 (M = 5) --------------

5x5 submatrix(lower right) before multiplication:
  0.0   0.0   0.3   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.4   0.0 
  0.0   0.0   0.0   0.0   0.1 
  0.0   0.0   0.0   0.0   0.0 

5x5 submatrix(lower right) after multiplication:
  0.0   0.0   0.0   0.1   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0 


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
1600     59.4802

M time table, doubling previous one
100      0.0196
200      0.0524
400      0.5064
800      5.9566
1600     59.4802


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