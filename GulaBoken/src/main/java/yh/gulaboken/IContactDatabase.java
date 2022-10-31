package yh.gulaboken;

import yh.gulaboken.models.Contact;

/**
 *
 */
public interface IContactDatabase {
    /**
     *
     * @param newEntry
     * @return
     */
    Contact create(Contact newEntry);

    /**
     *
     * @param id
     * @return
     */
    Contact read(long id);

    /**
     *
     * @param entry
     * @return
     */
    boolean update(Contact entry);

    /**
     *
     * @param id
     * @return
     */
    boolean delete(Contact id);

}
