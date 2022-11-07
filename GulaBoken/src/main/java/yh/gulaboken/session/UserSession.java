package yh.gulaboken.session;

import yh.gulaboken.IUser;
import yh.gulaboken.IUserSession;

import javax.validation.constraints.NotNull;

/**
 * Session implementation.
 */
class UserSession implements IUserSession {

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
    UserSession(@NotNull IUser defaultUser) {
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
