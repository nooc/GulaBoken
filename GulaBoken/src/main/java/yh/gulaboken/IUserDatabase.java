package yh.gulaboken;

import javax.validation.constraints.NotNull;

/**
 * Interface for user databases.
 */
public interface IUserDatabase {
    /**
     * @param username
     * @return IUser or null
     */
    IUser getUser(@NotNull String username);

    /**
     * Authenticate user with username and password and return the authenticated user.
     * If user doesn't exist or authentication fails, return null.
     *
     * @param username Username
     * @param password Password
     * @return IUser or null
     */
    IUser authenticate(@NotNull String username, String password);
}