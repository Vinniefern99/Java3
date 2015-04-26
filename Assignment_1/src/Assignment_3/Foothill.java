package Assignment_3;

public class Foothill
{

   public static void main(String[] args)
   {
      double[][] matrix1 = new double[2][2];
      double[][] matrix2 = new double[2][2];
      double[][] matrix3 = new double[2][2];

      matrix1[0][0] = 1;
      matrix1[0][1] = 5;
      matrix1[1][0] = 3;
      matrix1[1][1] = 2;

      matrix2[0][0] = 5;
      matrix2[0][1] = 3;
      matrix2[1][0] = 2;
      matrix2[1][1] = 4;

      matMult(matrix1, matrix2, matrix3);


      for (int k = 0 ; k < matrix1.length ; k++)
      {
         for (int j = 0 ; j < matrix1[0].length ; j++)
            System.out.print(matrix1[k][j] + "s\t");
         System.out.println();
      }
      System.out.println();
      for (int k = 0 ; k < matrix2.length ; k++)
      {
         for (int j = 0 ; j < matrix2[0].length ; j++)
            System.out.print(matrix2[k][j] + "s\t");
         System.out.println();
      }
      System.out.println();
      for (int k = 0 ; k < matrix3.length ; k++)
      {
         for (int j = 0 ; j < matrix3[0].length ; j++)
            System.out.print(matrix3[k][j] + "s\t");
         System.out.println();
      }




   }
   public static void matMult( double[][] matA, double[][] matB, 
         double[][] matC)
   {
      //minimal testing as outlined in the spec.
      if (matA[0].length != matB[0].length)
      {
         System.out.println("Unable to multiply. Number of rows in first "
               + "matrix must be equal the number of rows in second matrix.");
         return;
      }


      /*
      if (matA.length != matB[0].length) {
         System.out.println("Unable to multiply. Number of columns in first "
               + "matrix must be equal the number of rows in second matrix.");
         return;
      }

      if (matC.length != matA.length || matC[0].length != matB[0].length) {
         System.out.println("Unable to multiply. Number of columns in target "
               + "matrix must be equal the number of rows in first matrix"
               + "AND number of rows in target matrix must equal number of "
               + "columns in second matrix.");
         return;
      }
       */
      for (int i = 0; i < matC.length; i++) {
         for (int j = 0; j < matC[0].length; j++) {
            for (int k = 0; k < matA[0].length; k++) {
               matC[i][j] += (matA[i][k] * matB[k][j]);
            }
         }
      }

   }


   public static void matShow(double[][] matA, int start, int size)
   {

   }


}
