package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;

public class User implements IUser {
    private final String username;
    private final String password;

    /**
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getName() {
        return null;
    }
}
