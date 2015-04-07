// FHstack main
// CS 1C, Foothill College, Michael Loceff, creator

// import statement needed to gain access to the entire class
import cs_1c.*;

public class Foothill
{
   public static void main (String[] args) throws CloneNotSupportedException
   {
      int k;
      FHstack<Integer> myStack = new FHstack<Integer>();

      for (k = 0; k < 10; k++)
         myStack.push(k);

      int theTop = myStack.top();
      System.out.println("top: " + theTop );

      for (k = 0; k < 12; k++)
         System.out.println( myStack.pop() );
   }
}

//generic FHstack class ------------------------------------
class FHstack<E> 
{
 private FHarrayList<E> mArrayList = new FHarrayList<E>();
   
   public void push(E x)
   {
      mArrayList.add(x);
   }
   
   public E pop()
   {
      try
      {
         return mArrayList.remove( mArrayList.size() - 1 );
      }
      catch (IndexOutOfBoundsException e)
      {
         return null;
      }
   }
   
   E top()
   {
      try
      {
         return mArrayList.get( mArrayList.size() - 1 );
      }
      catch (IndexOutOfBoundsException e)
      {
         return null;
      }
   }
}