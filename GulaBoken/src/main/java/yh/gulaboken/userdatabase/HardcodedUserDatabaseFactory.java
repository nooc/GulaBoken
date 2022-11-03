package yh.gulaboken.userdatabase;

import yh.gulaboken.IUserDatabase;

/**
 * Factory for HardcodedUserDatabase
 */
public class HardcodedUserDatabaseFactory {

    /**
     * Return instance of an HardcodedUserDatabase.
     * @return IUserDatabase
     */
    public static IUserDatabase create() {
         return new HardcodedUserDatabase();
    }
}
