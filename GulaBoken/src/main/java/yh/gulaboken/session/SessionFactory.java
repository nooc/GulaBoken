package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;

/**
 * Factory for SessionFactory.
 */
public class SessionFactory {

    /**
     * Create an Session instance.
     * @param defaultUser Default session user
     * @return ISession instance
     */
    public static ISession create(IUser defaultUser) {
        return new Session(defaultUser);
    }
}
