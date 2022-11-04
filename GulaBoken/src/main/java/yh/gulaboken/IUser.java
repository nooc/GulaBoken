package yh.gulaboken;

import javax.validation.constraints.NotNull;

public interface IUser {
    /**
     * Get username.
     *
     * @return String
     */
    @NotNull
    String getUsername();

    /**
     * Get hashed password.
     *
     * @return Hash
     */
    @NotNull
    String getPasswordHash();

    /**
     * Get admin status.
     *
     * @return boolean
     */
    boolean isAdmin();
}
