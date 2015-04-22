package Assignment_2;

import java.util.*;

import cs_1c.*;

//--------------- Class SparseMat Definition ---------------
public class SparseMat<E>
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




/* ---------------------- Test Runs for Part A ---------------------------






/* ---------------------- Test Runs for Part A --------------------------- */