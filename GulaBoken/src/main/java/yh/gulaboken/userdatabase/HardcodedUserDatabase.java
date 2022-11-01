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
    }

    /**
     * Authenticate user with username and password and return the authenticated user.
     * If user doesn't exist or authentication fails, return null.
     * @param username
     * @param password
     * @return IUser
     */
    @Override
    public IUser authenticate(String username, String password) {
    }
}
