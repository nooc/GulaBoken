package yh.gulaboken;

import yh.gulaboken.models.Contact;

import java.util.Collection;

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
    boolean delete(long id);

    /**
     * Free text query contacts. Return relevant matches.
     * @param keywords query keywords
     * @return Collection
     */
    Collection<Contact> query(Collection<String> keywords);
}
