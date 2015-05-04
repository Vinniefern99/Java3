package Assignment_3_ModelAnswer;

/*
//CIS 1C Assignment #3
//Instructor Solution Client 1
//PROOF OF CORRECTNESS -- BOTH SPARSE AND NON-SPARSE
//------------------------------------------------------
import java.util.*;
import cs_1c.*;




public class Foothill
{
   final static int MAT_SIZE = 5;

   // -------  proof of correctness --------------
   public static void main(String[] args) throws Exception
   {
      int row, col;

      // non-sparse matrices
      double[][] matAns = new double[MAT_SIZE][MAT_SIZE];
      double[][]
            mat1 =
         {
            {1, 2, 3, 4, 5},
            {-1, -2, -3, -4, -5},
            {1, 3, 1, 3, 1},
            {0, 1, 0, 1, 0},
            {-1, -1, -1, -1, -1}
         };
      double[][] mat2 =
         {
            {2, 1, 5, 0, 2},
            {1, 4, 3, 2, 7},
            {4, 4, 4, 4, 4},
            {7, 1, -1, -1, -1},
            {0, 0, 8, -1, -6}
         };

      matShow(mat1, 0, 5);
      matShow(mat2, 0, 5);
      matMult(mat1, mat2, matAns);
      matShow(matAns, 0, 5);

      // sparse matrices
      SparseMatWMult mSparseMat, nSparseMat, matAnsS;
      mSparseMat = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      nSparseMat = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      matAnsS = new SparseMatWMult(MAT_SIZE, MAT_SIZE);

      for (row = 0; row < MAT_SIZE; row++)
         for (col = 0; col < MAT_SIZE; col++)
         {
            mSparseMat.set(row, col, mat1[row][col]);
            nSparseMat.set(row, col, mat2[row][col]);
         }
      matAnsS.matMult(mSparseMat, nSparseMat);

      mSparseMat.showSubSquare(0, 5);
      nSparseMat.showSubSquare(0, 5);
      matAnsS.showSubSquare(0, 5);
   }

   public static void matMult( double[][] matA,  double[][] matB,
         double[][] matC)
   {
      int row, col, k, size;
      float dotProd;

      size = matA[0].length;
      if ( size != matB[0].length || size != matC[0].length)
         return;
      for (row = 0; row < size; row++)
         for (col = 0; col < size; col++)
         {
            for (k = 0, dotProd = 0; k < size; k++)
               dotProd += matA[row][k] * matB[k][col];
            matC[row][col] = dotProd;
         }
   }

   public static void matShow(double[][] matA, int start, int size)
   {
      int row, col;
      String strFormattedDbl;

      for (row = start; row < start + size; row++)
      {
         for (col = start; col < start + size; col++)
         {
            strFormattedDbl = String.format("%6.2f", matA[row][col]);
            System.out.print(strFormattedDbl + " ");
         }
         System.out.println();
      }
      System.out.println();
   }
}

/* ---------------- run proof of correctness ------------------

1.00   2.00   3.00   4.00   5.00
-1.00  -2.00  -3.00  -4.00  -5.00
1.00   3.00   1.00   3.00   1.00
0.00   1.00   0.00   1.00   0.00
-1.00  -1.00  -1.00  -1.00  -1.00

2.00   1.00   5.00   0.00   2.00
1.00   4.00   3.00   2.00   7.00
4.00   4.00   4.00   4.00   4.00
7.00   1.00  -1.00  -1.00  -1.00
0.00   0.00   8.00  -1.00  -6.00

44.00  25.00  59.00   7.00  -6.00
-44.00 -25.00 -59.00  -7.00   6.00
30.00  20.00  23.00   6.00  18.00
8.00   5.00   2.00   1.00   6.00
-14.00 -10.00 -19.00  -4.00  -6.00

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

------------------------------------------------------------ */


