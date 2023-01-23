package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.AnswerCard;
import model.Category;
import model.Deck;
import model.Player;

public class MainService {

	public static void main(String[] args) {

		// insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte!");
		// insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte");

		/*
		 * ArrayList<String> new_adult_answers = new ArrayList<>();
		 * new_adult_answers.add("Testing, testing, testing!");
		 * new_adult_answers.add("A moment of silence.");
		 * new_adult_answers.add("11111111111111."); new_adult_answers.add("22222222.");
		 * new_adult_answers.add("A moment of silence.");
		 * 
		 * 
		 * updateAdult_answers(Category.ADULT, new_adult_answers);
		 * 
		 * try { // Use BufferedReader to read the file BufferedReader reader = new
		 * BufferedReader(new FileReader("src/main/resources/ADULT.txt")); String line;
		 * // Read the file line by line while ((line = reader.readLine()) != null) { //
		 * Print the contents of the file System.out.println(line); } // Close the
		 * reader reader.close(); } catch (IOException e) { e.printStackTrace(); }
		 */

		// Pārbaude, vai katram spēlētājam piešķir 5 dažādas atbilžu kārtis.
		/*
		 * Player speletajs = new Player(); Deck kava = new Deck(Category.ADULT);
		 * dealHand(speletajs, kava);
		 * 
		 * Player speletajs2 = new Player(); dealHand(speletajs2, kava);
		 * 
		 * System.out.println(speletajs.getAnswers());
		 * System.out.println(speletajs2.getAnswers()); //
		 * updateAnswerDeckByCategory(Category.ADULT, new_adult_answers);
		 * //deleteAnswerFromAnswerDeckByCategory(Category.ADULT, "11111111111111");
		 * //readAnswerDeckFileByCategory(Category.ADULT); Deck deck1 = new
		 * Deck(Category.ADULT); readAnswerDeck(deck1);
		 */

	}

	public static void insertAnswer(Category category, String newAnswer) {
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + category + ".txt"))) {
			String line;
			boolean alreadyExist = false;
			while ((line = br.readLine()) != null) {
				if (line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(newAnswer.replaceAll("[\\.,'!]", ""))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist) {
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter("src/main/resources/" + category + ".txt", true))) {
					bw.write(newAnswer);
					bw.newLine();
				}
			} else {
				System.out.println("Answer already exists in the file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * updateAdult_answers - pievieno failam sarakstu ar jaunām atbildēm, pārbauda,
	 * vai atbilde jau nav iekš faila
	 */

	public static void updateAdult_answers(Category category, ArrayList<String> new_adult_answers) {
		if (new_adult_answers != null) {
			for (String new_answer : new_adult_answers) {
				try (BufferedReader br = new BufferedReader(
						new FileReader("src/main/resources/" + category + ".txt"))) {
					String line;
					boolean alreadyExist = false;
					while ((line = br.readLine()) != null) {
						if (line.replaceAll("[\\.,']", "").equalsIgnoreCase(new_answer.replaceAll("[\\.,']", ""))) {
							alreadyExist = true;
							break;
						}
					}
					if (!alreadyExist) {
						try (BufferedWriter bw = new BufferedWriter(
								new FileWriter("src/main/resources/" + category + ".txt", true))) {
							if ((line = br.readLine()) == null) {
								bw.newLine();
							}
							bw.write(new_answer);
						}
					} else {
						System.out.println("Answer already exists in the file");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void dealHand(Player player, Deck deck) {
		if (player != null && deck != null) {
			for (int i = 0; i < 5; i++) {
				player.getAnswers().add(deck.giveMeOneCard());
			}
		} else {
			System.out.println("Error: Player or deck object is null.");
		}
	}

	// READ FILE
	public static void readAnswerDeckFileByCategory(Category category) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + category + ".txt"));
			String line;
			while ((line = reader.readLine()) != null) {

				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// READ DECK
	public static void readAnswerDeck(Deck deck) {
		deck.toString();
	}

	// READ 1st CARD
	public static void readAnswerDeckFirstCard(Deck deck) {
		System.out.println(deck.getAnswerCards().get(0));
	}

	// DELTE ANSWER

	public static void deleteAnswerFromAnswerDeckByCategory(Category category, String unwanted_Answer) {
		File file = new File("src/main/resources/" + category + ".txt");
		StringBuilder temp_answers = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(unwanted_Answer.replaceAll("[\\.,'!]", ""))) {
					temp_answers.append(line + "\n");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(temp_answers.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
