package yh.gulaboken;

public interface IContactDatabase {
    ContactEntry create(ContactEntry newEntry);
    ContactEntry read(long id);
    boolean update(ContactEntry entry);
    boolean delete(ContactEntry id);

}
