package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Category;
import model.Deck;
import model.Player;
import model.QuestionDeck;

public class MainService {

	public static void main(String[] args) {
		
		
		/* -----------------------SEIT SAKAS DATUBAZES TESTS-----------------------
		  try {
			DatabaseUtils.createDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player p1 = new Player("parole123", "username123", new GregorianCalendar(2002, Calendar.DECEMBER, 31), "epasts@gmail.com", Gender.MALE, Country.LATVIA, 0, 0, 0); // 18 sanak
		try {
			DatabaseUtils.savePlayerToDB(p1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Player p2 = new Player("citaparole", "citsusername", new GregorianCalendar(2010, Calendar.DECEMBER, 31), "citsepasts@gmail.com", Gender.MALE, Country.LATVIA, 0, 0, 0); // sim nesanaks 18
		try {
			DatabaseUtils.savePlayerToDB(p2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Player p3 = new Player();
		try {
			DatabaseUtils.savePlayerToDB(p3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Player p4 = new Player();
		try {
			DatabaseUtils.savePlayerToDB(p4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		GameLobby gl1 = new GameLobby();
		
		try {
			DatabaseUtils.saveGameLobbyToDB(gl1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Round r1 = new Round();
		
		Vote v1 = new Vote(r1, null, p1, p2);
		Vote v2 = new Vote(r1, null, p2, p1);
		Vote v3 = new Vote(r1, null, p3, p1);
		Vote v4 = new Vote(r1, null, p4, p1);
		ArrayList<Vote> votes = new ArrayList<Vote>();
		votes.add(v1);
		votes.add(v2);
		votes.add(v3);
		votes.add(v4);
		r1.setVotes(votes);
		
		try {
			DatabaseUtils.saveVotesToDB(r1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int player_id = DatabaseUtils.getPlayerIdByUsername(p1.getUserName());
		System.out.println("Player_id: "+player_id);
		
		int totalScoreByUsername = DatabaseUtils.getTotalScoreByUsername(p1.getUserName());
		System.out.println("TotalScoreByUsername: "+totalScoreByUsername);
		
		int TSBUAGID = DatabaseUtils.getTotalScoreByUsernameAndGameID(p1.getUserName(), 0);
		System.out.println("TSBUAGID: "+TSBUAGID);
		
		boolean isAdult = DatabaseUtils.isPlayerAnAdult(p1.getUserName());
		System.out.println("Vai ir virs 18?: "+isAdult);
		boolean isAdult2 = DatabaseUtils.isPlayerAnAdult(p2.getUserName());
		System.out.println("A sim ir 18?: "+isAdult2);
		
		boolean isUsernameTaken = DatabaseUtils.isUsernameTaken(p1.getUserName());
		boolean isUsernameTaken2 = DatabaseUtils.isUsernameTaken("simjaatgriezfalse");
		System.out.println("IsUsernameTaken: "+isUsernameTaken+ " IsUsernameTaken2: "+isUsernameTaken2);
		
		boolean correctpassword = DatabaseUtils.isPlayerPasswordCorrect(p1.getUserName(), p1.getPassword());
		boolean correctpassword1 = DatabaseUtils.isPlayerPasswordCorrect(p1.getUserName(), "simjaatgriezfalse");
		System.out.println("Parole1 pareiza: "+ correctpassword+"; parole2 pareiza: "+correctpassword1);
		
		
		boolean isEmailTaken = DatabaseUtils.isEmailTaken(p1.getEmail());
		boolean isEmailTaken2 = DatabaseUtils.isEmailTaken("atgriezamfalse@gmail.com");
		System.out.println("Epasts taken?: "+isEmailTaken+ " email2: "+isEmailTaken2);
		 -----------------------SEIT BEIDZAS DATUBAZES TESTS-----------------------*/
		
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
		 * //readAnswerDeckFileByCategory(Category.ADULT);
		 */
		Deck deck1 = new Deck(Category.ADULT);
		readAnswerDeck(deck1);

		// System.out.println(deck1);
		// readAnswerDeckFirstCard(deck1);

		// -------------- QUESTIONS ---------------------- //
		// QuestionDeck questionDeck = new QuestionDeck(Category.UNDER_18);

		QuestionDeck questionDeck2 = new QuestionDeck(Category.ADULT);
		readQuestionDeck(questionDeck2);

		/*
		 * ArrayList<String> newQuestions = new ArrayList<>(); newQuestions.
		 * add("I swear to God I am gonna murder my husband if he does not shut up about _____"
		 * ); newQuestions.add("Yo, is _____ racist?");
		 * newQuestions.add("I have solved politics. My solution is ______");
		 * 
		 * updateQuestions(Category.ADULT, newQuestions);
		 * readQuestionDeckFileByCategory(Category.ADULT);
		 * deleteQuestionFromQuestionDeckByCategory(Category.ADULT,
		 * "Instead of the Jews, Hitler should have worried more about ____.");
		 * readQuestionDeckFileByCategory(Category.ADULT);
		 */

	}

	// -------------------- ANSWERS --------------- //

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
		System.out.println(deck.toString());
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

	// -------------------- QUESTIONS --------------- //

	// READ QUESTION DECK
	public static void readQuestionDeck(QuestionDeck questionDeck) {
		System.out.println(questionDeck);
	}

	// READ QUESTION DECK 1ST CARD
	public static void readQuestionDeckFirstCard(QuestionDeck questionDeck) {
		System.out.println(questionDeck.getQuestionCards().get(0).getQuestion());
	}

	// READ FILE
	public static void readQuestionDeckFileByCategory(Category category) {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"));
			String line;
			while ((line = reader.readLine()) != null) {

				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CREATE QUESTION

	public static void insertQuestion(Category category, String newQuestion) {
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"))) {
			String line;
			boolean alreadyExist = false;
			while ((line = br.readLine()) != null) {
				if (line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(newQuestion.replaceAll("[\\.,'!]", ""))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist) {
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter("src/main/resources/QUESTIONS_" + category + ".txt", true))) {
					bw.write(newQuestion);
					bw.newLine();
				}
			} else {
				System.out.println("Question already exists in the file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// UPDATE QUESTION
	public static void updateQuestions(Category category, ArrayList<String> newQuestions) {
		if (newQuestions != null) {
			for (String new_question : newQuestions) {
				try (BufferedReader br = new BufferedReader(
						new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"))) {
					String line;
					boolean alreadyExist = false;
					while ((line = br.readLine()) != null) {
						if (line.replaceAll("[\\.,']", "").equalsIgnoreCase(new_question.replaceAll("[\\.,']", ""))) {
							alreadyExist = true;
							break;
						}
					}
					if (!alreadyExist) {
						try (BufferedWriter bw = new BufferedWriter(
								new FileWriter("src/main/resources/QUESTIONS_" + category + ".txt", true))) {
							if ((line = br.readLine()) == null) {
								bw.newLine();
							}
							bw.write(new_question);
						}
					} else {
						System.err.println("Question already exists in the file");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// DELETE QUESTION

	public static void deleteQuestionFromQuestionDeckByCategory(Category category, String unwanted_Question) {
		File file = new File("src/main/resources/QUESTIONS_" + category + ".txt");
		StringBuilder temp_questions = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(unwanted_Question.replaceAll("[\\.,'!]", ""))) {
					temp_questions.append(line + "\n");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(temp_questions.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
