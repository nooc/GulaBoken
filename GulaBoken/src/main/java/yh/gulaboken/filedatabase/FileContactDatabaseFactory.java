package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

public class FileContactDatabaseFactory {

    private final String DEFAULT_DATABASE_FILE = "contacts.json";

    public static IContactDatabase create() {
        return new FileContactDatabase();
    }
}
