package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class QuestionDeck {
	private ArrayList<QuestionCard> questionCards = new ArrayList<QuestionCard>();
	private Category category;

	public ArrayList<QuestionCard> getQuestionCards() {
		return questionCards;
	}

	public Category getCategory() {
		return category;
	}

	// constructor
	public QuestionDeck(Category category) {
		createDeck(category);
		shuffle();
		this.category = category;
	}

	// methods
	public void createDeck(Category category) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("/" + "QUESTIONS_" + category.toString() + ".txt")))) {
			String line;
			while ((line = br.readLine()) != null) {
				QuestionCard card = new QuestionCard(line, category, Colour.BLACK);
				questionCards.add(card);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shuffle() {
		Random rand = new Random();

		for (int i = 0; i < 1000; i++) {
			int place = rand.nextInt(questionCards.size() - 1);
			QuestionCard temp = questionCards.get(place);
			questionCards.remove(place);
			questionCards.add(temp);
		}
	}

	public QuestionCard giveMeOneQuestion() {
		QuestionCard temp = questionCards.get(0);
		questionCards.remove(0);
		return temp;
	}

	public void print() {
		for (QuestionCard temp : questionCards) {
			System.out.println(temp);
		}
	}

}