/*



//CIS 1C Assignment #3
//Instructor Solution Client 2
//TIMING NON-SPARSE
//------------------------------------------------------
import java.util.*;
import cs_1c.*;
import java.text.*;

public class Foothill
{
   final static int MAT_SIZE = 800;

   // -------  proof of correctness --------------
   public static void main(String[] args) throws Exception
   {
      int row, col, randRow, randCol;
      long startTime, stopTime;
      double randFrac;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // non-sparse matrices
      double[][] mat, matAns;

      mat = new double[MAT_SIZE][MAT_SIZE];
      matAns = new double[MAT_SIZE][MAT_SIZE];

      // initialize to 0 explicitly if this code appears mid-program)
      for (row = 0; row < MAT_SIZE; row++)
         for (col = 0; col < MAT_SIZE; col++)
            matAns[row][col] = 0;

      // generate small% of non-default values bet 0 and 1
      smallPercent = MAT_SIZE/10. * MAT_SIZE;
      for (row = 0; row < smallPercent; row++)
      {
         randFrac = Math.random();
         randRow =  (int)(randFrac * MAT_SIZE);
         randFrac = Math.random();
         randCol =  (int)(randFrac * MAT_SIZE);
         randFrac = Math.random();
         mat[randRow][randCol] = randFrac;
      }

      // 10x10 submatrix in lower right
      matShow(mat, MAT_SIZE - 10, 10);

      startTime = System.nanoTime();
      matMult(mat, mat, matAns);
      stopTime = System.nanoTime();

      matShow(matAns, MAT_SIZE - 10, 10);

      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
   }

   // support methods as above
}

//note:  timing will not change based on sparseness. confirm this.

/* ------------------  a few runs and times (10%) -------------------

0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.00 0.00 0.00 0.00 0.00 0.90 0.00 0.26 0.00
0.00 0.96 0.00 0.00 0.00 0.00 0.61 0.00 0.00 0.00
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.00 0.41 0.07 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.00 0.28 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.69 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00 0.00
0.00 0.03 0.00 0.00 0.00 0.06 0.00 0.00 0.00 0.00

1.82 3.14 0.79 3.19 2.30 3.24 2.62 2.10 2.90 2.26
1.79 2.11 1.79 2.61 2.11 2.90 0.87 1.18 3.18 1.20
0.70 1.90 2.14 3.58 2.79 1.70 3.31 2.18 2.41 0.78
3.14 2.22 0.78 1.89 1.94 0.74 3.21 2.35 1.92 2.36
1.87 3.46 2.05 2.55 2.19 1.65 3.64 1.29 1.84 1.52
0.57 1.50 1.79 0.89 2.55 0.91 2.13 2.17 0.42 2.25
1.85 3.67 1.65 2.70 2.72 0.49 3.46 2.61 1.67 3.12
0.83 2.37 1.72 2.92 0.20 0.87 2.21 1.00 1.90 1.76
1.45 1.90 2.12 1.85 2.52 0.51 1.34 1.39 1.09 1.45
0.59 0.91 0.66 1.49 2.35 2.07 2.07 2.34 1.13 2.03


Size = 100 Mat. Mult. Elapsed Time: 0.0034 seconds.

Size = 200 Mat. Mult. Elapsed Time: 0.0245 seconds.

Size = 400 Mat. Mult. Elapsed Time: 0.2207 seconds.

Size = 800 Mat. Mult. Elapsed Time: 4.7497 seconds.

Size = 1600 Mat. Mult. Elapsed Time: 74.5257 seconds.


(3200x3200 will not complete)

----------------------------------------------------- */

//------------------------------------------------------
//CIS 1C Assignment #3
//Instructor Solution Client 3
//TIMING FOR SPARSE MATRICES

import java.util.*;
import java.text.*;
import cs_1c.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 1600;

   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int row, randRow, randCol;
      long startTime, stopTime;
      double randFrac;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      SparseMatWMult mat, matAns;
      mat = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
      matAns = new SparseMatWMult(MAT_SIZE, MAT_SIZE);

      // generate small% non-default values bet 0 and 1
      smallPercent = MAT_SIZE/100. * MAT_SIZE;
      for (row = 0; row < smallPercent; row++)
      {
         randFrac = Math.random();
         randRow =  (int)(randFrac * MAT_SIZE);
         randFrac = Math.random();
         randCol =  (int)(randFrac * MAT_SIZE);
         randFrac = Math.random();
         mat.set(randRow, randCol, randFrac);
      }

      // 10x10 submatrix in lower right
      mat.showSubSquare(MAT_SIZE - 10, 10);

      startTime = System.nanoTime();
      matAns.matMult(mat, mat);
      stopTime = System.nanoTime();

      matAns.showSubSquare(MAT_SIZE - 10, 10);

      System.out.println("\nSize = " + MAT_SIZE
            + " Sp. Mat. Mult. Elapsed Time: "
            + tidy.format( (stopTime - startTime) / 1e9)
            + " seconds.");
   }
}

//--------------- Class SparseMatWMult Definition ---------------
//(uses SparseMat solution from previous assignment)

class SparseMatWMult extends SparseMat<Double>
{
   public SparseMatWMult( int numRows, int numCols)
   {
      super(numRows, numCols, 0.0);
   }

