package unl.soc;

import java.util.List;

public class Views {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_Green = "\u001B[32m";

    public static final String ANSI_Yellow = "\u001B[33m";

    public static final String ANSI_Blue = "\u001B[34m";

    public static final String ANSI_Cyan = "\u001B[36m";

    public static void mainWindow() {
        System.out.print(ANSI_Green);
        System.out.println(" ========================================");
        System.out.println("|         Welcome to Jstgram!            |");
        System.out.println("|                                        |");
        System.out.println("|              *************             |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|              *     *                   |");
        System.out.println("|              *******                   |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|   Register(R) or Login(L) or Quit(Q)   |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }

    public static void accountRegistrationWindow() {
        System.out.print(ANSI_Yellow);
        System.out.println(" ========================================");
        System.out.println("|          Register New Account          |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|         * User Name   _______          |");
        System.out.println("|         * Password    _______          |");
        System.out.println("|         * Phone Num   _______          |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }

    public static void accountLoginWindow() {
        System.out.print(ANSI_Blue);
        System.out.println(" ========================================");
        System.out.println("|                 Login                  |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|         * User Name   _______          |");
        System.out.println("|         * Password    _______          |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }


    public static void postViewWindow(List<Post> posts, Account currentAccount) {
        System.out.print(ANSI_Cyan);
        System.out.println(" ========================================");
        for (Post p : posts) {
            System.out.print(p.getFormattedContent());
            System.out.printf("|%40s|\n", p.tag());
        }
        System.out.println("|  New Post(+) or Logout(L) or Quit(Q)   |");
        if (currentAccount != null)
        	System.out.print(currentAccount.getFormattedContent());
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }

    public static void newPostWindow(Account currentAccount) {
        System.out.print(ANSI_Cyan);
        System.out.println(" ========================================");
        System.out.println("|  Text Post(T) or Text Art Post(A)      |");
        if (currentAccount != null)
        	System.out.print(currentAccount.getFormattedContent());
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }

    public static void displayTextArtOptions(Account currentAccount) {
        System.out.print(ANSI_Cyan);
        System.out.println(" ========================================");
        for (int i = 0; i < TextArtPost.OPTIONS.size(); i++) {
        	String[] strings = TextArtPost.OPTIONS.get(i);
            System.out.printf("|%d : %-36s|\n", i, strings[0]);
        	for(int j = 1; j<strings.length; j++) 
                System.out.printf("|    %-36s|\n", strings[j]);
        	System.out.printf("|%40s|\n", "");
        }
        System.out.println("|    Which Text Art do you choose?       |");
        if (currentAccount != null)
        	System.out.print(currentAccount.getFormattedContent());
        System.out.println(" ========================================");
        System.out.print(ANSI_RESET);
    }
 
    
}
