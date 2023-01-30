package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

	// Šeit vajadzētu AnswerCard
	private ArrayList<AnswerCard> answerCards = new ArrayList<AnswerCard>();
	private Category category;

	public ArrayList<AnswerCard> getAnswerCards() {
		return answerCards;
	}

	public Deck(Category newDeckCategory) {
		createDeck(newDeckCategory);
		shuffle();
		this.category = newDeckCategory;
	}

	public void shuffle() {
		Random rand = new Random();

		for (int i = 0; i < 10000; i++) {
			int place = rand.nextInt(answerCards.size());
			AnswerCard temp = answerCards.get(place);
			answerCards.remove(place);
			answerCards.add(temp);
		}
	}

	public void print() {
		for (AnswerCard temp : answerCards) {
			System.out.println(temp);
		}
	}

	public AnswerCard giveMeOneCard() {
		AnswerCard temp = answerCards.get(0);
		answerCards.remove(0);
		return temp;
	}

	public void createDeck(Category newDeckCategory) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("/" + newDeckCategory.toString() + ".txt")))) {
			String line;
			while ((line = br.readLine()) != null) {
				AnswerCard card = new AnswerCard(line, newDeckCategory, Colour.WHITE);
				answerCards.add(card);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Deck [answerCards=" + answerCards + ", category=" + category + "]";
	}

}
