package se.lexicon;


import se.lexicon.dao.PersonDao;
import se.lexicon.dao.daoimpl.PersonDaoImpl;
import se.lexicon.model.Person;

import java.sql.SQLException;

public class App {


    public static void main( String[] args ) {
        // Create a Person object
        Person person = new Person("John", "Doe");

        // Create a PersonDao object
        PersonDao personDao = new PersonDaoImpl();

        try {
            // Add the person to the database
            Person createdPerson = personDao.create(person);
            System.out.println("Created Person: " + createdPerson);
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Or log the exception
        }
    }




    }

