package Assignment_2;

/*
 *  
//client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foorhill_ModelAnswer
{
final static int MAT_SIZE = 100000;
// -------  main --------------
public static void main(String[] args) throws Exception
{
   // 100000 x 100000 filled with 0
   int k;
   SparseMat<Double> mat
      = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.);
   
   // test constructor error
   System.out.println("Test constructor");
   try
   {
      SparseMat<Double> matBad
         = new SparseMat<Double>(MAT_SIZE, -MAT_SIZE, 0.);
   }
   catch( IllegalArgumentException e)
   {
      System.out.println("oops - bad arg in constructor");
   }

   // test mutators
   for (k = 0; k < 10; k++)
   {
      mat.set(k, k, k * 1.);
      mat.set(4, k, k * 10.);
      mat.set(k, 4, -k * 10.);
   }
   
   // test accessors and exceptions
   System.out.println("\nTest get()");
   try
   {
      System.out.println( mat.get(7, 8) );
      System.out.println(  mat.get(4, 3) );
      System.out.println( mat.get(9, 9) );

      // should throw an exception
      System.out.println(  mat.get(-4, 7) );
   }
   catch( IndexOutOfBoundsException e)
   {
      System.out.println("oops - bounds in get()");
   }
   
   System.out.println("\nFirst 12x12 subsquare of original");
   mat.showSubSquare(0, 12);
   System.out.println();
   
   SparseMat<Double> mat2 = (SparseMat<Double>)mat.clone();
   
   for (k = 0; k < 10; k++)
   {
      mat.set(k, k, 1.);
      mat.set(4, k, 10.);
      mat.set(k, 4, -10.);
   }
   
   System.out.println("First 12x12, 1st 10 of diagonal & 4th r/c changed:");
   mat.showSubSquare(0, 12);
   System.out.println();
   System.out.println("Cloned 12x12  UN-changed:");
   mat2.showSubSquare(0, 12);
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

/* ---------------- Sample Run Featuring clone() ----------------------

Test constructor
oops - bad arg in constructor

Test get()
0.0
30.0
9.0
oops - bounds in get()

First 12x12 subsquare of original
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0
0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0


First 12x12, 1st 10 of diagonal & 4th r/c changed:
1.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   1.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   1.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
10.0  10.0  10.0  10.0 -10.0  10.0  10.0  10.0  10.0  10.0   0.0   0.0
0.0   0.0   0.0   0.0 -10.0   1.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -10.0   0.0   1.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -10.0   0.0   0.0   1.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   1.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   1.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0


Cloned 12x12  UN-changed:
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0
0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

---------------------------------------------------------- */
