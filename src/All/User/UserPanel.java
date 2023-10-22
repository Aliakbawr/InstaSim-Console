package All.User;


import All.Comment;
import All.Page.PageManager;

import All.Post.PostManager;
import Chats.Chat;
import Chats.Massage;
import panel.Panel;

import java.util.ArrayList;
import java.util.Scanner;

import static All.Post.PostManager.createPost;

public class UserPanel {
    static Scanner scanner = new Scanner(System.in);

    public static void mainPanel(User user) {
        ShowProfile(user);
        System.out.println("1.Home page\t2.explore & search\n3.notifications\t4.profile\t5.log out");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> homePage(user);
            case 2 -> explore_search(user);
            case 3 -> notificationsPanel(user);
            case 4 -> profile(user);
            case 5 -> Panel.mainPanel();
        }
    }

    public static void profile(User user) {
        ShowProfile(user);
        System.out.println("1.edit personal info\t2.add post\t3.edit Page\n4.Show followers\t5.Show followings\t6.return");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> editInfo(user);
            case 2 -> createNewPost(user);
            case 4 -> showFollowers(user);
            case 5 -> showFollowings(user);
            case 6 -> mainPanel(user);
        }
    }

    public static void editInfo(User user) {
        String firstname, lastname, phoneNumber, email, password;
        System.out.println("Enter new firstname");
        firstname = scanner.next();
        System.out.println("Enter new lastname");
        lastname = scanner.next();
        System.out.println("Enter new email");
        email = scanner.next();
        System.out.println("Enter new phoneNumber");
        phoneNumber = scanner.next();
        System.out.println("Enter new password");
        password = scanner.next();
        UserManager.editUser(user.getUsername(), firstname, lastname, phoneNumber, email, password);
        System.out.println("done");
        System.out.println(user);
        profile(user);
    }


    public static void notificationsPanel(User user)
    {
        System.out.println("Choose : 1.Notifications 2.Suggestions -1.return");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1: Notifs(user);break;
            case 2: SuggestPanel(user);break;
            case -1:mainPanel(user);
        }

    }

    public static void Notifs(User user) {
        int choice03, i;
        System.out.println("Follow requests");

        if (!user.getPage().follow_requests.isEmpty()) {
            for (i = 0; i < user.getPage().follow_requests.size(); i++) {
                System.out.println((i + 1) + ".'" + user.getPage().follow_requests.get(i).getUsername() +
                        "'wants to follow you" +
                        "\n-    -   -   -   -");
            }
            {
                {
                    int choice02 = scanner.nextInt();
                    choice03 = choice02 - 1;
                    scanner.nextLine();
                    System.out.println(user.getPage().follow_requests.get(choice03).getPage().toString());
                    System.out.println("1.accept 2.reject 3.return");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                                PageManager.addFollower(user, user.getPage().follow_requests.get(i));
                            }catch (Exception e){}
                            user.getPage().setFollowersNumber((user.getPage().getFollowingsNumber()+1));
                            System.out.println("Accepted ");
                        case 2:
                            try {
                                user.getPage().follow_requests.remove(user.getPage().follow_requests.get(i));
                            }catch (Exception e){}
                        case 4:
                            notificationsPanel(user);
                        case 3:
                            mainPanel(user);
                            break;
                    }
                }
            }
        } else {
            System.out.println("No request yet");
            mainPanel(user);
        }
    }
    public static void explore_search(User user) {
        System.out.println("1.search    2.explore   3.return");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.println("Enter username");
                String username = scanner.next();

                boolean isFollowing = false;
                for (int i = 0; i < user.getPage().followings.size(); i++) {
                    if (user.getPage().followings.get(i).getUsername().equals(username)) {
                        isFollowing = true;
                        break;
                    }
                }
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getUsername().equals(username)) {
                        System.out.println(UserManager.users.get(i).getPage().toString());
                        if (UserManager.users.get(i).getPage().isPrivate() && !isFollowing) {
                            System.out.println("this page is private\n1.send request 2.massage 3.return");
                            int choice02 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice02) {
                                case 1:
                                    PageManager.sendFollowRequest(user, UserManager.users.get(i));
                                    System.out.println("Requested successfully");
                                    mainPanel(user);
                                    break;
                                case 2:
                                    startChat(user, UserManager.users.get(i));
                                    break;
                                case 3:
                                    mainPanel(user);
                            }
                        } else {
                            System.out.println("1.follow 2.massage 3.Posts 4.return");
                            int choice01 = scanner.nextInt();
                            switch (choice01) {
                                case 1:
                                    PageManager.addFollowing(user, UserManager.users.get(i));
                                    System.out.println("Followed successfully");
                                    explore_search(user);
                                    break;
                                case 2:
                                    System.out.println("reached04");
                                    startChat(user, UserManager.users.get(i));
                                    System.out.println("reached05");
                                    break;
                                case 3:
                                    showAllPosts(user, UserManager.users.get(i));
                                    break;
                                case 4:
                                    mainPanel(user);
                                    break;
                            }
                        }
                    }
                }
                System.out.println("User not found");
                System.out.println("1. redo 2.return");

                int choice01 = scanner.nextInt();
                scanner.nextLine();
                switch (choice01) {
                    case 1 -> explore_search(user);
                    case 2 -> mainPanel(user);
                }
            }
            case 2 -> {
                Explore(user);
            }
            case 3 -> mainPanel(user);
        }

    }
    public static void createNewPost(User user) {
        System.out.println("Enter content ");
        String content = scanner.nextLine();
        createPost(user, content);
        System.out.println("done");
        mainPanel(user);
    }
    public static void showAllPosts(User viewer, User user) {
        System.out.println(user.getUsername() + "`s posts");
        for (int i = 0; i < user.getPage().posts.size(); i++) {
            System.out.println((i + 1) + ". " + user.getPage().posts.get(i).getContent() + "\n----------");
        }
        System.out.println("Choose : user i or 0.return  ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            mainPanel(user);
        } else {
            int choose = choice - 1;
            showPost(viewer, user, choose);
        }
    }
    public static void showPost(User viewer, User user, int userChoice) {
        System.out.println(user.getUsername()
                + "\nContent : \n" + user.getPage().posts.get(userChoice).getContent() + "\nLikes : " + user.getPage().posts.get(userChoice).getLikes() +
                "\tDisLikes : " + user.getPage().posts.get(userChoice).getDisLikes());
        System.out.println("1.show comments 2.Like 3.DisLike 4.comment 5.return");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                if (!user.getPage().posts.get(userChoice).comments.isEmpty()) {
                    for (Comment comment : user.getPage().posts.get(userChoice).comments) {
                        System.out.println("User : " + comment.getUsername() + "\nSays : " + comment.getComment() +
                                "\n-    -   -   -   -");
                    }
                } else System.out.println("No comments yet");
                showPost(viewer, user, userChoice);
                break;

            case 2:
                PostManager.likePost(user.getPage().posts.get(userChoice), viewer);
                showPost(viewer, user, userChoice);
                break;
            case 3:
                PostManager.disLikePost(user.getPage().posts.get(userChoice), viewer);
                showPost(viewer, user, userChoice);
                break;
            case 4:
                addComment(viewer, user, userChoice);
            case 5:
                whereToReturn(1,viewer,user);
//                showAllPosts(viewer, user);
        }
    }
    public static void whereToReturn(int i,User user,User user2)
    {
        if (i==1) showAllPosts(user,user2);
        else homePage(user);
    }

    public static void addComment(User commenter, User Poster, int postNumber) {
        System.out.println("Type your comment");
        String comment = scanner.nextLine();
        Poster.getPage().posts.get(postNumber).comments.add(new Comment(comment, commenter.getUsername()));
        showPost(commenter, Poster, postNumber);
    }

    public static void homePage(User user)  //choose to chat || scroll
    {
        System.out.println("1.massage   2.home page 3.return");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showChats(user);
                break;
            case 2:
                HomePage(user);
                break;
            case 3:
                mainPanel(user);
                break;
        }
        mainPanel(user);
    }

    public static void showChats(User user) // show all chats
    {
        if (user.getPage().chats.isEmpty()) {
            System.out.println("No chat yet ...");
            homePage(user);
        }
        for (int i = 0; i < user.getPage().chats.size(); i++)
            System.out.println((i + 1) + ". " + user.getPage().chats.get(i).toString());
        System.out.println("Choose chat or -1 for returning");
        int choice = scanner.nextInt();
        if (choice == -1) homePage(user);
        else
            privateChat(user, UserManager.findUser(user.getPage().chats.get(choice - 1).toString()));
    }

    public static void startChat(User user, User user2) {
        boolean chatExist = false;
        for (int i = 0; i < user.getPage().chats.size(); i++) {
            for (int j = 0; j < user2.getPage().chats.size(); j++) {
                if (user.getPage().chats.get(i).equals(user2.getPage().chats.get(j))) {
                    chatExist = true;
                    break;
                }
            }
        }
        if (!chatExist) {
            ArrayList<Massage> massages = new ArrayList<>();
            user.getPage().chats.add(new Chat(user2, massages));
            user2.getPage().chats.add(new Chat(user, massages));
        }
        privateChat(user, user2);
    }

    // TODO: 7/1/2022 Done
    public static void privateChat(User user, User user2) // go to a pv
    {
        System.out.println(user2.getUsername() + "\n-    -   -   -   -");
        for (int i = 0; i < user.getPage().chats.size(); i++) {
            if (user.getPage().chats.get(i).userDirect.equals(user2)) {
                for (int j = 0; j < user.getPage().chats.get(j).massages.size() -1; j++)
                    System.out.println(user.getPage().chats.get(i).massages.get(j).toString());

                System.out.println("send your massage ... or Enter EXIT");
                scanner.nextLine();
                String massage = scanner.nextLine();
                if (massage.equals("EXIT"))
                    showChats(user);
                else {
                    user.getPage().chats.get(i).massages.add(new Massage(massage, user.getUsername()));
                    privateChat(user, user2);
                }
            }
        }

    }

    public static void HomePage(User user) {
        for (int i = 0; i < user.getPage().followings.size(); i++) {
            for (int j = 0; j < user.getPage().followings.get(i).getPage().posts.size(); j++) {
                if (PageManager.CheckSeenPosts(user.getPage(), user.getPage().followings.get(i).getPage().posts.get(j))) {
                    user.getPage().SeenPosts.add(user.getPage().followings.get(i).getPage().posts.get(j));


                    System.out.println( "\nContent : \n" + user.getPage().followings.get(i).getPage().posts.get(j).getContent() + "\nLikes : " +
                            user.getPage().followings.get(i).getPage().posts.get(j).getLikes() +
                            "\tDisLikes : " + user.getPage().followings.get(i).getPage().posts.get(j).getDisLikes());
                    System.out.println("1.show comments 2.Like 3.DisLike 4.comment 5.return");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            if (!user.getPage().followings.get(i).getPage().posts.get(j).comments.isEmpty()) {
                                for (Comment comment : user.getPage().followings.get(i).getPage().posts.get(j).comments) {
                                    System.out.println("User : " + comment.getUsername() + "\nSays : " + comment.getComment() +
                                            "\n-    -   -   -   -");
                                }
                            } else System.out.println("No comments yet");
                            showPost(user, user.getPage().followings.get(i), j);
                            break;

                        case 2:
                            PostManager.likePost(user.getPage().followings.get(i).getPage().posts.get(j), user);
                            showPost(user, user.getPage().followings.get(i), j);
                            break;
                        case 3:
                            PostManager.disLikePost(user.getPage().followings.get(i).getPage().posts.get(j), user);
                            showPost(user, user.getPage().followings.get(i), j);
                            break;
                        case 4:
                            addComment(user, user.getPage().followings.get(i), j);
                        case 5:
                            whereToReturn(2,user,null);
                    }
                }
            }
        }
    }

    public static void Explore(User user) {
        for (int i = 0; i < UserManager.Explore().size(); i++) {
            System.out.println(UserManager.Explore().get(i));
            System.out.println("-   -   -   -   -");
        }
        System.out.println("Choose or enter -1 to return");
        int choice = scanner.nextInt();
        if (choice == -1) explore_search(user);
        else
        {
            System.out.println(UserManager.Explore().get(choice-1).toString());
            System.out.println("1.show comments 2.Like 3.DisLike 4.comment 5.return");
            int choice01 = scanner.nextInt();
            scanner.nextLine();
            switch (choice01) {
                case 5:explore_search(user);
            }
        }
    }

    public static void ShowProfile(User user) {
        System.out.println(user.getUsername() + user.getPage().toString());
    }
    public static void showFollowers(User user) {
        for (int i = 0; i < user.getPage().followers.size(); i++) {
            System.out.println((i + 1) + ". " + user.getPage().followers.get(i).getUsername());
            System.out.println("-   -   -   -   -");
        }
        System.out.println("Choose person or -1 to return");
        int choice = scanner.nextInt();
        if (choice == -1) {
            profile(user);
        } else {
            String username = user.getPage().followers.get(choice - 1).getUsername();

            boolean isFollowing = false;
            for (int i = 0; i < user.getPage().followings.size(); i++) {
                if (user.getPage().followings.get(i).equals(user)) {
                    isFollowing = true;
                    break;
                }
            }
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)) {
                    if (UserManager.users.get(i).getPage().isPrivate() && !isFollowing) {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("this page is private\n1.send request 2.massage 3.return");
                        int choice02 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice02) {
                            case 1:
                                PageManager.sendFollowRequest(user, UserManager.users.get(i));
                                System.out.println("Requested successfully");
                                mainPanel(user);
                                break;
                            case 2:
                                privateChat(user, UserManager.users.get(i));
                                break;
                            case 3:
                                mainPanel(user);
                        }
                    } else {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("1.follow 2.massage 3.Posts 4.return");
                        int choice01 = scanner.nextInt();
                        switch (choice01) {
                            case 1:
                                PageManager.addFollowing(user, UserManager.users.get(i));
                                System.out.println("Followed successfully");
                                explore_search(user);
                                break;
                            case 2:
                                System.out.println("reached04");
                                privateChat(user, UserManager.users.get(i));
                                System.out.println("reached05");
                                break;
                            case 3:
                                showAllPosts(user, UserManager.users.get(i));
                                break;
                            case 4:
                                mainPanel(user);
                                break;
                        }
                    }
                }
            }
        }
    }

    public static void showFollowings(User user) {
        for (int i = 0; i < user.getPage().followings.size(); i++) {
            System.out.println((i + 1) + ". " + user.getPage().followings.get(i).getUsername());
            System.out.println("-   -   -   -   -");
        }
        System.out.println("Choose person or -1 to return");
        int choice = scanner.nextInt();
        if (choice == -1) {
            profile(user);
        } else {
            String username = user.getPage().followings.get(choice - 1).getUsername();

            boolean isFollowed = false;
            for (int i = 0; i < user.getPage().followers.size(); i++) {
                if (user.getPage().followers.get(i).equals(user)) {
                    isFollowed = true;
                    break;
                }
            }
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)) {
                    if (UserManager.users.get(i).getPage().isPrivate() && !isFollowed) {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("this page is private\n1.send request 2.massage 3.return");
                        int choice02 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice02) {
                            case 1:
                                PageManager.sendFollowRequest(user, UserManager.users.get(i));
                                System.out.println("Requested successfully");
                                mainPanel(user);
                                break;
                            case 2:
                                privateChat(user, UserManager.users.get(i));
                                break;
                            case 3:
                                mainPanel(user);
                        }
                    } else {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("1.follow 2.massage 3.Posts 4.return");
                        int choice01 = scanner.nextInt();
                        switch (choice01) {
                            case 1:
                                PageManager.addFollowing(user, UserManager.users.get(i));
                                System.out.println("Followed successfully");
                                explore_search(user);
                                break;
                            case 2:
                                System.out.println("reached04");
                                privateChat(user, UserManager.users.get(i));
                                System.out.println("reached05");
                                break;
                            case 3:
                                showAllPosts(user, UserManager.users.get(i));
                                break;
                            case 4:
                                mainPanel(user);
                                break;
                        }
                    }
                }
            }
            System.out.println("User not found");
            System.out.println("1. redo 2.return");

            int choice01 = scanner.nextInt();
            scanner.nextLine();
            switch (choice01) {
                case 1 -> explore_search(user);
                case 2 -> mainPanel(user);
            }
        }
    }

    public static void SuggestPanel(User user) {
        PageManager.suggest(user);
        System.out.println("Suggested for you : ");
        for (int i = 0; i < 10; i++)
            if (user.getPage().suggestedPeople.size() > i)
                System.out.println((i+1)+". " + user.getPage().suggestedPeople.get(i).user.getUsername() + "\n-  -   -   -   -");
        System.out.println("Choose or enter -1 to return");
        int choice = scanner.nextInt();

        if (choice == -1)
            notificationsPanel(user);
        else {
            String username = user.getPage().suggestedPeople.get(choice-1).user.getUsername();

            boolean isFollowing = false;
            for (int i = 0; i < user.getPage().followings.size(); i++) {
                if (user.getPage().followings.get(i).equals(user)) {
                    isFollowing = true;
                    break;
                }
            }
            for (int i = 0; i < UserManager.users.size(); i++) {
                if (UserManager.users.get(i).getUsername().equals(username)) {
                    if (UserManager.users.get(i).getPage().isPrivate() && !isFollowing) {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("this page is private\n1.send request 2.massage 3.return");
                        int choice02 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice02) {
                            case 1:
                                PageManager.sendFollowRequest(user, UserManager.users.get(i));
                                System.out.println("Requested successfully");
                                mainPanel(user);
                                break;
                            case 2:
                                privateChat(user, UserManager.users.get(i));
                                break;
                            case 3:
                                mainPanel(user);
                        }
                    } else {
                        ShowProfile(UserManager.users.get(i));
                        System.out.println("1.follow 2.massage 3.Posts 4.return");
                        int choice01 = scanner.nextInt();
                        switch (choice01) {
                            case 1:
                                PageManager.addFollowing(user, UserManager.users.get(i));
                                System.out.println("Followed successfully");
                                explore_search(user);
                                break;
                            case 2:
                                System.out.println("reached04");
                                privateChat(user, UserManager.users.get(i));
                                System.out.println("reached05");
                                break;
                            case 3:
                                showAllPosts(user, UserManager.users.get(i));
                                break;
                            case 4:
                                mainPanel(user);
                                break;
                        }
                    }
                }
            }
        }
    }
}


//    public static void Suggestion(User user)
//    {
//        for (int i = 0; i < user.getPage().followings.size(); i++) {
//            for (int j = 0; j < user.getPage().followings.get(i).getPage().followings.size(); j++) {
//                boolean
//                if (user.getPage().followings.get(i).getPage().followings.get(j).equals(user.getPage().followings.get(i)))
//                {
//
//                }
//            }
//        }
//    }
//
//}
