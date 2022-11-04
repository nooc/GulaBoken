package yh.gulaboken.session;

import yh.gulaboken.ISession;
import yh.gulaboken.IUser;

import javax.validation.constraints.NotNull;

/**
 * Session implementation.
 */
class Session implements ISession {

    /**
     * Default user.
     */
    private final IUser defaultUser;
    /**
     * Current user.
     * Never null.
     */
    private IUser currentUser;

    /**
     * Constructor
     * Initialize defaultUser.
     */
    Session(@NotNull IUser defaultUser) {
        this.currentUser = this.defaultUser = defaultUser;
    }

    @Override
    public IUser getUser() {
        return currentUser;
    }

    @Override
    public void setUser(IUser user) {
        // Set currentUser. If null, set to default user.
        if (user == null) {
            currentUser = defaultUser;
        } else {
            currentUser = user;
        }
    }
}
