package All.Page;

import All.Post.Post;

import All.User.User;
import Chats.Chat;

import java.util.ArrayList;

public class Page {
    public ArrayList<SuggestedPerson> suggestedPeople = new ArrayList<>();
    private String bio;
    private boolean isPrivate;
    private int followersNumber;
    private int followingsNumber;
    private int postNumber;

    public ArrayList<Post> SeenPosts = new ArrayList<>();
    public ArrayList<User> followers = new ArrayList<>();
    public ArrayList<User> followings = new ArrayList<>();
    public ArrayList<User> follow_requests = new ArrayList<>();
    public ArrayList<Post> posts = new ArrayList<>();
    public ArrayList<Chat> chats = new ArrayList<>();
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getFollowersNumber() {
        return followersNumber;
    }

    public void setFollowersNumber(int followersNumber) {
        this.followersNumber = followersNumber;
    }

    public int getFollowingsNumber() {
        return followingsNumber;
    }

    public void setFollowingsNumber(int followingsNumber) {
        this.followingsNumber = followingsNumber;
    }

    public Page(String bio, boolean isPrivate) {
        this.bio = bio;
        this.isPrivate = isPrivate;
        this.followersNumber = 0;
        this.followingsNumber = 0;
        this.postNumber = 0;
    }

    public Page() {
        this.bio = "";
        this.isPrivate = false;
        this.followersNumber = 0;
        this.followingsNumber = 0;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    @Override
    public String toString() {
        return "\nposts : " + posts.size() + "\tfollowers : "
                + getFollowersNumber() + "\tfollowings : " + getFollowingsNumber() +
                "\n"+ getBio() + "\n";
    }
}