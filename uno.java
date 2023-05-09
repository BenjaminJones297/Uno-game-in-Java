 
 // Benjamin Jones
 // 1/5/2022
 // Needs to fix CPU using draw 4 then going again
 
 
 
 
 import java.util.ArrayList;
 import java.util.Scanner;
 public class uno {
 
 private ArrayList<String> deck = new ArrayList<String>();
 private ArrayList<String> shuffledDeck = new ArrayList<String>();
 private ArrayList<String> userHand = new ArrayList<String>();
 private ArrayList<String> cpuHand = new ArrayList<String>();
 private ArrayList<String> drawPile = new ArrayList<String>();
 private ArrayList<String> discardPile = new ArrayList<String>();
 private Scanner scnr = new Scanner(System.in);
 private String cardPlayed = "";
 private int cardNumberPlayed = -1;
 private boolean correct = true;
 private boolean gameOver = false;
 private String color = "";
 private int numRed = 0;
 private int numBlue = 0;
 private int numYellow = 0;
 private int numGreen = 0;
 private boolean userTurn = false;


   private void shuffle() {
      String color = "Red ";
     int count = 0;
     for (int i = 0; i < 4; ++i) {
         if (i == 0) {
            color = "Red ";
         }
         else if (i == 1) {
            color = "Blue ";
         }
         else if (i == 2) {
            color = "Green ";
         }
         
         else {
         
         color = "Yellow ";
         
         }
         
         for (int j = 1; j <= 9; ++j) {
            deck.add(color);
            deck.set(count, deck.get(count).concat(Integer.toString(j)));
            ++count;
         }
     }
     deck.add("Draw 4");
     deck.add("Draw 4");
     deck.add("Draw 4");
     deck.add("Draw 4");
     deck.add("Yellow Draw 2");
     deck.add("Yellow Draw 2");
     deck.add("Red Draw 2");
     deck.add("Red Draw 2");
     deck.add("Green Draw 2");
     deck.add("Green Draw 2");
     deck.add("Blue Draw 2");
     deck.add("Blue Draw 2");
     deck.add("Wild");
     deck.add("Wild");
     deck.add("Wild");
     deck.add("Wild");
          
     int n = 51;
     int randNum = (int)(Math.random() * n);
     boolean filled = false;
     for (int i = 0; i <= 51; ++i) {
         randNum = (int)(Math.random() * n);
         shuffledDeck.add(deck.get(randNum));
         deck.remove(randNum);
         --n;
      }
   }



   public void deal() {     
     for (int i = 0; i < 5; ++i) {
         userHand.add(shuffledDeck.get(0));
         shuffledDeck.remove(0);
         cpuHand.add(shuffledDeck.get(0));
         shuffledDeck.remove(0);
      }
      
      discardPile.add(shuffledDeck.get(0));
      shuffledDeck.remove(0);
   }
   
private void displayHand() {
   System.out.println("Your Hand");
   for (int i = 0; i < userHand.size(); ++i) {
      System.out.println((i + 1) + ". " + userHand.get(i));
      
   }

   System.out.println("Top card is : " + discardPile.get(discardPile.size() - 1) + "\n");
   System.out.println("Computer has " + cpuHand.size() + " cards left\n");

   }
   
   public void playGame() {
      shuffle();
      deal();
      while (!(gameOver)) {
         userTurn();
         isGameOver();
         if (gameOver) {
            System.out.println("Game Over, you Win!");
            break;
         }
         cpuTurn();
         isGameOver();
         if (gameOver) {
            System.out.println("Game Over, computer Wins!");
            break;
         }
      }      
   }
   
   public void userTurn() {
      userTurn = true;
      displayHand();
      System.out.println("Which card would you like to play?");
      System.out.println("Enter 0 to draw");
      cardNumberPlayed = scnr.nextInt();
      if (cardNumberPlayed > 0) {
      cardPlayed = userHand.get((cardNumberPlayed - 1));
      System.out.println("You chose " + cardPlayed);
      correct = isCorrect(cardPlayed);
         if (isCorrect(cardPlayed)) {
            userPlaysCard();
         }   
      }
      
      else if (cardNumberPlayed == 0) {
         userCardDrawn();
      }
      
      else {
         System.out.println("Invalid Input");
      }
      
      userTurn = false;
                    
   }
   
   private boolean isCorrect(String cardPlayed) {
      String topCard = discardPile.get(discardPile.size() - 1);
      if ((cardPlayed.equals("Wild")) || (cardPlayed.equals("Draw 4"))) {
         correct = true;
      }
      
      else if (topCard.equals("Wild") && (color.equals("Red")) && (cardPlayed.contains("Red"))) {
         correct = true;
      }
      
      else if (topCard.equals("Wild") && (color.equals("Blue")) && (cardPlayed.contains("Blue"))) {
         correct = true;
      }
      
      else if (topCard.equals("Wild") && (color.equals("Green")) && (cardPlayed.contains("Green"))) {
         correct = true;
      }
      
      else if (topCard.equals("Wild") && (color.equals("Yellow")) && (cardPlayed.contains("Yellow"))) {
         correct = true;
      }

      
      else if (cardPlayed.contains("Draw 2") && (topCard.contains("Draw 2"))) {
         correct = true;
      }
      
      else if (cardPlayed.contains("Blue") && (topCard.contains("Blue"))) {
         correct = true;
      }
      
      else if (cardPlayed.contains("Red") && (topCard.contains("Red"))) {
         correct = true;
      }
      
      else if (cardPlayed.contains("Green") && (topCard.contains("Green"))) {
         correct = true;
      }
      
      else if (cardPlayed.contains("Yellow") && (topCard.contains("Yellow"))) {
         correct = true;
      }
      
      else if (cardPlayed.contains(Integer.toString(1)) && (topCard.contains(Integer.toString(1)))) {
         correct = true;
      }
         
      else if (cardPlayed.contains(Integer.toString(2)) && (topCard.contains(Integer.toString(2)))) {
         if (cardPlayed.contains("Draw") && (topCard.contains("Draw"))) {
            correct = true;
         }
         else if (!(cardPlayed.contains("Draw")) && (!(topCard.contains("Draw")))) {
            correct = true;
         }
         else {
            correct = false;;
            }
         }
         
      else if (cardPlayed.charAt(cardPlayed.length()-1) == (topCard.charAt(topCard.length()-1))) {
         correct = true;
      }
         
      else {
         correct = false;
      }

   return correct;

      }
      
   public void userCardDrawn() {
      if (shuffledDeck.size() > 1) {
      userHand.add(shuffledDeck.get(shuffledDeck.size() - 1));
      }
      shuffledDeck.remove(shuffledDeck.size() - 1);
      if (userTurn) {
         if (isCorrect(userHand.get(userHand.size() - 1))) {
            cardPlayed = userHand.get(userHand.size() - 1);
            System.out.println("You drew " + cardPlayed + " you must play it");
            userPlaysCard();
         }
         
      }
      
     }
     
   public void isGameOver() {
      if ((userHand.size() == 0) || (cpuHand.size() == 0)) {
         gameOver = true;
      }
   }
   
   public void cpuCardDrawn() {
      cpuHand.add(shuffledDeck.get(shuffledDeck.size() - 1));
      shuffledDeck.remove(shuffledDeck.size() - 1);
   }

   
   public void userPlaysCard() {
            
       if (cardPlayed.contains("Draw 2")) {
         userHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         System.out.println("Computer draws 2");
         cpuCardDrawn();
         cpuCardDrawn();
         userTurn();
      }
      
      else if (cardPlayed.contains("Draw 4")) {
         userHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         System.out.println("Computer draws 4");
         cpuCardDrawn();
         cpuCardDrawn();
         cpuCardDrawn();
         cpuCardDrawn();
         userChangeColor();
         userTurn();       
      }
      
      else if (cardPlayed.contains("Wild")) {
         userHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         userChangeColor();
         userTurn();
      }
      
      else {
         discardPile.add(cardPlayed);
         userHand.remove(cardPlayed);

      }

   }
   
   public void userChangeColor() {
      System.out.println("What color would you like to change it too?");
      color = scnr.next();
      if (color.equals("Red")) {
         color = "Red";
      }
      
      if (color.equals("Blue")) {
         color = "Blue";
      }
      
      if (color.equals("Green")) {
         color = "Green";
      }
    
      if (color.equals("Yellow")) {
         color = "Yellow";
      }
      
      
      
}

public void cpuChangeColor() {

for (int i = 0; i < cpuHand.size(); ++i) {
   if (cpuHand.get(i).contains("Red")) {
      numRed++;
   }

   
   if (cpuHand.get(i).contains("Blue")) {
      numBlue++;
   }
   
   if (cpuHand.get(i).contains("Yellow")) {
      numYellow++;
   }
   
   if (cpuHand.get(i).contains("Green")) {
      numGreen++;
   }
}

      if (numRed >= numBlue) {
         if (numRed >= numYellow) {
            if (numRed >= numGreen) {
               color = "Red";
            }
         }
      }

      else if (numBlue >= numRed) {
         if (numBlue >= numYellow) {
            if (numBlue >= numGreen) {
               color = "Blue";
            }
         }
      }
      
      else if (numGreen >= numBlue) {
         if (numGreen >= numRed) {
            if (numGreen >= numYellow) {
               color = "Green";
            }
         }
      }
    
      if (numYellow >= numRed) {
         if (numYellow >= numBlue) {
            if (numYellow >= numGreen) {
               color = "Yellow";
            }
         }
      }
      System.out.println("Cpu changes color to " + color);
      
      for (int i = 0; i < cpuHand.size(); ++i) {
         if (cpuHand.get(i).contains(color)) {
            cardPlayed = cpuHand.get(i);
            cpuPlaysCard();
            break;
         }
}
      

}


public void cpuPlaysCard() {
   if (cardPlayed.contains("Draw 2")) {
         cpuHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         System.out.println("You draw 2");
         userCardDrawn();
         userCardDrawn();
         cpuTurn();
      }
      
      else if (cardPlayed.contains("Draw 4")) {
         cpuHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         System.out.println("You draw 4");
         userCardDrawn();
         userCardDrawn();
         userCardDrawn();
         userCardDrawn();
         cpuChangeColor();     
      }
      
      else if (cardPlayed.contains("Wild")) {
         cpuHand.remove(cardPlayed);
         discardPile.add(cardPlayed);
         cpuChangeColor();
      }
      
      else {
         cpuHand.remove(cardPlayed);
         discardPile.add(cardPlayed);

   }
   
}

   public void cpuTurn() {
      for (int i = 0; i < cpuHand.size(); ++i) {
         if (isCorrect(cpuHand.get(i))) {
            cardPlayed = cpuHand.get(i);
            cpuPlaysCard();
            return;
         }
      }
      cpuCardDrawn();
      return;
      
    }
    
   public void outOfCards() {
      if (shuffledDeck.size() == 1) {
         shuffle();
         for (int i = 0; i < cpuHand.size(); ++i) {
            shuffledDeck.remove(cpuHand.get(i));
         }
         
         for (int i = 0; i < userHand.size(); ++i) {
            shuffledDeck.remove(userHand.get(i));
         }

         
      }
   }
}