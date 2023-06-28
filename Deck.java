import java.util.Scanner;
import java.util.Random;

public class Deck {

	private Card[] cards;

	public Deck() {
		cards = new Card[52];
		createDeck();
		shuffleDeck();
		chooseGame();
	}

	public void createDeck() {
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] ranks = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
				"King", "Ace" };
		int deckIndex = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				cards[deckIndex] = new Card(ranks[j], suits[i]);
				deckIndex++;
			}
		}
	}

	public void shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < cards.length; i++) {
			int j = rand.nextInt(cards.length);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}

	public void chooseGame() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What game would you like to play?\n Blackjack (A)\n Other (B)");
		String input = scan.nextLine();
		if (input.equals("A")) {
			playBlackJack();
		} else {
			System.out.println("error");
		}
	}

	public void playBlackJack() {
		Card[] playerHand = { takeCard(), takeCard() };
		Card[] dealerHand = { takeCard(), takeCard() };

		Scanner sc = new Scanner(System.in);
		boolean endGame = false;
		
		while(endGame == false) {
			System.out.println("\n\nYour cards:");
			for (int i = 0; i < playerHand.length; i++) {
				System.out.println(playerHand[i].getRank() + " of " + playerHand[i].getSuit());
			}
			System.out.println("\nDealer's cards:");
			for (int i = 0; i< dealerHand.length; i++) {
				System.out.println(dealerHand[i].getRank() + " of " + dealerHand[i].getSuit());
			}
			
			System.out.println("Hit Me! (A) \n Hold (B)");
			String input = sc.nextLine();
			
			if (input.equals("A")) {
				Card[] newPlayerHand = new Card[playerHand.length + 1];
				for (int i = 0; i < playerHand.length; i++) {
					newPlayerHand[i] = playerHand[i];
				}
				newPlayerHand[playerHand.length] = takeCard();
				playerHand = newPlayerHand;
			}
			else
				endGame = true;	
		}
		
		//add them up
		System.out.println("you won");
	}

	public Card takeCard() {
		Card myCard = cards[0];
		for (int i = 1; i < cards.length; i++) {
			cards[i - 1] = cards[i];
		}
		cards[cards.length - 1] = null;
		return myCard;
	}

	public static void main(String[] args) {
		Deck d = new Deck();
	}
}
