package Assignment_2;

import java.util.*;

import cs_1c.*;

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


   protected int j, k, rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList < FHlinkedList< MatNode > > rows;


   final int MAX_ROW_OR_COLUMN_SIZE = 1000000;
   final int DEFAULT_ROW_OR_COL_SIZE = 100;

   ListIterator<SparseMat<E>.MatNode> p;
   ListIterator<FHlinkedList<SparseMat<E>.MatNode>> q;

   // A constructor that establishes a size (row size and column size both > 1) 
   // as well as a default value for all elements.  It will allocate all the 
   // memory of an empty matrix by calling a private utility void allocateEmptyMatrix().
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
      {
         rows.add(new FHlinkedList<MatNode>()); 
      }

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
   public E get(int row, int column)
   {
      if (row > rowSize || column > colSize)
         throw new IndexOutOfBoundsException();

      FHlinkedList<MatNode> currentRow = rows.get(row);

      for (p = currentRow.listIterator() ; p.hasNext() ; )
      {
         if (p.next().column == column)
            return p.previous().data;

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

   //  a display method that will show a square sub-matrix anchored at 
   // (start, start) and whose size is size x size.  In other words it will 
   // show the rows from start to start + size -1 and the columns from 
   // start to start + size - 1.  This is mostly for debugging purposes 
   // since we obviously cannot see the entire matrix at once.
   public void showSubSquare(int start, int size) 
   {
      for (k = start ; k < (start + size) ; k++ )
      {
         for (j = start ; j < (start + size) ; j++)
         {
            double intToDisplay = (double)get(k, j);
            if (intToDisplay < 10)
               System.out.print("  " + intToDisplay);
            else
               System.out.print(" " + intToDisplay);      
         }
         System.out.println();
      }
   }

   
   //Part B - clone()
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      SparseMat<E> newObject = (SparseMat<E>)super.clone();
      // deep copy
      
      for (int k = 0 ; k < this.rows.size() ; k++)
      {
         for (j = 0 ; j < this.rows.get(k).size() ; j ++)
         {
            newObject.rows.get(k).get(j).column = this.rows.get(k).get(j).column;
            newObject.rows.get(k).get(j).data = this.rows.get(k).get(j).data;
 
         }
         
      }

      return newObject;
   }

}