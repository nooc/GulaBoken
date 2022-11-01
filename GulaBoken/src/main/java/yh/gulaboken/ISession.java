package yh.gulaboken;

public interface ISession {

    /**
     * Get current user.
     * @return IUser
     */
    IUser getUser();

    /**
     * Set current user.
     * If user is null, set user to default.
     */
    void setUser(IUser user);
}
