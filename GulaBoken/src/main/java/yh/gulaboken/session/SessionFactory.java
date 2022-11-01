package yh.gulaboken.session;

import yh.gulaboken.ISession;

public class SessionFactory {

    /**
     * Session singleton.
     */
    private static ISession singleton = null;

    /**
     * Get the session singleton and create it if needed.
     * @return ISession
     */
    public static ISession getSingleton() {
        if(singleton==null) {
            singleton = new Session();
        }
        return singleton;
    }
}
