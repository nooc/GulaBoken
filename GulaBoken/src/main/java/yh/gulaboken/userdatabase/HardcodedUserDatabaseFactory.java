package yh.gulaboken.userdatabase;

import yh.gulaboken.IUserDatabase;

public class HardcodedUserDatabaseFactory {

    /**
     *
     * @return
     */
    public static IUserDatabase create() {
         return new HardcodedUserDatabase();
    }
}
