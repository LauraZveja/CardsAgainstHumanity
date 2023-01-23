package model;

public class AnswerCard {

	private String answer;
	private Category category;
	private Colour colour;

	public String getAnswer() {
		return answer;
	}

	public Category getCategory() {
		return category;
	}

	public Colour getColour() {
		return colour;
	}

	public void setAnswer(String answer) {
		if (answer != null) {
			this.answer = answer;
		}
	}

	public void setCategory(Category category) {
		if (category != null) {
			this.category = category;
		}
	}

	public void setColour(Colour colour) {
		if (colour != null) {
			this.colour = colour;
		}
	}

	public AnswerCard(String answer, Category category, Colour colour) {
		setAnswer(answer);
		setCategory(category);
		setColour(colour);
	}

	public String toString() {
		return answer;
	}

}
