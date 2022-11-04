package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;

import javax.validation.constraints.NotNull;

/**
 * Factory for SessionFactory.
 */
public class SessionFactory {

    /**
     * Create instance of an ISession.
     *
     * @param defaultUser Default session user
     * @return ISession instance
     */
    public static ISession create(@NotNull IUser defaultUser) {
        return new Session(defaultUser);
    }
}
