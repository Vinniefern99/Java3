package Modules;

import javax.swing.*;

public class CardRevealerMain
{
   public static void main(String[] args)
   {
      String string1 = "death of a salesman";
      String string2 = "queen's gambit";
      Card card1, card2, card3;
 
      Revealer<Card> rv1 = new Revealer<Card>();    
      Revealer<String> rv2 = new Revealer<String>();
 
      card1 = new Card();
      card2 = new Card('5');
      card3 = new Card('9', Card.HEARTS);

      rv1.displayGUI(card1);
      rv2.displayGUI(string1);
      rv1.displayGUI( new Card('t',Card.CLUBS));
      
      rv1.displayConsole(card3);   
      rv1.displayConsole(card2);
      rv2.displayConsole(string2); 
   }
}

class Revealer<E>
{
   public   void displayGUI(E myThing)
   {
      String report = myThing.toString();
      JOptionPane.showMessageDialog(null, report, 
            "Loceff's Data Revealer", JOptionPane.PLAIN_MESSAGE);
   }
   
   public   void displayConsole(E myThing)
   {
      String report = myThing.toString();
      System.out.println(" --- Loceff's Data Revealer ---");
      System.out.println(report);
      System.out.println(" ------------------------------\n\n");
   }
}
