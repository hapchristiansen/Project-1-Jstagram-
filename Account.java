package unl.soc;

import java.io.Serializable;

public class Account implements Serializable{
    private final String userName;
    private final String password;
    private final String phoneNumber;

	public Account(String userName, String password, String phoneNumber) {
    	this.userName = userName;
    	this.password = password;
    	this.phoneNumber = phoneNumber;
    	
    }
    public String getUsername() {
        return userName;
    }
    public String getphoneNumber() {
        return phoneNumber;
    }
    public String getpassword() {
        return password;
    }

    public String getFormattedContent() {
        return String.format("|  Current user : %-23s|\n", userName);
    }
}
