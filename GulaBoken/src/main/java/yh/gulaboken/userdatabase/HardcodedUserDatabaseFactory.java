package yh.gulaboken.userdatabase;

import yh.gulaboken.IUserDatabase;

public class HardcodedUserDatabaseFactory {

    /**
     * HardcodedUserDatabase singleton.
     */
    private static HardcodedUserDatabase userDatabaseSingleton = null;

    /**
     *
     * @return
     */
    public static IUserDatabase getSingleton() {
        if(userDatabaseSingleton == null) {
            userDatabaseSingleton = new HardcodedUserDatabase();
        }
        return userDatabaseSingleton;
    }
}
