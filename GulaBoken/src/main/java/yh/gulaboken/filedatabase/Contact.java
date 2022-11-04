package yh.gulaboken.filedatabase;

import yh.gulaboken.IContact;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Implements IContact interface.
 */
class Contact implements IContact {
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
    private static final String COMMA_DELIMITER = ", ";

    /**
     * A unique contact id.
     */
    private final long contactId;
    /**
     * contact name
     */
    private String name;
    /**
     * contact surname
     */
    private String surname;
    /**
     * contact age
     */
    private String age;
    /**
     * one or more comma separated phone numbers
     */
    private String phoneNumber;
    /**
     * street name and number
     */
    private String street;
    /**
     * city name
     */
    private String city;
    /**
     * zip code number
     */
    private String zipCode;

    /**
     * Constructor
     * Initialize contact to empty state.
     *
     * @param id New id
     */
    Contact(long id) {
        this.contactId = id;
        this.name = EMPTY_STRING;
        this.surname = EMPTY_STRING;
        this.age = EMPTY_STRING;
        this.phoneNumber = EMPTY_STRING;
        this.street = EMPTY_STRING;
        this.city = EMPTY_STRING;
        this.zipCode = EMPTY_STRING;
    }

    /**
     * Make sure value is non-null.
     *
     * @param value Input value.
     * @return Input value if not null, else empty string.
     */
    @NotNull
    protected String nonNull(String value) {
        return value == null ? EMPTY_STRING : value;
    }

    @Override
    public long getContactId() {
        return contactId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = nonNull(name);
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = nonNull(surname);
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public void setAge(String age) {
        this.age = nonNull(age);
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = nonNull(phoneNumber);
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = nonNull(street);
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = nonNull(city);
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(String zipCode) {
        this.zipCode = nonNull(zipCode);
    }

    @Override
    public String getAddressLine() {
        // list of items to join with ", "
        var addressItems = new ArrayList<String>();
        if (!street.isEmpty()) {
            addressItems.add(street);
        }
        //
        if (!zipCode.isEmpty()) {
            if (!city.isEmpty()) {
                addressItems.add(String.join(SPACE, zipCode, city));
            } else {
                addressItems.add(zipCode);
            }
        } else if (!city.isEmpty()) {
            addressItems.add(city);
        }

        return String.join(COMMA_DELIMITER, addressItems);
    }
}
