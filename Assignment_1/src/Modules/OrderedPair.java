package Modules;

class OrderedPair<A, B>
{
   private A first;
   private B second; 
   public OrderedPair()
   {
   }

   public OrderedPair(final A a, final B b)
   {
      setBoth(a, b);
   }
   public void setFirst(final A a)
   {
      first = a;
   }
   public void setSecond(final B b)
   {
      second = b;
   }
   public void setBoth(final A a, final B b)
   {
      setFirst(a);
      setSecond(b);  
   }
   public A getFirst()
   { 
      return first;
   } 
   public B getSecond()
   { 
      return second;
   } 
   public String toString()
   {
      return "(" + first + ", " + second + ") ";
   }
}