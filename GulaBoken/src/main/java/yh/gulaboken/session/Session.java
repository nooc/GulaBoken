package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;

/**
 * Session implementation.
 */
class Session implements ISession {

    /**
     * Current user.
     * Never null.
     */
    private IUser currentUser;

    /**
     * Default user.
     */
    private final IUser defaultUser;

    /**
     * Constructor
     * Initialize defaultUser.
     */
    Session(IUser defaultUser) {
        if(defaultUser == null) {
            throw new RuntimeException("Default user can not be null.");
        }
        this.currentUser = this.defaultUser = defaultUser;
    }

    /**
     * @see ISession#getUser()
     */
    @Override
    public IUser getUser() {
        return currentUser;
    }

    /**
     * @see ISession#setUser(IUser)
     */
    @Override
    public void setUser(IUser user) {
        // Set currentUser. If null, set to default user.
        if(user == null) {
            currentUser = defaultUser;
        } else {
            currentUser = user;
        }
    }
}
