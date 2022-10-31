package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

public class FileContactDatabaseFactory {

    private final String DEFAULT_DATABASE_FILE = "contacts.json";

    private static IContactDatabase database = null;

    public IContactDatabase getSingleton() {
        if(database == null) {
            database = new FileContactDatabase();
        }
        return database;
    }
}
