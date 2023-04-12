import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainFrame extends JFrame{
    private JButton findProfileDescriptionButton;
    private JButton listAllAccountsButton;
    private JButton createAnAccountButton;
    private JButton deleteAnAccountButton;
    private JButton displayAllPostsForButton;
    private JButton addANewPostButton;
    private JButton loadAFileOfButton;
    private JButton quitButton;
    private JPanel mainPanel;
    private JTextField textField1;
    private JLabel accountLabel;
    private JLabel optionLabel;
    private JLabel outputLabel;
    private JScrollPane scrollPane;
    private JTextField textField2;
    private JLabel accountDescription;
    private JButton OKButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel nameLabel;
    private JLabel likesLabel;
    private JLabel titleLabel;

    public static void main(String[] args)
    {
        MainFrame mainFrame = new MainFrame();
    }

    public MainFrame()
    {
        setContentPane(mainPanel);
        setTitle("TikTok");
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        reset();
        setVisible(true);

        final int[] OKButtonMode = {0};

        BST bst = new BST();

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        findProfileDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                textField1.setVisible(true);
                accountLabel.setVisible(true);
                OKButton.setVisible(true);
                outputLabel.setVisible(true);
                OKButtonMode[0] = 2;
            }
        });

        listAllAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                JTextArea textArea = new JTextArea();
                bst.inorderTraversal(textArea);
                scrollPane.setViewportView(textArea);
                scrollPane.setVisible(true);
            }
        });

        createAnAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                OKButton.setVisible(true);
                accountLabel.setVisible(true);
                outputLabel.setVisible(true);
                textField1.setVisible(true);
                accountDescription.setVisible(true);
                textField2.setVisible(true);
                OKButtonMode[0] = 1;
            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OKButtonMode[0] == 1) {
                    String accountName = textField1.getText();
                    String accountDescription = textField2.getText();
                    Account account = new Account(accountName, accountDescription);
                    bst.insert(account);
                    outputLabel.setText("Added account: " + accountName);
                }
                else if (OKButtonMode[0] == 2)
                {
                    String accountName = textField1.getText();
                    Account temp = new Account(accountName);

                    Account searchRes = bst.search(temp);

                    // Where account is not found
                    if (searchRes == null)
                    {
                        outputLabel.setText("Account not found");
                    }
                    // Where account is found, print the profile description
                    else outputLabel.setText("Profile description for account " + accountName + ": " + searchRes.getProfile());
                }
                else if (OKButtonMode[0] == 3)  {
                    String accountName = textField1.getText();
                    Account account = new Account(accountName);
                    bst.delete(account);
                    outputLabel.setText("Deleted account: " + accountName);
                }
                else if (OKButtonMode[0] ==4) {
                    String accountName = textField1.getText();
                    Account account = new Account(accountName);
                    JTextArea textArea = new JTextArea("");

                    // Searching for account
                    Account searchRes = bst.search(account);

                    // If account exists
                    if (searchRes != null) {
                        // Getting the posts
                        ArrayList<Post> posts = searchRes.getPosts();

                        // Where the account has no posts
                        if (!posts.isEmpty())
                        {
                            for (Post post: posts)
                            {
                                textArea.append(post.toString());
                                textArea.append("\n");
                            }
                            outputLabel.setText("Showing all posts for: " + accountName);
                        }
                        // Where the account has posts
                        else outputLabel.setText("Account has no posts");

                    }
                    else outputLabel.setText("Account '" + accountName + "' could not be found");
                    scrollPane.setViewportView(textArea);
                }
                else if (OKButtonMode[0] == 5) {
                    String accountName = textField1.getText();
                    Account account = new Account(accountName);

                    // Searching for account
                    Account searchRes = bst.search(account);

                    // Where account is not in the BST
                    if (searchRes == null)
                    {
                        outputLabel.setText("Account not found");
                    }
                    // Where account is in the BST
                    else
                    {
                        // Getting post details
                        String postName = textField3.getText();
                        int postLikes = Integer.parseInt(textField4.getText());
                        String postTitle = textField5.getText();

                        // Creating post object
                        Post post = new Post(postTitle, postName, postLikes);

                        // Adding post to the account
                        searchRes.addPost(post);

                        outputLabel.setText("Added new post to account: " + accountName);
                    }
                }
                else if (OKButtonMode[0] == 6) {
                    String fileName = textField1.getText();
                    try {
                        handleFile(fileName,bst);
                        outputLabel.setText("File added to the database");
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        outputLabel.setText("Error adding the file");
                    }
                }

            }
        });

        deleteAnAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                accountLabel.setVisible(true);
                textField1.setVisible(true);
                OKButton.setVisible(true);
                outputLabel.setVisible(true);
                OKButtonMode[0] = 3;
            }
        });

        displayAllPostsForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                scrollPane.setVisible(true);
                accountLabel.setVisible(true);
                textField1.setVisible(true);
                OKButton.setVisible(true);
                outputLabel.setVisible(true);
                OKButtonMode[0] = 4;
            }
        });

        addANewPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                nameLabel.setVisible(true);
                likesLabel.setVisible(true);
                titleLabel.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);
                textField5.setVisible(true);
                OKButton.setVisible(true);
                outputLabel.setVisible(true);
                accountLabel.setVisible(true);
                textField1.setVisible(true);
                OKButtonMode[0] = 5;
            }
        });

        loadAFileOfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                accountLabel.setText("Enter file name: ");
                accountLabel.setVisible(true);
                textField1.setVisible(true);
                outputLabel.setVisible(true);
                OKButton.setVisible(true);
                OKButtonMode[0] = 6;
            }
        });
    }

    private void reset()
    {
        accountLabel.setText("Enter account name: ");
        OKButton.setVisible(false);
        outputLabel.setVisible(false);
        outputLabel.setText("");
        accountLabel.setVisible(false);
        textField1.setVisible(false);
        accountDescription.setVisible(false);
        textField2.setVisible(false);
        scrollPane.setVisible(false);
        nameLabel.setVisible(false);
        likesLabel.setVisible(false);
        titleLabel.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
    }

    /**
     * Method to convert file to a String and process the file
     *
     * @param fileName the file name as a <code>String</code>
     * @throws FileNotFoundException an error reading the file
     */
    private void handleFile(String fileName, BST bst) throws FileNotFoundException {
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
            outputLabel.setText("Error reading the file");
            throw new FileNotFoundException("Error reading the file");
        }
    }
}
