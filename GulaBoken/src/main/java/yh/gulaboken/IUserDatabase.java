package yh.gulaboken;

public interface IUserDatabase {
    /**
     *
     * @param username
     * @return User
     */
    IUser getUser(String username);

    /**
     *
     * @param username
     * @param password
     * @return Authenticated user
     */
    IUser authenticate(String username, String password);
}