package simple21;

import java.util.Scanner;

import java.util.Random;


/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {

	/**
	 * Human player.
	 */
	HumanPlayer human;

	/**
	 * Computer player.
	 */
	ComputerPlayer player1;

	/**
	 * Computer player.
	 */
	ComputerPlayer player2;

	/**
	 * Computer player.
	 */
	ComputerPlayer player3;

	/** 
	 * A random number generator to be used for returning random "cards" in a card deck.
	 * */
	Random random = new Random();



	/**
	 * The main method just creates a GameControl object and calls its run method.
	 * @param args Not used.
	 */
	public static void main(String args[]) {    
		new GameControl().run();
	}

	/**
	 * Prints a welcome method, then calls methods to perform each of the following actions:
	 * - Create the players (one of them a Human)
	 * - Deal the initial two cards to each player
	 * - Control the play of the game
	 * - Print the final results
	 */
	public void run() {

		Scanner scanner = new Scanner(System.in);  

		// Students: your code goes here.
		// prints the welcoming message
		System.out.println("Welcome to Simple 21!\n"
				+ "You'll play against 3 other players (computers).\n"
				+ "Try to get as close to 21 as possible, without going over.");

		// calls the players

		System.out.println("What is your name?");
		String humanname = scanner.nextLine();

		createPlayers(humanname);

		deal();

		controlPlay(scanner);

		printWinner();

		scanner.close();
	}

	/**
	 * Creates one human player with the given humansName, and three computer players with hard-coded names.
	 * @param humansName for human player
	 */
	public void createPlayers(String humansName) {
		// Students: your code goes here.

		this.human = new HumanPlayer(humansName);
		this.player1 = new ComputerPlayer("Player1");
		this.player2 = new ComputerPlayer("Player2");
		this.player3 = new ComputerPlayer("Player3");
	}

	/**
	 * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
	 * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
	 * of each other player's cards, not the individual cards.)
	 */
	public void deal() { 
		// Students: your code goes here.

		this.human.takeHiddenCard(nextCard());
		this.human.takeVisibleCard(nextCard());
		this.player1.takeHiddenCard(nextCard());
		this.player1.takeVisibleCard(nextCard());
		this.player2.takeHiddenCard(nextCard());
		this.player2.takeVisibleCard(nextCard());
		this.player3.takeHiddenCard(nextCard());
		this.player3.takeVisibleCard(nextCard()); 	
		}

	/**
	 * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
	 * The odds of returning a 10 are four times as likely as any other value (because in an actual
	 * deck of cards, 10, Jack, Queen, and King all count as 10).
	 * 
	 * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
	 * @return a random integer in the range 1 - 10.
	 */
	public int nextCard() { 
		// Students: your code goes here.
		// generate random number between 1 and 1o for the hidden card
		int randomValue = random.nextInt(13)+1;
		if (randomValue >= 10) {
			return 10;
		}
		else {
			return randomValue;
		}
	}

	/**
	 * Gives each player in turn a chance to take a card, until all players have passed. Prints a message when 
	 * a player passes. Once a player has passed, that player is not given another chance to take a card.
	 * @param scanner to use for user input
	 */
	public void controlPlay(Scanner scanner) { 
		// Students: your code goes here.
		while (!checkAllPlayersHavePassed()) {
			System.out.println("");
			if (this.human.passed == false) {

				boolean humanNotPass = this.human.offerCard(human, player1, player2, player3, scanner );
				if(humanNotPass == true) {
					this.human.takeVisibleCard(nextCard());
				}
				else  {
					System.out.println(this.human.name + " passes");
					if (this.human.passed == true) {
					}
				}
			}

			if (this.player1.passed == false) {

				boolean player1NotPass = this.player1.offerCard(human, player1, player2, player3);
				if (player1NotPass == true) {
					this.player1.takeVisibleCard(nextCard());
				}
				else {
					System.out.println(this.player1.name + " passes");
					if (this.player1.passed == true) {
					}
				}
			}

			if (this.player2.passed == false) {
				boolean player2NotPass = this.player2.offerCard(human, player1, player2, player3);
				if (player2NotPass == true) {
					this.player2.takeVisibleCard(nextCard());
				}
				else {
					System.out.println(this.player2.name + " passes");
					if (this.player2.passed == true) {
					}
				}
			}

			if (this.player3.passed == false) {
				boolean player3NotPass = this.player3.offerCard(human, player1, player2, player3);
				if (player3NotPass == true) {
					this.player3.takeVisibleCard(nextCard());
				}
				else {
					System.out.println(this.player3.name + " passes");
					if (this.player3.passed == true) {
					}
				}
			}

		}
	}

	/**
	 * Checks if all players have passed.
	 * @return true if all players have passed
	 */
	public boolean checkAllPlayersHavePassed() {
		// Students: your code goes here.
		if (human.passed == true && player1.passed == true && player2.passed == true && player3.passed == true) {
			return true;
		}
		return false;

	}


	/**
	 * Prints a summary at the end of the game.
	 * Displays how many points each player had, and if applicable, who won.
	 */
	public void printResults() { 
		// Students: your code goes here.

		System.out.println(this.human.name + " has " +  this.human.getScore()  + " total point(s).");
		System.out.println(this.player1.name + " has " +  this.player1.getScore()  + " total point(s).");
		System.out.println(this.player2.name + " has " +  this.player2.getScore()  + " total point(s).");
		System.out.println(this.player3.name + " has " +  this.player3.getScore()  + " total point(s).");	
	}

	/**
	 * Determines who won the game, and prints the results.
	 */
	public void printWinner() { 
		// Students: your code goes here.
		System.out.println("");
		printResults();
		System.out.println("");
		int humanScore = this.human.getScore();
		int player1Score = this.player1.getScore();
		int player2Score = this.player2.getScore();
		int player3Score = this.player3.getScore();

		int maxScore = -1;
		int number = 1;

		if (humanScore <= 21 || player1Score <= 21 || player2Score <= 21 || player3Score <= 21)  {
			if (humanScore <= 21) {
				maxScore = humanScore;

			}
			if (player1Score <= 21) {
				if (player1Score > maxScore) {
					maxScore = player1Score;
					number = 1;

				} else if (player1Score == maxScore) {
					number++;
				}
			} 

			if (player2Score <= 21) {
				if (player2Score > maxScore) {
					maxScore = player2Score;
					number = 1;

				} else if (player2Score == maxScore) {
					number++;
				}
			} 

			if (player3Score <= 21) {
				if (player3Score > maxScore) {
					maxScore = player3Score;
					number = 1;

				} else if (player3Score == maxScore) {
					number++;
				}
			} 

			if (number == 1) {
				if (humanScore == maxScore) {
					System.out.println(this.human.name + " wins with " + maxScore + " point(s)");
				} else if (player1Score == maxScore) {
					System.out.println(this.player1.name + " wins with " + maxScore + " point(s)");
				} else if (player2Score == maxScore) {
					System.out.println(this.player2.name + " wins with " + maxScore + " point(s)");
				} else if (player3Score == maxScore) {
					System.out.println(this.player3.name + " wins with " + maxScore + " point(s)");
				}
			} else {
				System.out.println("Tie, Nobody wins !");
			}
		}
		else {
			System.out.println("Tie, Nobody wins !");
		}
	}

}
