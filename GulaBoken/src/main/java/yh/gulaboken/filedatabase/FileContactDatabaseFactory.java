package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

public class FileContactDatabaseFactory {

    private final String DEFAULT_DATABASE_FILE = "contacts.json";

    private static IContactDatabase databaseSingleton = null;

    public IContactDatabase getSingleton() {
        if(databaseSingleton == null) {
            databaseSingleton = new FileContactDatabase();
        }
        return databaseSingleton;
    }
}
