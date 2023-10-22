package All.Post;
import All.Comment;
import All.User.User;

import java.util.ArrayList;

public class Post {
    private String content;
    private int likes = 0;
    private int disLikes = 0;
    private int commentsNum = 0;

    public ArrayList<Comment> comments = new ArrayList<>();
    public ArrayList<Post> seenPosts = new ArrayList<>();
    public ArrayList<User> likedBy  = new ArrayList<>();
    public ArrayList<User> dislikedBy  = new ArrayList<>();
    public ArrayList<User> sees  = new ArrayList<>();

    public Post(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(int disLikes) {
        this.disLikes = disLikes;
    }
    @Override
    public String toString() {
        return
                "content : '" + content + '\'' +
                        "\n, likes=" + likes +
                        "\t, disLikes=" + disLikes +
                        "\t, commentsNum=" + commentsNum +
                        '}';
    }
}

