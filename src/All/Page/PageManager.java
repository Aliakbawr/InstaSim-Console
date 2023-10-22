package All.Page;

import All.Post.Post;
import All.User.*;
import Chats.Massage;

import java.util.*;

public class PageManager {
    public static void sendFollowRequest(User sender, User receiver)   //1) sends & adds follow Req...
    {
            for (int i = 0; i < receiver.getPage().follow_requests.size(); i++) {
                if (receiver.getPage().follow_requests.get(i) == sender) return;
            }
            receiver.getPage().follow_requests.add(sender);
    }

    public static void setUpPage(User user, String bio, boolean isPrivate)    // set & edit page info
    {
        user.getPage().setBio(bio);
        user.getPage().setPrivate(isPrivate);
    }
    public static boolean addFollower(User user,User follower)  //2) add follower
    {
        for (int i = 0; i < user.getPage().followers.size(); i++) {
            if (user.getPage().followers.get(i) == follower) return false;
        }

        user.getPage().setFollowersNumber(user.getPage().getFollowersNumber()+1);
        user.getPage().followers.add(follower);
        follower.getPage().followings.add(user);
        follower.getPage().setFollowingsNumber(follower.getPage().getFollowingsNumber()+1);
        return true;
    }
    public static boolean addFollowing(User user,User following)    //2) add following
    {
        for (int i = 0; i < user.getPage().followings.size(); i++)
            if (user.getPage().followings.get(i) == following) return false;

        user.getPage().setFollowingsNumber(user.getPage().getFollowingsNumber()+1);
        user.getPage().followings.add(following);
        following.getPage().followers.add(user);
        following.getPage().setFollowersNumber(following.getPage().getFollowersNumber()+1);
        return true;
    }
    public static void startChat(String massage, User user, ArrayList<Massage> massages)
    {
        Massage massage1 = new Massage(massage,user.getUsername());
        massages.add(massage1);
    }

    public static boolean CheckSeenPosts(Page page, Post post)
    {
        for (int i = 0; i < page.SeenPosts.size(); i++) {
            if(page.SeenPosts.get(i).equals(post))
            {
                return false;
            }
        }
        return true;
    }
    //suggestions -     -       -       -       -       -       -

    public static int findCommonNumber(ArrayList<User> users1,ArrayList<User> users2)
    {
        Set<User> set = new HashSet<>();
        for (int i = 0; i < users1.size(); i++) {
            for (int j = 0; j < users2.size(); j++) {
                if (users1.get(i) == users2.get(j)) {

                    // add common elements
                    set.add(users1.get(i));
                    break;
                }
            }
        }
        return set.size();
    }
    public static void suggest(User user)
    {
        int commonNumber = 0;
        for (int i = 0; i < user.getPage().followings.size(); i++) {
            for (int j = 0; j < user.getPage().followings.get(i).getPage().followings.size(); j++) {
                commonNumber+= findCommonNumber(user.getPage().followings, user.getPage().followings.get(i).getPage().followings);
                SuggestedPerson person = new SuggestedPerson(user.getPage().followings.get(i).getPage().followings.get(j),commonNumber);
                user.getPage().suggestedPeople.add(person);
                Arrays.sort(new ArrayList[]{user.getPage().suggestedPeople}, Collections.reverseOrder());
            }
        }
    }
}

