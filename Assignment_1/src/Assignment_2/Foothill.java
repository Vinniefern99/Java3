package Assignment_2;

//CIS 1C Assignment #2 
//Vince Fernald, studentID 20217838
//Due Date: April 22, 2pm
//Loceff

import cs_1c.*;
import java.util.*;


//---------------------- SparseMat generic class -------------------------

class SparseMat<E>
{
   // protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int column;
      public E data;

      // we need a default constructor for lists
      MatNode()
      {
         column = 0;
         data = null;
      }

      MatNode(int col, E data)
      {
         column = col;
         this.data = data;
      }

      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode)super.clone(); 
         return (Object) newObject;
      }
   };


   protected int j, k, rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList < FHlinkedList< MatNode > > rows;


   final int MAX_ROW_OR_COLUMN_SIZE = 1000000;
   final int DEFAULT_ROW_OR_COL_SIZE = 100;

   ListIterator<SparseMat<E>.MatNode> p;
   ListIterator<FHlinkedList<SparseMat<E>.MatNode>> q;

   // A constructor that establishes a size (row size and column size both > 1) 
   public SparseMat( int numRows, int numCols, E defaultVal) 
   {
      if (isValidSize(numRows, numCols))
      {
         rowSize = numRows;
         colSize = numCols;
      }
      else
      {
         rowSize = DEFAULT_ROW_OR_COL_SIZE;
         colSize = DEFAULT_ROW_OR_COL_SIZE;
      }

      this.defaultVal = defaultVal;

      allocateEmptyMatrix();

   }

   //used by SparseMat constructor!
   private void allocateEmptyMatrix()
   {
      rows = new FHarrayList < FHlinkedList< MatNode > >();

      for (k = 0 ; k < rowSize ; k++)
         rows.add(new FHlinkedList<MatNode>()); 
   }

   // (row size and column size both > 1)
   // adding a max
   private boolean isValidSize(int rowSize, int colSize)
   {
      if (rowSize <= 1 || rowSize > MAX_ROW_OR_COLUMN_SIZE || 
            colSize <= 1 || colSize > MAX_ROW_OR_COLUMN_SIZE)
         return false;
      return true;
   }

   // An accessor that returns the object stored in row r and column c.  
   public E get(int row, int column)
   {
      if (row > rowSize || column > colSize)
         throw new IndexOutOfBoundsException();

      FHlinkedList<MatNode> currentRow = rows.get(row);

      for (p = currentRow.listIterator() ; p.hasNext() ; )
         if (p.next().column == column)
            return p.previous().data;

      return defaultVal;
   }

   // A mutator that places x in row r and column c. 
   public boolean set(int row, int column, E x) 
   {
      if (row > rowSize || column > colSize)
         return false;

      FHlinkedList<MatNode> currentRow = rows.get(row);

      for (int k = 0 ; k < currentRow.size() ; k++)
      {
         MatNode tempNode = currentRow.get(k);

         if (tempNode.column == column)
         {
            currentRow.remove(k);
            currentRow.add(k, new MatNode(column, x));
            return true;
         }   
      }

      if (x != defaultVal)
         currentRow.add(new MatNode(column, x));

      return true;
   }

   // clears all the rows, effectively setting all values to the 
   // defaultVal(but leaves the matrix size unchanged).
   public void clear()
   {
      for (q = rows.listIterator() ; q.hasNext() ; )
         q.next().clear();
   }

   public void showSubSquare(int start, int size) 
   {
      for (k = start ; k < (start + size) ; k++ )
      {
         for (j = start ; j < (start + size) ; j++)
         {
            Double intToDisplay = (Double)get(k, j);
            if (intToDisplay < 10)
               System.out.print("  " + intToDisplay);
            else
               System.out.print(" " + intToDisplay);      
         }
         System.out.println();
      }
   }

}



//--------------------------- Client ------------------------------

public class Foothill
{
   final static int MAT_SIZE = 100000;
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k;
      SparseMat<Double> mat 
      = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); 

      //test mutators
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, k*1.);
         mat.set(4, k, k*10.);
         mat.set(k, 4, -k*10.);
      }

      //print upper left
      System.out.println("Upper left 12x12 matrix:");
      mat.showSubSquare(0, 12);

      //test individual set
      mat.set(6,2,-5.0);
      System.out.println("\nTesting set() on r: 6, c:2 :");
      System.out.println("Node at row 6, column 2:");
      System.out.println(mat.get(6,2));

      //test multiple sets() to replace existing nodes
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, 1.);
         mat.set(4, k, 10.);
         mat.set(k, 4, -10.);
      }
      System.out.println("\nResult upper left matrix after many replacments:");
      mat.showSubSquare(0, 12);


      //print lower right
      System.out.println("\nLower right 10 x 10 matrix:");
      mat.showSubSquare(99990, 10);
      System.out.println("\nLower right 10 x 10 matrix after changing\n" + 
            " matrix r:99991, c:99991 to 8.4:");
      mat.set(99991,99991, 8.4);
      mat.showSubSquare(99990, 10);

      //test clear
      mat.clear();
      System.out.println("\nTesting clear()");
      System.out.println("Now printing lower right 10 x 10 after clear:\n" +
            "(the value 8.4 in r:99991, c:99991 changes to 0.0");
      mat.showSubSquare(99990, 10);

      //testing true/false return for index error in set()
      //Should return "False"
      System.out.println("\nTrying to set() out of index range:");
      if (mat.set(100000,100005, 3.))
         System.out.println("\nTrue");
      else
         System.out.println("\nFalse");

   }
}


/* ----------- Test Run (only one run. many tests in this run) ---------------

Upper left 12x12 matrix:
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  1.0  0.0  0.0  -10.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  2.0  0.0  -20.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  3.0  -30.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0 10.0 20.0 30.0  -40.0 50.0 60.0 70.0 80.0 90.0  0.0  0.0
  0.0  0.0  0.0  0.0  -50.0  5.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -60.0  0.0  6.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -70.0  0.0  0.0  7.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -80.0  0.0  0.0  0.0  8.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -90.0  0.0  0.0  0.0  0.0  9.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

Testing set() on r: 6, c:2 :
Node at row 6, column 2:
-5.0

Result upper left matrix after many replacments:
  1.0  0.0  0.0  0.0  -10.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  1.0  0.0  0.0  -10.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  1.0  0.0  -10.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  1.0  -10.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
 10.0 10.0 10.0 10.0  -10.0 10.0 10.0 10.0 10.0 10.0  0.0  0.0
  0.0  0.0  0.0  0.0  -10.0  1.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  -5.0  0.0  -10.0  0.0  1.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -10.0  0.0  0.0  1.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -10.0  0.0  0.0  0.0  1.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  -10.0  0.0  0.0  0.0  0.0  1.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

Lower right 10 x 10 matrix:
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

Lower right 10 x 10 matrix after changing
 matrix r:99991, c:99991 to 8.4:
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  8.4  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

Testing clear()
Now printing lower right 10 x 10 after clear:
(the value 8.4 in r:99991, c:99991 changes to 0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

Trying to set() out of index range:

False

/* ------------------------------------------------------------------------ */