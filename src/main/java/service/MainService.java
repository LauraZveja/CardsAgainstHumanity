package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Category;

public class MainService {
	
	
	public static void main(String[] args) {

		
		//insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte!");
		//insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte");

	}
	
	public static void insertAnswer(Category category, String newAnswer) {
	    try (BufferedReader br = new BufferedReader(
	            new FileReader("src/main/resources/" + category + ".txt"))) {
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
	                    new FileWriter("src/main/resources/ADULT.txt", true))) {
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


}
