package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;
import yh.gulaboken.models.Contact;

/**
 *
 */
class FileContactDatabase implements IContactDatabase {
    private long idCounter = 0;
    private List <contact> contactList;
    /**
     *
     */
    FileContactDatabase() {
        this.contactList = new Arraylist<>();
    }

    @Override
    public Contact create(Contact newEntry) {
        idCounter += 1;
        newEntry.setContactId(idCounter);
        contactList.add(newEntry);
        return newEntry;
    }

    @Override
    public Contact read(long id) {
        for(var contact : contactList){
            if(contact.getContactId() == id){
                return contact;
            }
        }

        return null;
    }

    @Override
    public boolean update(Contact entry) {
        var contact = read(entry.getContactId());
        if(contact == null){
            return false;
        }
        contact.setName(entry.getName());
        contact.setSurName(entry.getSurname());
        contact.setAge(entry.getAge());
        contact.setTelephoneNumber(entry.getTelephoneNumber());
        contact.setAddress(entry.getAddress());

        return true;
    }

    @Override
    public boolean delete(Contact id) {
        var contact = read (id);
        if(contact != null){
            contactList.remove(contact);
            return true;
        }
        return false;
    }
}
