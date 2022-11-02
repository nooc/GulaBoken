package yh.gulaboken.models;

public class Contact extends BaseModel{
    private long contactId;
    private String name;
    private String surname;
    private String age;
    private String telephoneNumber;
    private Address address;

    public Contact() {
        this.contactId = 0;
        this.name = "";
        this.surname = "";
        this.age = "";
        this.telephoneNumber = "";
        this.address = new Address();
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

    public Address getAddress() {
        return address;
    }
}
