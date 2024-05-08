package se.lexicon.model;

public class Person {
    private int id;
    private String firstName;
    private String lastName;

    //Default constructor
    public Person() {

    }
    //Overloaded constructor
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //Getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    //toString


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
