package Chats;

import All.User.User;

import java.util.ArrayList;

public class Chat {
    public ArrayList<Massage> massages = new ArrayList<>();
    public User userDirect;

    public Chat(User user, ArrayList<Massage> massages) {
        this.userDirect = user;
        this.massages = massages;
    }

    @Override
    public String toString() {
        return userDirect.getUsername();
    }
}
