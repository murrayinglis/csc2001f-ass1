package test;

import main.Account;
import main.BST;
import main.Post;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing TikTok interface
 *
 * @author Murray Inglis
 */
public class TikTok
{
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Tiktok");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        frame.setSize(200,200);
        frame.setVisible(true);


        BST bst = new BST();
        Account account;
        String accountName;

        /**
        boolean quit = false;
        while (!quit)
        {
            menuScreen();
            String input = scanner.nextLine();
            switch(input)
            {
                case "1":
                    System.out.println("Enter account name: ");

                    // Getting account name
                    accountName = scanner.nextLine();

                    // Creating account object
                    account = new Account(accountName);

                    // Searching for account
                    Account searchRes = bst.search(account);

                    // Where account is not found
                    if (searchRes == null)
                    {
                        System.out.println("Account not found");
                    }
                    // Where account is found, print the profile description
                    else System.out.println("The profile description is: " + searchRes.getProfile());
                    break;
                case "2":
                    // Traverse through the whole BST
                    // Printing out the names of every account
                    bst.inorderTraversal();
                    break;
                case "3":
                    System.out.println("Enter account name followed by description: ");

                    // Getting account name and profile
                    accountName = scanner.next();
                    String profile = scanner.nextLine().trim(); //Using trim to remove leading whitespace

                    // Creating account object
                    account = new Account(accountName, profile);

                    // Inserting into tree
                    bst.insert(account);
                    break;
                case "4":
                    System.out.println("Enter account name: ");

                    // Getting account name
                    accountName = scanner.nextLine();

                    // Creating account object
                    account = new Account(accountName);

                    // Deleting object from the bst
                    bst.delete(account);
                    break;
                case "5":
                    System.out.println("Enter account name: ");

                    // Getting account name
                    accountName = scanner.nextLine();

                    // Creating account object
                    account = new Account(accountName);

                    // Searching for account
                    searchRes = bst.search(account);

                    // Getting the posts
                    ArrayList<Post> posts = searchRes.getPosts();

                    // Where the account has no posts
                    if (!posts.isEmpty())
                    {
                        for (Post post: posts)
                        {
                            System.out.println(post);
                        }
                    }
                    // Where the account has posts
                    else System.out.println("Account has no posts");

                    break;
                case "6":
                    System.out.println("Enter account name: ");

                    // Getting account name
                    accountName = scanner.nextLine();

                    // Creating account object
                    account = new Account(accountName);

                    // Searching for account
                    searchRes = bst.search(account);

                    // Where account is not in the BST
                    if (searchRes == null)
                    {
                        System.out.println("Account not found");
                    }
                    // Where account is in the BST
                    else
                    {
                        System.out.println("Enter post details (video name, likes, title): ");

                        // Getting post details
                        String postName = scanner.next();
                        int postLikes = scanner.nextInt();
                        String postTitle = scanner.nextLine().trim(); //Using trim to remove leading whitespace

                        // Creating post object
                        Post post = new Post(postTitle, postName, postLikes);

                        // Adding post to the account
                        searchRes.addPost(post);
                    }
                    break;
                case "7":
                    System.out.println("Enter file name: ");

                    // Getting and handling the file
                    String fileName = scanner.nextLine();
                    TikTok.handleFile(fileName, bst);
                    break;
                case "8":
                    // Quit case
                    quit = true;
                    System.out.println("Bye!");
                    break;
                default:
                    // Invalid input case
                    System.out.println("Invalid input");
                    break;
            }
         **/

    }

    /**
     * Prints the menu screen
     *
     */

    private static void menuScreen()
    {
        System.out.println("Choose an action from the menu:");
        System.out.println("1. Find the profile description for a given account");
        System.out.println("2. List all accounts");
        System.out.println("3. Create an account");
        System.out.println("4. Delete an account");
        System.out.println("5. Display all posts for a single account");
        System.out.println("6. Add a new post for an account");
        System.out.println("7. Load a file of actions from disk and process this");
        System.out.println("8. Quit");
        System.out.println("Enter your choice:");
    }

    /**
     * Method to convert file to a String and process the file
     *
     * @param fileName the file name as a <code>String</code>
     * @throws FileNotFoundException an error reading the file
     */
    private static void handleFile(String fileName, BST bst) throws FileNotFoundException {
        try
        {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            // Reading file line by line
            while (reader.hasNextLine())
            {
                String action = reader.next();
                // Creating an account
                if (action.equals("Create"))
                {
                    // Creating account
                    String accountName = reader.next();
                    String accountProfile = reader.nextLine().trim(); // Using trim to fix leading whitespace
                    Account account = new Account(accountName, accountProfile);

                    // Inserting account into BST
                    bst.insert(account);
                }
                // Adding a post to an account
                else if (action.equals("Add"))
                {
                    String accountName = reader.next();
                    String postName = reader.next();
                    int postLikes = reader.nextInt();
                    String postTitle = reader.nextLine().trim(); // Using trim to fix leading whitespace

                    Post post = new Post(postTitle, postName, postLikes);

                    Account temp = new Account(accountName);

                    // Searching for account in the BST and adding a post to it
                    Account searchRes = bst.search(temp);
                    searchRes.addPost(post);
                }

            }
        }
        catch (Exception e)
        {
            throw new FileNotFoundException("Error reading the file");
        }
    }
}