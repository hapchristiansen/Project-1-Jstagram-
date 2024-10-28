package unl.soc;

import java.io.*;
import java.util.*;

public class Main {

    // file where all the accounts and posts will be saved
    private static final String DATA_FILE = "jstagram_data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();  // this holds all the accounts
        List<Post> postList = new ArrayList<>();  // this holds all the posts

        // adding the admin account at the start
        accounts.put("Admin", new Account("Admin", "Password", "402-326-6488"));

        // load saved data from file if it exists
        loadData(accounts, postList);

        boolean isRunning = true;  // this is the main loop flag

        // main loop where the user chooses what to do
        while (isRunning) {
            Views.mainWindow();  // display the main menu
            System.out.print("Enter choice (R for Register, L for Login, Q for Quit): ");
            String choice = scanner.nextLine().toUpperCase();  // get user input

            switch (choice) {
                case "R":
                    // registering a new account
                    registerAccount(accounts, scanner);
                    break;
                case "L":
                    // logging in with an account
                    Account currentAccount = login(accounts, scanner);
                    if (currentAccount != null) {
                        if (currentAccount.getUsername().equals("Admin")) {
                            // admin stuff happens here
                            adminOptions(accounts, postList, scanner);
                        } else {
                            // if it's just a normal user, let them manage posts
                            managePosts(postList, currentAccount, scanner);
                        }
                    }
                    break;
                case "Q":
                    // save everything to the file and quit
                    saveData(accounts, postList);
                    System.out.println("Goodbye!");
                    isRunning = false;  // stop the loop
                    break;
                default:
                    System.out.println("Not a valid choice. try again.");
            }
        }
        scanner.close();  // close the scanner when done
    }

    // saves accounts and posts to the data file (admin use only)
    private static void saveData(Map<String, Account> accounts, List<Post> posts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("jstagram_data.ser"))) {
            oos.writeObject(accounts);  // save all accounts
            oos.writeObject(posts);     // save all posts
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Could not save data. " + e.getMessage());
        }
    }


    // loads accounts and posts from the data file (if it exists)
    private static void loadData(Map<String, Account> accounts, List<Post> posts) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("jstagram_data.ser"))) {
            Map<String, Account> savedAccounts = (Map<String, Account>) ois.readObject();
            List<Post> savedPosts = (List<Post>) ois.readObject();

            // add the loaded data to the current lists
            accounts.putAll(savedAccounts);
            posts.addAll(savedPosts);
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found.");
        }
    }


    // admin-only options to save and load data
    private static void adminOptions(Map<String, Account> accounts, List<Post> posts, Scanner scanner) {
        System.out.print("Admin options: (S to save data, L to load data, Q to quit: ");
        String adminChoice = scanner.nextLine();

        switch (adminChoice) {
            case "S":
                saveData(accounts, posts);  // admin saves data
                break;
            case "L":
                loadData(accounts, posts);  // admin loads data
                break;
            case "Q":
                System.out.println("Back to main menu.");
                break;
            default:
                System.out.println("Not a valid option.");
        }
    }

    // lets users manage their posts
    private static void managePosts(List<Post> postList, Account currentAccount, Scanner scanner) {
        boolean managingPosts = true;

        while (managingPosts) {
            // ask the user how they want to sort the posts
            System.out.print("Sort by: (1 for descending time, 2 for ascending time, 3 for ascending user,4 for descending user: ");
            int sortOption = Integer.parseInt(scanner.nextLine());
            sortPosts(postList, sortOption);  // sort based on the choice

            Views.postViewWindow(postList, currentAccount);  // show the sorted posts

            System.out.print("Enter choice (+ for new post, L to logout, D to delete account, Q to quit): ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "+":
                    // let user make a new post
                    createNewPost(postList, currentAccount, scanner);
                    break;
                case "L":
                    System.out.println("Logging out...");
                    managingPosts = false;  // stop managing posts
                    break;
                case "D":
                    // delete the current account and posts
                    deleteAccount(postList, currentAccount);
                    managingPosts = false;  // stop managing after deletion
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    System.exit(0);  // exit the program
                default:
                    System.out.println("Not a valid choice. Try again.");
            }
        }
    }

    // makes a new post based on user input
    private static void createNewPost(List<Post> postList, Account currentAccount, Scanner scanner) {
        Views.newPostWindow(currentAccount);
        System.out.print("Choose post type (T for text, A for art): ");
        String type = scanner.nextLine().toUpperCase();

        if (type.equals("T")) {
            // make a text post
            System.out.print("Enter your text: ");
            String text = scanner.nextLine();
            postList.add(new TextPost(text, currentAccount));
            System.out.println("Text post created.");
        } else if (type.equals("A")) {
            // make a text art post
            Views.displayTextArtOptions(currentAccount);
            System.out.print("Choose an art option by number: ");
            int optionIndex = Integer.parseInt(scanner.nextLine());
            postList.add(new TextArtPost(optionIndex, currentAccount));
            System.out.println("Text art post created.");
        } else {
            System.out.println("Not a valid post type.");
        }
    }

    // deletes an account and its posts
    private static void deleteAccount(List<Post> postList, Account account) {
        postList.removeIf(post -> post.postAccount.equals(account));  // remove all posts from the account
        System.out.println("Account and posts deleted.");
    }

    // sorts posts based on the option chosen
    private static void sortPosts(List<Post> posts, int option) {
        switch (option) {
            case 1:  // sort by time descending
                posts.sort(Comparator.comparing(Post::getPostTime, Comparator.nullsLast(Comparator.reverseOrder())));
                break;
            case 2:  // sort by time ascending
                posts.sort(Comparator.comparing(Post::getPostTime, Comparator.nullsFirst(Comparator.naturalOrder())));
                break;
            case 3:  // sort by username ascending
                posts.sort(Comparator.comparing(p -> p.getPostAccount().getUsername(), String.CASE_INSENSITIVE_ORDER));
                break;
            case 4:  // sort by username descending
                posts.sort(Comparator.comparing(p -> p.getPostAccount().getUsername(), String.CASE_INSENSITIVE_ORDER.reversed()));
                break;
            default:
                System.out.println("Invalid sort option. Defaulting to descending time.");
                posts.sort(Comparator.comparing(Post::getPostTime, Comparator.nullsLast(Comparator.reverseOrder())));
        }
    }


    // registers a new account
    private static void registerAccount(Map<String, Account> accounts, Scanner scanner) {
        Views.accountRegistrationWindow();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        accounts.put(username, new Account(username, password, phoneNumber));
        System.out.println("Account registered successfully.");
    }

    // logs in a user if the credentials are correct
    private static Account login(Map<String, Account> accounts, Scanner scanner) {
        Views.accountLoginWindow();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Account account = accounts.get(username);

        // Check if the account exists and the password matches
        if (account != null && account.getpassword().equals(password)) {
            System.out.println("Login successful.");

            // If the logged-in user is the admin, trigger the admin menu
            if (username.equalsIgnoreCase("Admin")) {
                adminOptions(accounts, new ArrayList<>(), scanner);  // Pass admin menu control
            }

            return account;  // Return the logged-in account
        } else {
            System.out.println("Invalid credentials. Returning to main menu.");
            return null;
        }
    }}
