package model;

public class QuestionCard {

	// variables
	private String question;
	private Category category;
	private Colour colour;

	// getters
	public String getQuestion() {
		return question;
	}

	public Category getCategory() {
		return category;
	}

	public Colour getColour() {
		return colour;
	}

	// setters
	public void setQuestion(String question) {
		if (question != null) {
			this.question = question;
		} else {
			this.question = "Error: Empty Question";
		}
	}

	public void setCategory(Category category) {
		if (category != null) {
			this.category = category;
		} else {
			this.category = Category.ADULT;
		}
	}

	public void setColour(Colour colour) {
		if (colour != null) {
			this.colour = colour;
		} else {
			this.colour = Colour.WHITE;
		}
	}

	// constructors
	public QuestionCard() {
		setQuestion("Error: Empty Question");
		setCategory(Category.ADULT);
		setColour(Colour.WHITE);
	}

	public QuestionCard(String question, Category category, Colour colour) {
		setQuestion(question);
		setCategory(category);
		setColour(colour);
	}

	// toString
	public String toString() {
		return "Question card: " + question + ", category: " + category + ", colour: " + colour;
	}
}
