package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import model.AnswerCard;

public class MainService {

	
	public void addArrayListAdult_answers(ArrayList<String> new_adult_answers) {
		if (new_adult_answers != null) {
			for (String new_answer : new_adult_answers) {
				if (!adult_answers.contains(new_answer)) {
					try (Writer output = new BufferedWriter(new FileWriter("ADULT.txt", true))) {
						output.append(new_answer);
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	
	
	public static void main(String[] args) {

		
	

	}

}
