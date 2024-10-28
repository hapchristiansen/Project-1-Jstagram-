package unl.soc;

public interface ContentFormatter {
	/** This method returns one or multiple lines to show the content
	 * Each line is a string of exactly 43 characters
	 * the first character is |
	 * then 40 characters of the content
	 * then a character of |
	 * the last character is '\n'
	 */ 
    String getFormattedContent(); 
}
