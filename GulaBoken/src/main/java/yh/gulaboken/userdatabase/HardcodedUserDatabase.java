package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;
import yh.gulaboken.IUserDatabase;

/**
 * A hard coded user database.
 */
class HardcodedUserDatabase implements IUserDatabase {

    /**
     * List of hard coded users.
     */
    private final IUser[] users;

    /**
     * Constructor
     * Create list of hard coded users.
     */
    HardcodedUserDatabase() {
        // Some hardcoded users
        this.users = new IUser[] {
                new User("guest", "guest", false),
                new User("admin", "secret", true)
        };
    }

    @Override
    public IUser getUser(String username) {
        for (var user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public IUser authenticate(String username, String password) {
        // find user by username
        var user = getUser(username);
        // get hash for password and compare it to user's hash
        if(user!=null && user.getPasswordHash().equals(User.calculatePasswordHash(password))) {
            return user;
        }
        return null;
    }
}
