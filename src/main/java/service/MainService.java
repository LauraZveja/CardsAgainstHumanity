package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Category;

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

}
