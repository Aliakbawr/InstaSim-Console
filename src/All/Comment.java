package All;

import java.util.ArrayList;

public class Comment {
    public Comment(String comment, String username) {
        this.comment = comment;
        this.username = username;
        this.comments = new ArrayList<>();
    }

    private String comment;
    private String username;
    private ArrayList<Comment> comments;


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
