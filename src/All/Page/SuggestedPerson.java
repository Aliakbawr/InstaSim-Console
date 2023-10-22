package All.Page;

import All.User.User;

public class SuggestedPerson {
    public User user;
    public int chance;

    public SuggestedPerson(User user, int chance) {
        this.user = user;
        this.chance = chance;
    }
}
