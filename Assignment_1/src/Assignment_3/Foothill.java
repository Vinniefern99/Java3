package Assignment_3;

import java.text.NumberFormat;
import java.util.*;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

/*
 * Main Foothill class for both Part A and Part B (in that order)
 * 
 * Each Part contains Instructor-provided 5x5 test AND a test for the matrix
 * created with the the two final static variables MAT_SIZE and MAT_SIZE_PART_B.
 * S
 */
public class Foothill
{
   //change these variable to test different sizes
   final static int MAT_SIZE = 100;
   final static int MAT_SIZE_PART_B = 800;

   //don't change these
   final static double DEFAULT_VAL = 0.;
   final static int TEST_MAT_SIZE = 5;

   public static void main(String[] args) throws Exception
   {
      //variables used by both Part A and Part B
      int r, randRow, randCol;
      long startTime, stopTime;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("\n\n** Part A - Mat Mult with Matrices**\n");

      //MAT_SIZE matrix
      double[][] mat, matAns;
      //Instructor-provided 5x5 test
      double[][] mat5x5test1, mat5x5test2, mat5x5testAns;

      //Instructor-provided 5x5 test
      mat5x5test1 = new double[TEST_MAT_SIZE][TEST_MAT_SIZE]; 
      mat5x5test2 = new double[TEST_MAT_SIZE][TEST_MAT_SIZE]; 
      mat5x5testAns = new double[TEST_MAT_SIZE][TEST_MAT_SIZE];

      // allocate the instructor-provided 5x5 matrix
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

      //allocate mat with MAT_SIZE
      mat = new double[MAT_SIZE][MAT_SIZE]; 
      for (int j = 0 ; j < MAT_SIZE ; j++)
         for (int k = 0 ; k < MAT_SIZE ; k++)
            mat[j][k] = DEFAULT_VAL;

      //don't need to allocate values to matAns, just initialize it
      //matMult() will give it values
      matAns = new double[MAT_SIZE][MAT_SIZE];

      //generate small% of non-default values bet 0 and 1 
      //for MAT_SIZE matrix
      smallPercent = MAT_SIZE/10. * MAT_SIZE;
      for (r = 0; r < smallPercent; r++)
      {
         randRow = randIntInRangePartA();
         randCol = randIntInRangePartA();

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

      System.out.println("\n\nMAT_SIZE matrix test:\n");

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

      // ----------------------------------------------------------------------
      // ----------------------------------------------------------------------
      System.out.println("\n\n** Part B - Mat Mult with Sparse Matrices**\n");

      //Instructor-provided 5x5 test matrix
      SparseMatWMult sparseMat5x5Test1, sparseMat5x5Test2, sparseMat5x5TestAns;
      //MAT_SIZE_PART_B matrix
      SparseMatWMult sparseMat, sparseMatAns;

      //Instructor-provided 5x5 test matrix
      sparseMat5x5Test1 = 
            new SparseMatWMult(TEST_MAT_SIZE, TEST_MAT_SIZE, DEFAULT_VAL);
      sparseMat5x5Test2 = 
            new SparseMatWMult(TEST_MAT_SIZE, TEST_MAT_SIZE, DEFAULT_VAL);
      sparseMat5x5TestAns = 
            new SparseMatWMult(TEST_MAT_SIZE, TEST_MAT_SIZE, DEFAULT_VAL);

      //MAT_SIZE_PART_B matrix
      sparseMat = 
            new SparseMatWMult(MAT_SIZE_PART_B, MAT_SIZE_PART_B, DEFAULT_VAL);
      sparseMatAns = 
            new SparseMatWMult(MAT_SIZE_PART_B, MAT_SIZE_PART_B, DEFAULT_VAL);


      // allocate the instructor-provided 5x5 matrix
      sparseMat5x5Test1.set(0, 0, 1.0);
      sparseMat5x5Test1.set(0, 1, 2.0);
      sparseMat5x5Test1.set(0, 2, 3.0);
      sparseMat5x5Test1.set(0, 3, 4.0);
      sparseMat5x5Test1.set(0, 4, 5.0);
      sparseMat5x5Test1.set(1, 0, -1.0);
      sparseMat5x5Test1.set(1, 1, -2.0);
      sparseMat5x5Test1.set(1, 2, -3.0);
      sparseMat5x5Test1.set(1, 3, -4.0);
      sparseMat5x5Test1.set(1, 4, -5.0);
      sparseMat5x5Test1.set(2, 0, 1.0);
      sparseMat5x5Test1.set(2, 1, 3.0);
      sparseMat5x5Test1.set(2, 2, 1.0);
      sparseMat5x5Test1.set(2, 3, 3.0);
      sparseMat5x5Test1.set(2, 4, 1.0);
      sparseMat5x5Test1.set(3, 0, 0.0);
      sparseMat5x5Test1.set(3, 1, 1.0);
      sparseMat5x5Test1.set(3, 2, 0.0);
      sparseMat5x5Test1.set(3, 3, 1.0);
      sparseMat5x5Test1.set(3, 4, 0.0);
      sparseMat5x5Test1.set(4, 0, -1.0);
      sparseMat5x5Test1.set(4, 1, -1.0);
      sparseMat5x5Test1.set(4, 2, -1.0);
      sparseMat5x5Test1.set(4, 3, -1.0);
      sparseMat5x5Test1.set(4, 4, -1.0);

      sparseMat5x5Test2.set(0, 0, 2.0);
      sparseMat5x5Test2.set(0, 1, 1.0);
      sparseMat5x5Test2.set(0, 2, 5.0);
      sparseMat5x5Test2.set(0, 3, 0.0);
      sparseMat5x5Test2.set(0, 4, 2.0);
      sparseMat5x5Test2.set(1, 0, 1.0);
      sparseMat5x5Test2.set(1, 1, 4.0);
      sparseMat5x5Test2.set(1, 2, 3.0);
      sparseMat5x5Test2.set(1, 3, 2.0);
      sparseMat5x5Test2.set(1, 4, 7.0);
      sparseMat5x5Test2.set(2, 0, 4.0);
      sparseMat5x5Test2.set(2, 1, 4.0);
      sparseMat5x5Test2.set(2, 2, 4.0);
      sparseMat5x5Test2.set(2, 3, 4.0);
      sparseMat5x5Test2.set(2, 4, 4.0);
      sparseMat5x5Test2.set(3, 0, 7.0);
      sparseMat5x5Test2.set(3, 1, 1.0);
      sparseMat5x5Test2.set(3, 2, -1.0);
      sparseMat5x5Test2.set(3, 3, -1.0);
      sparseMat5x5Test2.set(3, 4, -1.0);
      sparseMat5x5Test2.set(4, 0, 0.0);
      sparseMat5x5Test2.set(4, 1, 0.0);
      sparseMat5x5Test2.set(4, 2, 8.0);
      sparseMat5x5Test2.set(4, 3, -1.0);
      sparseMat5x5Test2.set(4, 4, -6.0);

      //generate small% of non-default values bet 0 and 1 
      // MAT_SIZE_PART_B matrix
      smallPercent = MAT_SIZE_PART_B/100. * MAT_SIZE_PART_B;
      for (r = 0; r < smallPercent; r++)
      {
         randRow = randIntInRangePartB();
         randCol = randIntInRangePartB();
         sparseMat.set(randRow, randCol, (double)Math.random());
      }

      System.out.println("Instructor-provided 5x5 test matrix:\n");

      startTime = System.nanoTime();
      sparseMat5x5TestAns.matMult(sparseMat5x5Test1, sparseMat5x5Test2);
      stopTime = System.nanoTime();

      sparseMat5x5Test1.showSubSquare(0, 5);
      sparseMat5x5Test2.showSubSquare(0, 5);
      sparseMat5x5TestAns.showSubSquare(0, 5);
      System.out.println("5x5 Test Sparse Mat. Mult. Elapsed Time: "
            + "Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
      System.out.println();


      System.out.println("\n\nMAT_SIZE_PART_B matrix:\n");

      startTime = System.nanoTime();
      sparseMatAns.matMult(sparseMat, sparseMat);
      stopTime = System.nanoTime();

      sparseMat.showSubSquare(MAT_SIZE_PART_B - 10, 10);
      sparseMatAns.showSubSquare(MAT_SIZE_PART_B - 10, 10);
      System.out.println("\nSize = " + MAT_SIZE_PART_B + " Sparse Mat. "
            + "Mult. Elapsed Time: "
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

   private static int randIntInRangePartA()
   {
      Random rand = new Random();
      return rand.nextInt(MAT_SIZE);
   }

   private static int randIntInRangePartB()
   {
      Random rand = new Random();
      return rand.nextInt(MAT_SIZE_PART_B);
   }

}


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
      ListIterator<MatNode> iter, iter2, iter3;
      int curCol, curCol2;

      //if matricies are not squares, return false.
      if (matA.rowSize != matA.colSize || matB.rowSize != matB.colSize 
            || this.rowSize != this.colSize)
         return false;

      // if matricies are not 1x1 or larger
      if (matA.rowSize < 1 || matA.colSize < 1 || matB.rowSize < 1 || 
            matB.colSize < 1 || this.rowSize < 1 || this.colSize < 1)
         return false;

      //if all matricies are not the same size, return false
      if (matA.rowSize != matB.rowSize || matB.rowSize != this.rowSize)
         return false;

      for (int j = 0; j < this.rowSize; j++)
      {
         for (
               iter =  (ListIterator<MatNode>)matA.rows.get(j).listIterator() ;
               iter.hasNext() ;
               // iterate in loop body
               )
         {
            MatNode currNode = iter.next();
            curCol = currNode.col;
            double curData = currNode.data;

            for (
                  iter2 =  
                  (ListIterator<MatNode>)matB.rows.get(curCol).listIterator();
                  iter2.hasNext() ;
                  // iterate in loop body
                  )
            {
               MatNode currNode2 = iter2.next();
               curCol2 = currNode2.col;

               double tempData = curData * currNode2.data;

               for (
                     iter3 =  
                     (ListIterator<MatNode>)this.rows.get(j).listIterator();
                     iter3.hasNext() ;
                     // iterate in loop body
                     )
               {
                  MatNode currNode3 = iter3.next();
                  if (currNode3.col == curCol2)
                     tempData += currNode3.data;
               }

               this.set(j, curCol2, tempData);
            }
         }
      }
      return true;
   }
}


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

-----------Run # 1 with the following settings------------
MAT_SIZE = 100  
MAT_SIZE_PART_B = 800
Sparseness for PartA: 10%
Sparseness for PartB: 1%
----------------------------------------------------------

 ** Part A - Mat Mult with Matrices**

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


MAT_SIZE matrix test:

  0.0   0.0   0.0   0.0   0.0   0.3   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.3   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.4   0.0 
  0.8   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.1   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.3 

  0.0   0.4   1.5   0.0   0.8   0.0   0.0   0.3   0.7   0.0 
  0.0   0.2   0.0   0.3   0.0   0.0   0.0   0.1   0.2   0.0 
  0.0   0.0   0.7   0.0   0.0   0.3   0.1   0.4   0.5   0.1 
  0.3   0.4   1.5   0.1   0.0   0.2   1.3   0.1   0.4   0.0 
  0.0   0.2   0.3   0.0   0.0   0.3   0.5   0.7   0.4   0.0 
  0.0   0.3   0.0   0.0   0.0   0.0   0.2   0.3   0.0   0.1 
  0.7   0.1   0.5   0.1   0.1   0.0   0.4   0.0   0.0   0.5 
  0.4   0.3   0.0   0.0   0.1   0.0   0.1   0.2   0.0   0.0 
  0.0   0.2   0.5   0.0   0.0   0.0   0.0   0.0   0.0   0.2 
  0.0   0.3   0.2   0.4   0.0   0.0   0.0   0.2   0.6   0.1 

Size = 100 Mat. Mult. Elapsed Time: 0.0183 seconds.


 ** Part B - Mat Mult with Sparse Matrices**

Instructor-provided 5x5 test matrix:

  1.0   2.0   3.0   4.0   5.0 
 -1.0  -2.0  -3.0  -4.0  -5.0 
  1.0   3.0   1.0   3.0   1.0 
  0.0   1.0   0.0   1.0   0.0 
 -1.0  -1.0  -1.0  -1.0  -1.0 

  2.0   1.0   5.0   0.0   2.0 
  1.0   4.0   3.0   2.0   7.0 
  4.0   4.0   4.0   4.0   4.0 
  7.0   1.0  -1.0  -1.0  -1.0 
  0.0   0.0   8.0  -1.0  -6.0 

 44.0  25.0  59.0   7.0  -6.0 
-44.0 -25.0 -59.0  -7.0   6.0 
 30.0  20.0  23.0   6.0  18.0 
  8.0   5.0   2.0   1.0   6.0 
-14.0 -10.0 -19.0  -4.0  -6.0 

5x5 Test Sparse Mat. Mult. Elapsed Time: Mult. Elapsed Time: 0.0002 seconds.



MAT_SIZE_PART_B matrix:

  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.3   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 

  0.2   0.0   0.6   0.0   0.0   0.0   0.0   0.0   0.0   0.4 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.5   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 
  0.3   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 


Size = 800 Sparse Mat. Mult. Elapsed Time: 0.0647 seconds.


-----------Run # 2 with the following settings (just run times, no display)---
MAT_SIZE = 200  
MAT_SIZE_PART_B = 800
Sparseness for PartA: 10%
Sparseness for PartB: 10%
----------------------------------------------------------


 ** Part A - Mat Mult with Matrices**

5x5 Test Mat. Mult. Elapsed Time: 0 seconds.

Size = 200 Mat. Mult. Elapsed Time: 0.042 seconds.


 ** Part B - Mat Mult with Sparse Matrices**

5x5 Test Sparse Mat. Mult. Elapsed Time: Mult. Elapsed Time: 0.0001 seconds.

Size = 800 Sparse Mat. Mult. Elapsed Time: 26.2294 seconds.


-----------Run # 3 with the following settings (just run times, no display)---
MAT_SIZE = 300  
MAT_SIZE_PART_B = 1000
Sparseness for PartA: 10%
Sparseness for PartB: 10%
----------------------------------------------------------


 ** Part A - Mat Mult with Matrices**

5x5 Test Mat. Mult. Elapsed Time: 0 seconds.

Size = 300 Mat. Mult. Elapsed Time: 0.0851 seconds.


 ** Part B - Mat Mult with Sparse Matrices**

5x5 Test Sparse Mat. Mult. Elapsed Time: Mult. Elapsed Time: 0.0002 seconds.

Size = 1000 Sparse Mat. Mult. Elapsed Time: 78.6191 seconds.



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

4. How did the data agree or disagree with your original time complexity 
estimate?
The tests are disagreeing with my estimates of time complexity, and my
guess is that I cannot go high enough to get an accurate measurement without
the memory crashing. 

// ------------------Further Analysis for Part B:--------------------

---- Part B Tables showing various Sparse Times ------

Sparsness: 1%:
 *M*      *Time in Seconds*
100      0.0003
200      0.0041
400      0.0323 
800      0.077
1600     0.5983
3200     20.6314

Sparsness: 10%:
 *M*      *Time in Seconds*
100      0.0696
200      0.1567
400      1.4778
800      30.3014

Sparseness 25%:
 *M*      *Time in Seconds*
100      0.0983
200      0.4498
400      8.6621
800      157.2481

With Part B, the sparseness seriously affects the time complexity because
the higher the sparseness, the more iterations occur and the closer to 
the upper bound it gets. I could not compute theta() of Part B, but the
O() is O(N^3) because I have 3 for loops that are all iterate M times,
if it's the upper bound. But I could not compute theta() because it seems like
it depends on the sparseness.

 */