package yh.gulaboken.filedatabase;

import yh.gulaboken.IContact;

import java.util.LinkedList;

/**
 * Implements IContact interface.
 */
class Contact implements IContact {
    /**
     * An unique contact id.
     */
    private long contactId;
    /** contact name */
    private String name;
    /** contact surname */
    private String surname;
    /** contact age */
    private String age;
    /** one or more comma separated phone numbers */
    private String phoneNumber;
    /** street name and number */
    private String street;
    /** city name */
    private String city;
    /** zip code number */
    private String zipCode;

    /**
     * Constructor
     * Initialize contact to empty state.
     */
    Contact() {
        this.contactId = 0;
        this.name = "";
        this.surname = "";
        this.age = "";
        this.phoneNumber = "";
        this.street = "";
        this.city = "";
        this.zipCode = "";
    }

    /**
     * Make sure value is non-null.
     * @param value Input value.
     * @return Input value if not null, else empty string.
     */
    protected String nonNull(String value) {
        return value == null ? "" : value;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = nonNull(name);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = nonNull(surname);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = nonNull(age);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = nonNull(phoneNumber);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = nonNull(street);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = nonNull(city);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = nonNull(zipCode);
    }

    /**
     * Join address entries into a single comma separated line.
     * @return
     */
    public String getAddressLine() {
        var addressItems = new LinkedList<String>();
        if(!street.isEmpty())  { addressItems.add(street); }
        if(!zipCode.isEmpty())  { addressItems.add(zipCode); }
        if(!city.isEmpty())  { addressItems.add(city); }
        return String.join(", ", addressItems);
    }
}
