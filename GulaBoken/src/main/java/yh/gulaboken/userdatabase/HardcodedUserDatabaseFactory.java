package yh.gulaboken.userdatabase;

import yh.gulaboken.IUserDatabase;

public class HardcodedUserDatabaseFactory {

    private static HardcodedUserDatabase userDatabaseSingleton = null;

    public IUserDatabase getInstance() {
        if(userDatabaseSingleton == null) {
            userDatabaseSingleton = new HardcodedUserDatabase();
        }
        return userDatabaseSingleton;
    }
}
