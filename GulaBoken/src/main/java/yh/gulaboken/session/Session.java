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
        this.currentUser = HardcodedUserDatabaseFactory.getSingleton().getUser(DEFAULT_USER);
    }

    @Override
    public IUser getUser() {
        return currentUser;
    }

    @Override
    public void setUser(IUser user) {
        currentUser = user;
    }
}
