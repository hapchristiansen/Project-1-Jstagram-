package unl.soc;

import java.time.LocalDateTime;
import java.util.*;

public class TextArtPost extends Post {
	public final static List<String[]> OPTIONS;
    
	static { // initialize when the class is loaded
		OPTIONS = initTextArtOptions();
    	}
    private String[] chosenArt; //stores the art that the person chose
    
    public TextArtPost(int optionIndex, Account postAccount) {
    	this.chosenArt = OPTIONS.get(optionIndex); //sets the chosen art
    	this.postTime = LocalDateTime.now(); //sets time
    	this.postAccount = postAccount; //sets account for post
    }
    	public static List<String[]> initTextArtOptions(){
    		List<String[]> textArts = new ArrayList<>();
    		String[] house = {
        		"  ____||____   ",
        		" ///////////\\ ",
        		"///////////  \\",
        		"|    _    |  | ",
        		"|[] | | []|[]| ",
        		"|   | |   |  | ",
    		};
    		textArts.add(house);
    		String[] dog = {
    			" /^ ^\\ ",
    			"/ 0 0 \\",
    			"V\\ Y /V",
    			" / - \\ ", 
    			"/    | ",
    			"V__) ||"
    		};
    		textArts.add(dog);
    		String[] coffee = {
    			"  ( (    ",
    			"   ) )   ",
    			"........ ",
    			"|      |]",
    			"\\      / ",  
    			" `----'   "	
    		};
    		textArts.add(coffee);
    		String[] flower = {
    			"   (\\__         ",
    			"  :=)__)-|  __/) ",
    			"   (/    |-(__(=:",
    			" ______  |  _ \\) ",
    			"/      \\ | / \\   ",
    			"     ___\\|/___\\  ",
    			"    [         ]\\ ",
    			"     \\       /   ",
    			"      \\_____/    "
    		};
    		textArts.add(flower);
    		return textArts;
	}

    @Override
    	public String getFormattedContent() {
    	    StringBuilder contentBuilder = new StringBuilder();
    	    for (String line : chosenArt) {
    	        contentBuilder.append(String.format("| %-40s |\n", line));
    	        }
    	    
    	    return contentBuilder.toString();
    	}}
