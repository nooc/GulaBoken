package yh.gulaboken.filedatabase;

import yh.gulaboken.IContactDatabase;
import yh.gulaboken.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class FileContactDatabase implements IContactDatabase {
    private long idCounter = 0;
    private List<Contact> contactList;
    /**
     *
     */
    FileContactDatabase(String filePath) {
        //TODO: read contactList from filePath, else create empty list.

        this.contactList = new ArrayList<>();
    }

    /**
     * Create database contact.
     * @param newContact
     * @return Contact with new id
     */
    @Override
    public Contact create(Contact newContact) {
        idCounter += 1;
        newContact.setContactId(idCounter);
        contactList.add(newContact);
        return newContact;
    }

    /**
     * Read contact with id.
     * @param id Contact id
     * @return Contact or null
     */
    @Override
    public Contact read(long id) {
        for(var contact : contactList){
            if(contact.getContactId() == id){
                return contact;
            }
        }

        return null;
    }

    /**
     * Update existing contact.
     * @param contact
     * @return True if success, else false.
     */
    @Override
    public boolean update(Contact contact) {
        // find db contact
        var dBContact = read(contact.getContactId());
        if(dBContact == null) {
            // not found
            return false;
        }
        // found -> update
        dBContact.setName(contact.getName());
        dBContact.setSurname(contact.getSurname());
        dBContact.setAge(contact.getAge());
        dBContact.setTelephoneNumber(contact.getTelephoneNumber());
        var dbAddr = dBContact.getAddress();
        var addr = contact.getAddress();
        dbAddr.setStreet(addr.getStreet());
        dbAddr.setCity(addr.getCity());
        dbAddr.setZipCode(addr.getZipCode());
        return true;
    }

    /**
     * Delete contact from database.
     * @param id Contact id.
     * @return True if success, else false.
     */
    @Override
    public boolean delete(long id) {
        // find contact
        var contact = read (id);
        if(contact != null) {
            // found
            contactList.remove(contact);
            return true;
        }
        return false;
    }

    @Override
    public List<Contact> query(List<String> keywords) {
        ArrayList<Contact> found = new ArrayList<>();
        for(var contact : found) {
            for (var key : keywords) {
                //TODO: query
            }
        }
        return found;
    }
}
