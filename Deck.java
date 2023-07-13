import java.util.Scanner;
import java.util.Random;

public class Deck {

	private Card[] deckCards;
	private Card[] playerHand;
	private Card[] computerHand;
	private int numPlayerCards;
	
	public Deck() {
		deckCards = new Card[52];
		playerHand = new Card[52];
		computerHand = new Card[52];
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
				deckCards[deckIndex] = new Card(ranks[j], suits[i]);
				deckIndex++;
			}
		}
	}

	public void shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < deckCards.length; i++) {
			int j = rand.nextInt(deckCards.length);
			Card temp = deckCards[i];
			deckCards[i] = deckCards[j];
			deckCards[j] = temp;
		}
	}

	public void chooseGame() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What game would you like to play?\nBlackjack (A)\nWar (B)\nOther (C)");
		String input = scan.nextLine();
		if (input.equals("A"))
			playBlackJack();
		else if (input.equals("B"))
			playWar();
		else
			System.out.println("error");
	}

	public void playWar() {
		for (int i = 0; i < 26; i++) {
			playerHand[i] = takeCard();
			computerHand[i] = takeCard();
		}
		int playerCardNum = 26;
		int computerCardNum = 26;
		while (playerCardNum > 0 && computerCardNum > 0) {
			playerCardNum--;
		}
	}

	public void playBlackJack() {
		playerHand[0] = takeCard();
		playerHand[1] = takeCard();
		numPlayerCards += 2;
		computerHand[0] = takeCard();
		computerHand[1] = takeCard();

		Scanner sc = new Scanner(System.in);
		boolean endGame = false;

		while (endGame == false && addCardValuesBJ(playerHand) < 21) {
			System.out.println("-------------------------------");
			
			System.out.println("\nYour cards:");
			printHand(playerHand, numPlayerCards);
			System.out.println("\nDealer's cards:");
			printHand(computerHand, 2);

			System.out.println("\n\nHit Me! (A) \nHold (B)");
			String input = sc.nextLine();

			if (input.equals("A")) {
				numPlayerCards++;
				playerHand[numPlayerCards - 1] = takeCard();
				printHand(playerHand, numPlayerCards);
			} else
				endGame = true;
		}

		System.out.println("-------------------------------");
		System.out.println("\nYour Hand:");
		printHand(playerHand, numPlayerCards);
		System.out.println("\nDealer's Hand:");
		printHand(computerHand, 2);

		// add them up
		int playerValue = addCardValuesBJ(playerHand);
		int dealerValue = addCardValuesBJ(computerHand);

		if (playerValue > 21)
			System.out.println("\nBust!");
		else if (playerValue > dealerValue)
			System.out.println("\nYou Win!");
		else
			System.out.println("\nYou Lose");
		
		System.out.println(playerValue);
		System.out.println(dealerValue);
	}

	public int addCardValuesBJ(Card myCards[]) {
		int handValue = 0;
		for (int i = 0; i < myCards.length; i++) {
			if (myCards[i] != null)
				switch (myCards[i].getRank()) {
				case "Ace":
					handValue += 1;
					break;
				case "Two":
					handValue += 2;
					break;
				case "Three":
					handValue += 3;
					break;
				case "Four":
					handValue += 4;
					break;
				case "Five":
					handValue += 5;
					break;
				case "Six":
					handValue += 6;
					break;
				case "Seven":
					handValue += 7;
					break;
				case "Eight":
					handValue += 8;
					break;
				case "Nine":
					handValue += 9;
					break;
				case "Ten":
					handValue += 10;
					break;
				case "Jack":
				case "King":
				case "Queen":
					handValue += 11;
					break;
				default:
					handValue -= 1000;
				}
		}
		return handValue;
	}

	public void printHand(Card[] myHand, int num) {
		for (int i = 0; i < num; i++) {
			if (myHand[i] != null)
				System.out.println(myHand[i].getRank() + " of " + myHand[i].getSuit());
		}
	}

	public Card takeCard() {
		Card myCard = deckCards[0];
		for (int i = 1; i < deckCards.length; i++) {
			deckCards[i - 1] = deckCards[i];
		}
		deckCards[deckCards.length - 1] = null;
		return myCard;
	}

	public static void main(String[] args) {
		Deck d = new Deck();
	}
}
