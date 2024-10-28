package unl.soc;

import java.time.LocalDateTime;

public class TextPost extends Post {
	private String text;
	
	public TextPost(String text, Account postAccount) {
		this.text = text;
		this.postTime = LocalDateTime.now();
		this.postAccount = postAccount;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String getFormattedContent() {
		return "TextPost: " +text;
	}
}