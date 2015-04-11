package Modules;

public class Card
{   
   // private data
   private char value;
   private int suit;

   // static class constants (for suits)
   public static final int CLUBS = 0;
   public static final int DIAMONDS = 1;
   public static final int HEARTS = 2;
   public static final int SPADES = 3;

   // 4 overloaded constructors
   public Card(char value, int suit)
   {
      set(value, suit);
   }
   public Card(char value)
   {
      this(value, SPADES);
   }
   public Card()
   {
      this('A', SPADES);
   }
   // copy constructor
   public Card(Card card)
   {
      this.suit = card.suit;
      this.value = card.value;
   }

   // mutator
   public boolean set(char value, int suit)
   {
      char upVal;            // for upcasing char
      boolean valid = true;   // return value

      // filter out bad suit input:

      if (suit == CLUBS || suit == DIAMONDS 
            || suit == HEARTS || suit == SPADES)
         this.suit = suit;
      else
      {
         valid = false;
         this.suit = SPADES;
      }

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      // check for validity
      if (
            upVal == 'A' || upVal == 'K'
            || upVal == 'Q' || upVal == 'J'
            || upVal == 'T'
            || (upVal >= '2' && upVal <= '9')
            )
         this.value = upVal;
      else
      {
         valid = false;
         this.value = 'A';
      }
      return valid;
   }

   // accessors
   public char getVal()
   {
      return value;
   }
   public int getSuit()
   {
      return suit;
   }

   // stringizer
   public String toString()
   {
      String retVal;

      // convert from char to String
      retVal =  String.valueOf(value);

      if (suit == SPADES)
         retVal += " of Spades";
      else if (suit == HEARTS)
         retVal += " of Hearts";
      else if (suit == DIAMONDS)
         retVal += " of Diamonds";
      else if (suit == CLUBS)
         retVal += " of Clubs";

      return retVal;
   }
} 