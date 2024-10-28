package unl.soc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public abstract class Post implements ContentFormatter {
    protected LocalDateTime postTime;
    protected Account postAccount;

    public LocalDateTime getPostTime() {
    	return postTime;
    }
    public Account getPostAccount() {
    	return postAccount;
    }
    public abstract String getFormattedContent();
    
    public String tag() {
        String s = postTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        return s + " by " + postAccount.getUsername();
    }
}
