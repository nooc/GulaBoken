package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;

import javax.validation.constraints.NotNull;
import java.io.File;

public class FileContactDatabaseFactory {

    /**
     * Create instance of an IContactDatabase.
     *
     * @param dataFile Path to data file.
     * @return IContactDatabase instance
     */
    public static IContactDatabase create(@NotNull File dataFile) {

        return new FileContactDatabase(dataFile);
    }
}
