package yh.gulaboken;

public interface IUser {
    /**
     * Get username.
     * @return String
     */
    String getUsername();

    /**
     * Get hashed password.
     * @return String
     */
    String getPasswordHash();

    /**
     * Get admin status.
     * @return boolean
     */
    boolean isAdmin();
}
