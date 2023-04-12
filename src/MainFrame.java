import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.intellij.uiDesigner.core.*;

public class MainFrame extends JFrame {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    public MainFrame() {
        initComponents();
        setContentPane(mainPanel);
        setTitle("TikTok");
        setSize(800, 800);
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
                } else if (OKButtonMode[0] == 2) {
                    String accountName = textField1.getText();
                    Account temp = new Account(accountName);

                    Account searchRes = bst.search(temp);

                    // Where account is not found
                    if (searchRes == null) {
                        outputLabel.setText("Account not found");
                    }
                    // Where account is found, print the profile description
                    else
                        outputLabel.setText("Profile description for account " + accountName + ": " + searchRes.getProfile());
                } else if (OKButtonMode[0] == 3) {
                    String accountName = textField1.getText();
                    Account account = new Account(accountName);
                    bst.delete(account);
                    outputLabel.setText("Deleted account: " + accountName);
                } else if (OKButtonMode[0] == 4) {
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
                        if (!posts.isEmpty()) {
                            for (Post post : posts) {
                                textArea.append(post.toString());
                                textArea.append("\n");
                            }
                            outputLabel.setText("Showing all posts for: " + accountName);
                        }
                        // Where the account has posts
                        else outputLabel.setText("Account has no posts");

                    } else outputLabel.setText("Account '" + accountName + "' could not be found");
                    scrollPane.setViewportView(textArea);
                } else if (OKButtonMode[0] == 5) {
                    String accountName = textField1.getText();
                    Account account = new Account(accountName);

                    // Searching for account
                    Account searchRes = bst.search(account);

                    // Where account is not in the BST
                    if (searchRes == null) {
                        outputLabel.setText("Account not found");
                    }
                    // Where account is in the BST
                    else {
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
                } else if (OKButtonMode[0] == 6) {
                    String fileName = textField1.getText();
                    try {
                        handleFile(fileName, bst);
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

    private void reset() {
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
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            // Reading file line by line
            while (reader.hasNextLine()) {
                String action = reader.next();
                // Creating an account
                if (action.equals("Create")) {
                    // Creating account
                    String accountName = reader.next();
                    String accountProfile = reader.nextLine().trim(); // Using trim to fix leading whitespace
                    Account account = new Account(accountName, accountProfile);

                    // Inserting account into BST
                    bst.insert(account);
                }
                // Adding a post to an account
                else if (action.equals("Add")) {
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
        } catch (Exception e) {
            outputLabel.setText("Error reading the file");
            throw new FileNotFoundException("Error reading the file");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - unknown
        var panel1 = new JPanel();
        mainPanel = new JPanel();
        findProfileDescriptionButton = new JButton();
        listAllAccountsButton = new JButton();
        createAnAccountButton = new JButton();
        deleteAnAccountButton = new JButton();
        displayAllPostsForButton = new JButton();
        quitButton = new JButton();
        optionLabel = new JLabel();
        textField1 = new JTextField();
        accountLabel = new JLabel();
        accountDescription = new JLabel();
        textField2 = new JTextField();
        nameLabel = new JLabel();
        textField3 = new JTextField();
        likesLabel = new JLabel();
        addANewPostButton = new JButton();
        titleLabel = new JLabel();
        textField4 = new JTextField();
        loadAFileOfButton = new JButton();
        textField5 = new JTextField();
        outputLabel = new JLabel();
        scrollPane = new JScrollPane();
        OKButton = new JButton();

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
            new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn"
            , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 )
            ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );
            panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

            //======== mainPanel ========
            {
                mainPanel.setBorder(new TitledBorder(""));
                mainPanel.setLayout(new GridLayoutManager(13, 2, new Insets(0, 0, 0, 0), -1, -1));

                //---- findProfileDescriptionButton ----
                findProfileDescriptionButton.setText("Find profile description");
                mainPanel.add(findProfileDescriptionButton, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- listAllAccountsButton ----
                listAllAccountsButton.setText("List all accounts");
                mainPanel.add(listAllAccountsButton, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- createAnAccountButton ----
                createAnAccountButton.setText("Create an account");
                mainPanel.add(createAnAccountButton, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- deleteAnAccountButton ----
                deleteAnAccountButton.setText("Delete an account");
                mainPanel.add(deleteAnAccountButton, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- displayAllPostsForButton ----
                displayAllPostsForButton.setText("Display all posts for an account");
                mainPanel.add(displayAllPostsForButton, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- quitButton ----
                quitButton.setText("Quit");
                mainPanel.add(quitButton, new GridConstraints(12, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- optionLabel ----
                optionLabel.setText("Select an option");
                mainPanel.add(optionLabel, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- textField1 ----
                textField1.setEditable(true);
                textField1.setEnabled(true);
                mainPanel.add(textField1, new GridConstraints(1, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- accountLabel ----
                accountLabel.setEnabled(true);
                accountLabel.setText("Enter account name:");
                mainPanel.add(accountLabel, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- accountDescription ----
                accountDescription.setText("Enter account description:");
                mainPanel.add(accountDescription, new GridConstraints(2, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(225, 16), null));
                mainPanel.add(textField2, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(225, 30), null));

                //---- nameLabel ----
                nameLabel.setText("Enter video name:");
                mainPanel.add(nameLabel, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                mainPanel.add(textField3, new GridConstraints(5, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- likesLabel ----
                likesLabel.setText("Enter number of likes:");
                mainPanel.add(likesLabel, new GridConstraints(6, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- addANewPostButton ----
                addANewPostButton.setText("Add a new post for an account");
                mainPanel.add(addANewPostButton, new GridConstraints(6, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- titleLabel ----
                titleLabel.setText("Enter video title:");
                mainPanel.add(titleLabel, new GridConstraints(8, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                mainPanel.add(textField4, new GridConstraints(7, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- loadAFileOfButton ----
                loadAFileOfButton.setText("Load a file of actions");
                mainPanel.add(loadAFileOfButton, new GridConstraints(7, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                mainPanel.add(textField5, new GridConstraints(9, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- outputLabel ----
                outputLabel.setText("");
                mainPanel.add(outputLabel, new GridConstraints(11, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                mainPanel.add(scrollPane, new GridConstraints(10, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //---- OKButton ----
                OKButton.setText("OK");
                mainPanel.add(OKButton, new GridConstraints(12, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(225, 30), null));
            }
            panel1.add(mainPanel, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel mainPanel;
    private JButton findProfileDescriptionButton;
    private JButton listAllAccountsButton;
    private JButton createAnAccountButton;
    private JButton deleteAnAccountButton;
    private JButton displayAllPostsForButton;
    private JButton quitButton;
    private JLabel optionLabel;
    private JTextField textField1;
    private JLabel accountLabel;
    private JLabel accountDescription;
    private JTextField textField2;
    private JLabel nameLabel;
    private JTextField textField3;
    private JLabel likesLabel;
    private JButton addANewPostButton;
    private JLabel titleLabel;
    private JTextField textField4;
    private JButton loadAFileOfButton;
    private JTextField textField5;
    private JLabel outputLabel;
    private JScrollPane scrollPane;
    private JButton OKButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
