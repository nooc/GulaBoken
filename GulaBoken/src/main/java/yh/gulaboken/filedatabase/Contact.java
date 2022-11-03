package yh.gulaboken.filedatabase;

import yh.gulaboken.IContact;
import yh.gulaboken.models.BaseModel;

import java.util.LinkedList;

class Contact implements IContact {
    private long contactId;
    private String name;
    private String surname;
    private String age;
    private String telephoneNumber;
    private String street;
    private String city;
    private String zipCode;

    Contact() {
        this.contactId = 0;
        this.name = "";
        this.surname = "";
        this.age = "";
        this.telephoneNumber = "";
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {

        this.telephoneNumber = nonNull(telephoneNumber);
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
