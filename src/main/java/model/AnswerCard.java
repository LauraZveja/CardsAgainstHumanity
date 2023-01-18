package model;

public class AnswerCard {
	
private String answer;
private Category category;
private Colour colour;


public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public Colour getColour() {
	return colour;
}
public void setColour(Colour colour) {
	this.colour = colour;
}
public AnswerCard(String answer, Category category, Colour colour) {
	this.answer = answer;
	this.category = category;
	this.colour = colour;
}


public String toString() {
	return "Answer card: " + answer + ", category: " + category + ", colour: " + colour;
}



}
