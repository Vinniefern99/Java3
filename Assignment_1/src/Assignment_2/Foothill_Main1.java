package Assignment_2;

//CIS 1C Assignment #2 
//Vince Fernald, studentID 20217838
//Due Date: April 22, 2pm
//Loceff

import cs_1c.*;
import java.util.*;




//--------------------------- Client ------------------------------

public class Foothill_Main1
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