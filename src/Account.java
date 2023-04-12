import java.util.ArrayList;

public class Account
{
    // Instance variables
    private String accountName;
    private String profile;
    private ArrayList<Post> posts;

    public Account(String accountName, String profile)
    {
        this.accountName = accountName;
        this.profile = profile;
        this.posts = new ArrayList<>();
    }

    public Account(String accountName)
    {
        this.accountName = accountName;
    }

    public int compareTo(Account target)
    {
        if (this.accountName.compareTo(target.accountName) == 0) {return 0;}
        else if (this.accountName.compareTo(target.accountName) > 0) {return 1;}
        else if (this.accountName.compareTo(target.accountName) < 0) {return -1;}
        return 0;
    }

    public String toString()
    {
        return accountName;
    }

    public String getProfile() {return this.profile;}

    public ArrayList<Post> getPosts() {return this.posts;}

    public void addPost(Post post) {posts.add(post);}
}