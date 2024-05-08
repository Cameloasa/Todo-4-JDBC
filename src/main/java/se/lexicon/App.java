package se.lexicon;


import se.lexicon.dao.PersonDao;
import se.lexicon.dao.daoimpl.PersonDaoImpl;
import se.lexicon.model.Person;

public class App
{
    public static void main( String[] args )
    {
        // Create a PersonDao object
        PersonDao personDao = new PersonDaoImpl();

        // Create a Person object
        Person person = new Person("John", "Doe");

        // Add Person to database
        Person createdPerson = personDao.create(person);
        System.out.println("Created Person: " + createdPerson);



    }
}
