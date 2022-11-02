package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;

public class SessionFactory {

    /**
     * Get the session singleton and create it if needed.
     * @return ISession
     */
    public static ISession create(IUser defaultUser) {
        return new Session(defaultUser);
    }
}
