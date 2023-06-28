import java.util.Scanner;

public class Deck {

   private Card[] cards;

   public Deck() {
      cards = new Card[52];
      createDeck();
      shuffleDeck();
      chooseGame();
   }

   public void createDeck() {
      String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
      String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
      int deckIndex = 0;     
      for(int i = 0; i < suits.length; i++) {
         for(int j = 0; j < ranks.length; j++) {
            cards[deckIndex] = new Card(ranks[j], suits[i]);
            deckIndex++;
         }
      } 

      for(int i = 0; i < 50; i++) {
//	System.out.println(cards[i].getSuit() + " " + cards[i].getRank());
      }
   }

   public void shuffleDeck() {
      
   }

   public void chooseGame() {
      Scanner scan = new Scanner(System.in);
      System.out.println("What game would you like to play? /n Blackjack (A) /n Other (B)");
      String input = scan.nextLine();
      if(input.equals("A")) {
         playBlackJack();
      }
      else {
         System.out.println("error");
      }  
   }

   public void playBlackJack() {
      
   }

   public static void main(String[] args) {
      Deck d = new Deck();
   }
}
