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
		System.out.println("What game would you like to play?\nBlackjack (A)\nOther (B)");
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
			System.out.println("-------------------------------");
			System.out.println("\nYour cards:");
			printHand(playerHand);
			
			System.out.println("\nDealer's cards:");
			for (int i = 0; i< dealerHand.length-1; i++) {
				System.out.println(dealerHand[i].getRank() + " of " + dealerHand[i].getSuit());
			}
			
			System.out.println("\n\nHit Me! (A) \nHold (B)");
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
		
		System.out.println("-------------------------------");
		System.out.println("\nYour Hand:");
		printHand(playerHand);
		System.out.println("\nDealer's Hand:");
		printHand(dealerHand);
		
		//add them up
		int playerValue = addCardValuesBJ(playerHand);
		int dealerValue = addCardValuesBJ(dealerHand);
		
		if(playerValue > 21)
			System.out.println("\nBust!");
		else if(playerValue > dealerValue)
			System.out.println("\nYou Win!");
		else
			System.out.println("\nYou Lose");
	}

	public int addCardValuesBJ(Card myCards[]) {
		int handValue = 0;
		for(int i = 0; i < myCards.length; i++) {
			switch(myCards[i].getRank()) {
			case "Ace":
				handValue += 1;
				break;
			case "Two" :
				handValue += 2;
				break;
			case "Three" :
				handValue += 3;
				break;
			case "Four" :
				handValue += 4;
				break;
			case "Five" :
				handValue += 5;
				break;
			case "Six" :
				handValue += 6;
				break;
			case "Seven" :
				handValue += 7;
				break;
			case "Eight" :
				handValue += 8;
				break;
			case "Nine" :
				handValue += 9;
				break;
			case "Ten" :
				handValue += 10;
				break;
			case "Jack" :
			case "King" :
			case "Queen" :
				handValue += 11;
				break;
			default :
				handValue -= 1000;
			}
		}
		return handValue;
	}
	
	public void printHand(Card[] myHand) {
		for (int i = 0; i < myHand.length; i++) {
			System.out.println(myHand[i].getRank() + " of " + myHand[i].getSuit());
		}
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
