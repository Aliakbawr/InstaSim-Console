package All.Post;

import All.Comment;
import All.User.User;
import All.User.UserManager;

import java.util.ArrayList;

public class PostManager {

    public static ArrayList<Post> All_Posts = new ArrayList<>();
    public static void createPost(User user,String content)
    {
        for (int i = 0; i < UserManager.users.size(); i++) {
            if (UserManager.users.get(i).equals(user))
            {
                Post post = new Post(content);
                user.getPage().setPostNumber(user.getPage().getPostNumber()+1);
                user.getPage().posts.add(post);
                All_Posts.add(post);
            }
        }
    }
    public static void likePost(Post post, User userWhoLiked)    // like a post
    {
        for (int i = 0; i < post.likedBy.size() ; i++) if (post.likedBy.get(i) == userWhoLiked) {
            post.likedBy.remove(userWhoLiked);
            post.setLikes(post.getLikes()-1);
            return;
        }
        for (int i = 0; i < post.dislikedBy.size() ; i++) if (post.dislikedBy.get(i) == userWhoLiked) {
            post.dislikedBy.remove(userWhoLiked);
            post.setDisLikes(post.getDisLikes()-1);
        }
        post.likedBy.add(userWhoLiked);
        post.setLikes(post.getLikes()+1);
    }
    public static void disLikePost(Post post, User userWhoDisLiked)  // dislike a post
    {
        for (int i = 0; i < post.dislikedBy.size() ; i++) if (post.dislikedBy.get(i) == userWhoDisLiked) {
            post.dislikedBy.remove(userWhoDisLiked);
            post.setDisLikes(post.getDisLikes()-1);
            return;
        }
        for (int i = 0; i < post.likedBy.size() ; i++) if (post.likedBy.get(i) == userWhoDisLiked) {
            post.likedBy.remove(userWhoDisLiked);
            post.setLikes(post.getLikes()-1);
        }

        post.dislikedBy.add(userWhoDisLiked);
        post.setDisLikes(post.getDisLikes()+1);
    }

    public static void editPost(Post post,String content)   // edit post
    {
        post.setContent(content);
    }

    public void show_homePage(User user,ArrayList<Post> posts)
    {
        for (int i = 0; i < user.getPage().followers.size(); i++) {

        }
    }

}
