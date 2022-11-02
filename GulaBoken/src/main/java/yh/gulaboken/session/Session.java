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
     * Default user
     */
    private final IUser defaultUser;

    /**
     * Constructor
     * Initialize with DEFAULT_USER.
     */
    Session(IUser defaultUser) {
        this.defaultUser = defaultUser;
        setUser(null);
    }

    @Override
    public IUser getUser() {
        return currentUser;
    }

    @Override
    public void setUser(IUser user) {
        if(user == null) {
            currentUser = defaultUser;
        } else {
            currentUser = user;
        }
    }
}
