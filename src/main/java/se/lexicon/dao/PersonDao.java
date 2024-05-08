package se.lexicon.dao;

import se.lexicon.model.Person;

import java.util.Collection;

public interface PersonDao {

    Person create(Person person);

    Person findById(int id);
    Collection<Person> findAll();
    Collection<Person> findByFirstName(String firstName);
    Collection<Person> findByLastName(String lastName);// <T
    Person update(Person person);
    boolean delete(int id); // >
}
