package yh.gulaboken.session;

import yh.gulaboken.ISession;

public class SessionFactory {

    /**
     * Get the session singleton and create it if needed.
     * @return ISession
     */
    public static ISession create() {
        return new Session();
    }
}
