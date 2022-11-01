package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;

import java.security.MessageDigest;

public class User implements IUser {
    private final String username;
    private String password;
    private final boolean isAdmin;

    /**
     * Constructor
     * Password is stored as its hash sum.
     * @param username
     * @param password
     * @param isAdmin
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = calculatePasswordHash(password);
        this.isAdmin = isAdmin;
    }

    /**
     * Get username.
     * @return String
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get password hash.
     * @return String
     */
    @Override
    public String getPasswordHash() {
        return password;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }


    static String calculatePasswordHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            return new String(digest.digest());
        } catch (Exception ex) {}
        return password;
    }
}
