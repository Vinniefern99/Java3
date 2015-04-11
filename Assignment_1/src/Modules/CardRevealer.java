package Modules;
import javax.swing.JOptionPane; 

class CardRevealer
{
   public   void displayGUI(Card myThing)
   {
      String report = myThing.toString();
      JOptionPane.showMessageDialog(null, report, 
            "Loceff's Data Revealer", JOptionPane.PLAIN_MESSAGE);
   }
   
   public   void displayConsole(Card myThing)
   {
      String report = myThing.toString();
      System.out.println(" --- Loceff's Data Revealer ---");
      System.out.println(report);
      System.out.println(" ------------------------------\n\n");
   }
}