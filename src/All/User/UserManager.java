package All.User;

import All.Page.Page;
import All.Post.Post;

import java.util.ArrayList;

public class UserManager {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Post> explore = new ArrayList<>();

    public static User findUser(String username) // finds a user by username
    {
        for (User user1 : users)
            if (user1.getUsername().equals(username))
                return user1;
        return null;
    }



    public static void editUser(String username, String firstname, String lastname, // edits exciting user
                                String phoneNumber, String email, String password)
    {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setPhoneNumber(phoneNumber);
                user.setEmail(email);
                user.setPassword(password);
                return;
            }
        }
    }


    public static void addUser(String username, String firstname, String lastname // creates new user
            , String phoneNumber, String email, String password, Page page)
    {
        users.add(new User(username,firstname,lastname,phoneNumber,email,password, page));
    }

    public static ArrayList Explore()
    {
        for (int i = 0; i < UserManager.users.size(); i++) {
            for (int j = 0; j < UserManager.users.get(i).getPage().posts.size() ; j++) {
                if(UserManager.users.get(i).getPage().posts.get(j).getLikes() > 5)
                {
                    explore.add(UserManager.users.get(i).getPage().posts.get(j));
                }
            }
        }
        return explore;
    }

}
