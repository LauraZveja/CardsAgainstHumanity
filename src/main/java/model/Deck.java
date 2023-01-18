package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	//Šeit vajadzētu AnswerCard
	private ArrayList<AnswerCard> answerCards = new ArrayList<AnswerCard>();
	
	public Deck() {
		//pārbaude, kāda tipa kārtis deckā ir jāieliek - adult vai under_18
		readCategoryAdultTxt(); // Šajā gadījumā izveido Adult answer deck
		readCategoryUnderEighteenTxt(); // šajā gadījumā izveido UNDER_18 deck
		shuffle();
		print();
	}
	

	public void shuffle()
	{
		Random rand = new Random();
		
		for(int i = 0; i < 1000; i++) {
			int place = rand.nextInt(52);
			AnswerCard temp = answerCards.get(place);
			answerCards.remove(place);
			answerCards.add(temp);
		}
	}
	
	public void print()
	{
		for(AnswerCard temp: answerCards) {
			System.out.println(temp);
		}
	}
	
	public AnswerCard giveMeOneCard()
	{
		AnswerCard temp = answerCards.get(0);
		answerCards.remove(0);
		return temp;
	}
	
	public void readCategoryAdultTxt() {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/ADULT.txt")))) {  
	        String line;
	        while ((line = br.readLine()) != null) {
	            AnswerCard card = new AnswerCard(line, Category.ADULT, Colour.WHITE);
	            answerCards.add(card);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void readCategoryUnderEighteenTxt() {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/UNDER_18.txt")))) {  
	        String line;
	        while ((line = br.readLine()) != null) {
	            AnswerCard card = new AnswerCard(line, Category.UNDER_18, Colour.WHITE);
	            answerCards.add(card);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