   public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
   {
      double dotProd;
      int row, col, k;

      // we want sizes the same and default values both 0
      if ( matA.getColSize() != matB.getRowSize()  
            || matA.rowSize < MIN_SIZE || matB.colSize < MIN_SIZE
            || matA.colSize < MIN_SIZE
            || (Double)matA.defaultVal != 0 || (Double)matB.defaultVal != 0)
         return false;

      // clear out object and set row size to matA's row size.
      rowSize = matA.rowSize;
      colSize = matB.colSize;
      allocateEmptyMatrix();
      // if C's default value is not already 0, we must make sure it is now
      defaultVal = 0.;

      // the multiplication loop
      for (row = 0; row < rowSize; row++)
      {
         // only saves time if very sparse matrix
         if (matA.rows.get(row).size() == 0)
            continue;
         for (MatNode rowNode : matA.rows.get(row))
         {
            k = rowNode.col;
            // only saves time if very sparse matrix
            if (matB.rows.get(k).size() == 0)
               continue;
            for (MatNode colNode : matB.rows.get(k))
            {
               // each node we find in row k of B contributes to ans[row,col]
               col = colNode.col;
               dotProd = get(row, col);  // may be non-0 value already
               dotProd += (rowNode.data * colNode.data);
               set(row, col, dotProd);
            }
         }
      }
      return true;
   }
}

//runs - notice that the times get smaller for "sparser" matrices, as expected

/* ----------------  a few runs and times (10% sparse) -------------------

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.9  0.0  0.0  0.0  0.8  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.1  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.8  0.7  0.0  0.0  0.0  0.0
0.0  0.2  0.0  0.0  0.9  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.6  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.6  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.8  0.0  0.7  0.5  1.2  2.4  1.0  0.4  0.6  1.5
0.1  1.4  1.8  0.6  1.6  2.2  0.2  0.1  1.6  0.8
1.6  0.3  0.6  0.7  2.9  1.9  0.3  1.8  0.6  1.1
0.4  0.5  1.7  0.6  0.9  1.0  0.4  0.6  0.9  0.9
0.3  1.8  0.7  1.1  0.6  1.1  0.8  1.8  1.3  0.8
2.2  0.7  1.3  0.1  0.9  2.6  0.6  1.3  2.0  0.8
1.7  1.3  0.7  1.2  2.1  1.2  1.2  2.0  0.1  0.9
1.2  0.8  1.2  2.0  1.3  0.8  1.4  0.8  2.0  0.8
1.5  0.9  1.2  0.6  2.0  0.1  1.9  2.0  2.0  0.2
1.1  1.5  1.8  1.5  1.1  1.7  0.8  2.9  0.9  0.0



Size = 100 Sp. Mat. Mult. Elapsed Time: 0.0227 seconds.

Size = 200 Sp. Mat. Mult. Elapsed Time: 0.0845 seconds.

Size = 400 Sp. Mat. Mult. Elapsed Time: 1.119 seconds.

Size = 800 Sp. Mat. Mult. Elapsed Time: 19.4901 seconds.

----------------  a few runs and times (5% sparse) -------------------

0.4  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.6  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.4  0.0  0.8  0.0  0.0  0.0  0.0
0.7  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.3  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.4  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.1  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.6  0.0
0.4  0.4  0.2  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.3  0.0  0.4  0.0  0.0  0.0  0.0  0.0  0.0  0.0
1.3  1.0  0.0  0.0  0.5  0.0  0.0  0.0  0.1  0.1
0.0  0.0  0.0  0.0  0.1  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.3  0.0  0.0  0.0  0.8  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.7  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.3  0.0  0.0  0.3  0.0  0.0  0.0  0.7  0.0

Size = 100 Sp. Mat. Mult. Elapsed Time: 0.0168 seconds.

Size = 200 Sp. Mat. Mult. Elapsed Time: 0.0249 seconds.

Size = 400 Sp. Mat. Mult. Elapsed Time: 0.1381 seconds.

Size = 800 Sp. Mat. Mult. Elapsed Time: 3.2357 seconds.

----------------  a few runs and times (1% sparse) -------------------

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.2  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.0  0.1  0.0  0.0  0.0  0.0  0.4  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.9  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.4  0.0

Size = 400 Sp. Mat. Mult. Elapsed Time: 0.018 seconds.

Size = 800 Sp. Mat. Mult. Elapsed Time: 0.041 seconds.

Size = 1600 Sp. Mat. Mult. Elapsed Time: 0.3559 seconds.


---- and the final proof that sparse matrices work:  3200 x 3200 ---

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.2   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

0.0   0.0   0.0   0.0   0.2   0.0   0.0   0.0   0.0   0.4
0.0   0.0   0.5   0.5   0.0   0.0   0.1   0.0   0.0   0.0
0.0   0.3   0.0   0.0   0.3   0.0   0.1   0.0   0.2   0.3
0.3   0.0   0.0   0.0   0.0   0.0   0.0   0.3   0.0   0.8
0.0   0.0   0.0   0.0   0.0   0.3   0.0   0.1   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.1   0.5   0.0   0.3   0.0   0.2   0.0   0.0   0.1   0.0
0.2   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.3   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.4   0.0   0.2

Size = 3200 Sp. Mat. Mult. Elapsed Time: 16.1961 seconds.