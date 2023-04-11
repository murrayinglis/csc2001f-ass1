package main;

public class Post
{
    // Instance variables
    private String title;
    private String name;
    private int likes;

    public Post(String title, String name, int likes)
    {
        this.title = title;
        this.name = name;
        this.likes = likes;
    }

    public String toString()
    {
        return "Title: " + this.title + "\nVideo: " + this.name + "\nNumber of likes: " + this.likes;
    }
}