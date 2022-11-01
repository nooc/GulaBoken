package yh.gulaboken;

public interface ISession {

    /**
     * Get current user.
     * @return IUser
     */
    IUser getUser();

    /**
     * Set current user.
     */
    void setUser(IUser user);
}
