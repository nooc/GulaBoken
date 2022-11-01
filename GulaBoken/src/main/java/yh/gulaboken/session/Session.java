package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;

public class Session implements ISession {
    /**
     * Default session user.
     */
    private static final String DEFAULT_USER = "guest";
    /**
     * Current user.
     */
    private IUser currentUser;

    /**
     * Constructor
     * Initialize with DEFAULT_USER.
     */
    Session() {
        setUser(null);
    }

    @Override
    public IUser getUser() {
        return currentUser;
    }

    @Override
    public void setUser(IUser user) {
        if(user == null) {
            currentUser = HardcodedUserDatabaseFactory.getSingleton().getUser(DEFAULT_USER);
        } else {
            currentUser = user;
        }
    }
}
