package Assignment_2;

//client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foothill_Main2
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
