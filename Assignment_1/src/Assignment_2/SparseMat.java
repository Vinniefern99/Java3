package Assignment_2;

import java.util.ListIterator;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;
import cs_1c.FHlinkedList.Node;

//--------------- Class SparseMat Definition ---------------
public class SparseMat<E> implements Cloneable
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


   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList < FHlinkedList< MatNode > > rows;


   final int MAX_ROW_OR_COLUMN_SIZE = 1000;
   final int DEFAULT_ROW_OR_COL_SIZE = 100;
   private int k, numRows;

   // A constructor that establishes a size (row size and column size both > 1) 
   // as well as a default value for all elements.  It will allocate all the 
   // memory of an empty matrix by calling a private utility void allocateEmptyMatrix().
   public void SparseMat( int numRows, int numCols, E defaultVal) 
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

      defaultVal = defaultVal;
      allocateEmptyMatrix();

   }

   //used by SparseMat constructor!
   private void allocateEmptyMatrix()
   {
      for (k = 0 ; k < numRows ; k++)
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
   // It throws an IndexOutOfBoundsException  
   // if matrix bounds (row and/or column) are violated.
   public E get(int r, int c)
   {
      if (r < rowSize || c > colSize)
         throw new IndexOutOfBoundsException();

      ListIterator<SparseMat<E>.MatNode> p;
      
      FHlinkedList<MatNode> currentRow = rows.get(r);

      for (p = currentRow.listIterator() ; p.hasNext() ; )
      {
         
      }
      
      return defaultVal;

   }

   // A mutator that places x in row r and column c.   It returns false 
   // without an exception if bounds are violated.  Also, if x is the default 
   // value it will remove any existing node (the internal data type 
   // used by SparseMat) from the data structure, since there is never a 
   // need to store the default value explicitly.  Of course, if there is 
   // no node present in the internal data representation, set() will add 
   // one if x is not default and store x in it.
   public boolean set(int r, int c, E x) 
   {

   }

   // clears all the rows, effectively setting all values to the 
   // defaultVal(but leaves the matrix size unchanged).
   public void clear()
   {

   }

   //  a display method that will show a square sub-matrix anchored at 
   // (start, start) and whose size is size x size.  In other words it will 
   // show the rows from start to start + size -1 and the columns from 
   // start to start + size - 1.  This is mostly for debugging purposes 
   // since we obviously cannot see the entire matrix at once.
   public void showSubSquare(int start, int size) 
   {

   }

   //I'm guessing I'll have to add shit here
}