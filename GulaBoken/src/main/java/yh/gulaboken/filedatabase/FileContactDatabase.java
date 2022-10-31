package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;
import yh.gulaboken.models.Contact;

/**
 *
 */
class FileContactDatabase implements IContactDatabase {

    /**
     *
     */
    FileContactDatabase() {

    }

    @Override
    public Contact create(Contact newEntry) {
        return null;
    }

    @Override
    public Contact read(long id) {
        return null;
    }

    @Override
    public boolean update(Contact entry) {
        return false;
    }

    @Override
    public boolean delete(Contact id) {
        return false;
    }
}
