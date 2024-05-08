package se.lexicon.daoimpl;

import se.lexicon.dao.PersonDao;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return List.of();
    }

    @Override
    public Collection<Person> findByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public Collection<Person> findByLastName(String lastName) {
        return List.of();
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
