package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;
import yh.gulaboken.IUserDatabase;

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
        this.users = new IUser[] {
                new User("guest", "guest", false),
                new User("admin", "secret", true)
        };
    }

    /**
     * Get user with username.
     * @param username
     * @return IUser
     */
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
        var user = getUser(username);
        if(user!=null && user.getPasswordHash().equals(User.calculatePasswordHash(password))) {
            return user;
        }
        return null;
    }
}
