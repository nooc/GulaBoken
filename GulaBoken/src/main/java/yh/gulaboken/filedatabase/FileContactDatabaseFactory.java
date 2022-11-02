package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

import java.io.File;

public class FileContactDatabaseFactory {

    /**
     * Return database instance.
     * @param dataFile Path to data file.
     * @return IContactDatabase
     */
    public static IContactDatabase create(File dataFile) {

        return new FileContactDatabase(dataFile);
    }
}
