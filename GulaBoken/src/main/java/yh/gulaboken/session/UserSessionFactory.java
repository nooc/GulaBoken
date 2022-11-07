package yh.gulaboken.session;

import yh.gulaboken.IUser;
import yh.gulaboken.IUserSession;

import javax.validation.constraints.NotNull;

/**
 * Factory for UserSession.
 */
public class UserSessionFactory {

    /**
     * Create instance of an IUserSession.
     *
     * @param defaultUser Default session user
     * @return IUserSession instance
     */
    public static IUserSession create(@NotNull IUser defaultUser) {
        return new UserSession(defaultUser);
    }
}
