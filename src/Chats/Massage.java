package Chats;

public class Massage {
    private String content;
    private String username;

    public Massage(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "-   -   -   -   -\n" + username + " :" + content + "\n-   -   -   -   -";
    }
}
