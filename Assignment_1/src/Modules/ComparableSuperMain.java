package Modules;
public class ComparableSuperMain
{
   public static void main(String[] args)
   {
      SpecialInt a = new SpecialInt(5);
      SpecialInt b = new SpecialInt(-29);
      SpecialInt c = new SpecialInt(77);
      SpecialInt x;
      
      a.compareTo(b);

      x = findLargestOfThree(a, b, c);
 
      System.out.println(x.the_int + " " );
      
    }
   
   static <E extends Comparable<? super E>>
   E findLargestOfThree(E x, E y, E z)
   {
      if (x.compareTo(y) > 0)
         return (x.compareTo(z) > 0) ? x : z;
      else
         return (y.compareTo(z) > 0) ? y : z; 
   } 
}

class MyInt implements Comparable<MyInt>
{
   public int the_int;
   public MyInt(int n) { the_int = n; }
   public int compareTo(MyInt other)
   {
      return (the_int - other.the_int);
   }
}

class SpecialInt extends MyInt
{
   public SpecialInt(int n) { super(n); }
   // etc.
}