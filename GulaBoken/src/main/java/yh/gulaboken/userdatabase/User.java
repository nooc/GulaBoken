package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;

import javax.validation.constraints.NotNull;
import java.security.MessageDigest;

class User implements IUser {
    private final String username;
    private final boolean isAdmin;
    private final String passwordHash;

    /**
     * Constructor
     * Password is stored as its hash sum.
     *
     * @param username
     * @param password
     * @param isAdmin
     */
    User(@NotNull String username, String password, boolean isAdmin) {
        this.username = username;
        this.passwordHash = calculatePasswordHash(password);
        this.isAdmin = isAdmin;
    }

    /**
     * Calculate MD5 hash for the clear-text password.
     *
     * @param password Clear-text password
     * @return MD5 hash for password or null
     */
    static String calculatePasswordHash(String password) {
        if (password == null) {
            password = "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            return new String(digest.digest());
        } catch (Exception ex) {
        }
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }
}
