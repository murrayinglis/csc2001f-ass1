package main;

import java.util.ArrayList;

/**
 * Account class
 * Represents an account on TikTok
 * 
 * @author Murray Inglis 
 */
public class Account
{
    // Instance variables
    private String accountName;
    private String profile;
    private ArrayList<Post> posts;

    /**
     * Account constructor
     * 
     * @param accountName The name of the account as a <code>String</code>
     * @param profile The profile description of the account as a <code>String</code>
     */
    public Account(String accountName, String profile)
    {
        this.accountName = accountName;
        this.profile = profile;
        this.posts = new ArrayList<>();
    }

    /**
     * Account constructor
     * 
     * @param accountName The name of the account as a <code>String</code>
     */
    public Account(String accountName)
    {
        this.accountName = accountName;
    }

    /**
     * Compares the account names of 2 <code>Account</code> objects
     * 
     * @return -1 if less than, 1 if greater than, 0 if equal
     */
    public int compareTo(Account target)
    {
        if (this.accountName.compareTo(target.accountName) == 0) {return 0;}
        else if (this.accountName.compareTo(target.accountName) > 0) {return 1;}
        else if (this.accountName.compareTo(target.accountName) < 0) {return -1;}
        return 0;
    }

    /**
     * Returns a <code>String</code> representation
     * 
     * @return Returns the account name as a <code>String</code>
     */
    public String toString()
    {
        return accountName;
    }

    /**
     * Returns the profile description
     * 
     * @return Returns the profile description as a <code>String</code>
     */
    public String getProfile() {return this.profile;}

    /**
     * Returns a list of the account's posts
     * 
     * @return Returns the account posts as an <code>ArrayList<Post></code>
     */
    public ArrayList<Post> getPosts() {return this.posts;}

    /**
     * Adds a post to the account
     * 
     * @param post The post to add to the account as a <code>Post</code>
     */
    public void addPost(Post post) {posts.add(post);}
}