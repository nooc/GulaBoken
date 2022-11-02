package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

import java.io.File;

public class FileContactDatabaseFactory {

    /**
     * Return database instance.
     * @return IContactDatabase
     */
    public static IContactDatabase create(File dataFile) {

        return new FileContactDatabase(dataFile);
    }
}
