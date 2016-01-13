package Assignment_2;

import java.util.ListIterator;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;


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

   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      MatNode newObject = (MatNode)super.clone();
      return (Object) newObject;
   }


}


/* Version 2


//--------------- Class SparseMat Definition ---------------
class SparseMat<E> implements Cloneable
{
   //protected enables us to safely make col/data public
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

   //constructor creates an empty Sublist (no indices)
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

   //optional method
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


*/