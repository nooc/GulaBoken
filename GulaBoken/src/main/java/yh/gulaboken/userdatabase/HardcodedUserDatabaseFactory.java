package yh.gulaboken.userdatabase;

import yh.gulaboken.IUserDatabase;

/**
 * Factory for HardcodedUserDatabase
 */
public class HardcodedUserDatabaseFactory {

    /**
     * Create instance of an IUserDatabase.
     *
     * @return IUserDatabase instance
     */
    public static IUserDatabase create() {
        return new HardcodedUserDatabase();
    }
}
