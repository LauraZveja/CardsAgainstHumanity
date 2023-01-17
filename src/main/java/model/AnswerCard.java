package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AnswerCard {
	
private ArrayList<String> adult_answers = new ArrayList<>();
private ArrayList<String> underage_answers = new ArrayList<>();


// man šķiet, ka setterus nevajag. Jāpajautā Karinai. 
public ArrayList<String> getAdult_answers() {
	return adult_answers;
}

public ArrayList<String> getUnderage_answers() {
	return underage_answers;
}

public void readAdultTxt() {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/ADULT.txt")))) {
        String line;
        while ((line = br.readLine()) != null) {
            adult_answers.add(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
