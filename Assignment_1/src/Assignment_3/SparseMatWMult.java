package Assignment_3;
import java.util.ListIterator;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

//--------------- Class SparseMatWMult Definition ---------------
//(uses SparseMat solution from previous assignment)
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

