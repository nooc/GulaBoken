package yh.gulaboken.menus;

import yh.gulaboken.IAppContext;
import yh.gulaboken.IMenu;

public abstract class BaseMenu implements IMenu {
    protected static final int SPLIT_IN_TWO = 2;
    protected static final int INDEX_0 = 0;
    protected static final int INDEX_1 = 1;
    protected static final int INDEX_2 = 2;
    protected final IAppContext context;

    /**
     * Constructor
     *
     * @param context Application context
     */
    protected BaseMenu(IAppContext context) {
        this.context = context;
    }

    public abstract void show();

    /**
     * Try logging in user with credentials.
     *
     * @param username Username
     * @param password Password
     * @return True if success, else false
     */
    protected boolean loginUser(String username, String password) {
        // find authenticated user
        var user = context.getUserDatabase().authenticate(username, password);
        if (user == null) {
            System.out.println("Login failed.");
            return false;
        }
        // set session user
        context.getSession().setUser(user);
        System.out.format("User %s logged in.\n", user.getUsername());
        return true;
    }
}
