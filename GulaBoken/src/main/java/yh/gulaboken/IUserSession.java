package yh.gulaboken;

import javax.validation.constraints.NotNull;

public interface IUserSession {

    /**
     * Get current user.
     *
     * @return IUser
     */
    @NotNull
    IUser getUser();

    /**
     * Set current user.
     * If user is null, set user to default user.
     */
    void setUser(IUser user);
}
