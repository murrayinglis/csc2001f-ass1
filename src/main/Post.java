package main;

/**
 * Post class
 * Represents a post an TikTok
 * 
 * @author Murray Inglis
 */
public class Post
{
    // Instance variables

  
    private String title;
    private String name;
    private int likes;

    /**
     * Post constructor
     * 
     * @param title The post title as a <code>String</code>
     * @param name The video file name as a <code>String</code>
     * @param likes The number of likes as an <code>int</code>
     */
    public Post(String title, String name, int likes)
    {
        this.title = title;
        this.name = name;
        this.likes = likes;
    }

    /**
     * Returns a <code>String</code> representation
     * 
     * @return Returns the post information as a formatted <code>String</code>
     */
    public String toString()
    {
        return "Title: " + this.title + "\nVideo: " + this.name + "\nNumber of likes: " + this.likes;
    }
}