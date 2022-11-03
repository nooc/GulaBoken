package yh.gulaboken;

public interface IUserDatabase {
    /**
     *
     * @param username
     * @return User
     */
    IUser getUser(String username);

    /**
     * Authenticate user with username and password and return the authenticated user.
     * If user doesn't exist or authentication fails, return null.
     * @param username
     * @param password
     * @return IUser
     */
    IUser authenticate(String username, String password);
}