package se.lexicon.dao.daoimpl;

import se.lexicon.dao.PersonDao;
import se.lexicon.db.MySQL;
import se.lexicon.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private Connection connection;
    //Extract Person from ResultSet
    private Person extractPersonFromResultSet(ResultSet resultSet) throws SQLException {
        // get - id, firstName, lastName - return Person
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        return new Person(id, firstName, lastName);
    }


    // Constructor
    public PersonDaoImpl() {
        this.connection = MySQL.getConnection();
    }

    @Override
    public Person create(Person person) {
        // SQL - INSERT INTO Person (firstName, lastName) VALUES (?, ?)
        String query = "INSERT INTO Person (firstName, lastName) VALUES (?, ?)";
        // Prepare statement
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Set parameters
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            // Execute
            int affectedRows = statement.executeUpdate();
            // Check
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    @Override
    public Person findById(int id) {
        // SQL - SELECT * FROM Person WHERE id = ?
        String query = "SELECT * FROM Person WHERE id = ?";
        try {
            // Prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            // Set parameters
            statement.setInt(1, id);
            // Execute
            ResultSet resultSet = statement.executeQuery();
            // Check
            if (resultSet.next()) {
                return extractPersonFromResultSet(resultSet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        //Create a list
        List<Person> people = new ArrayList<>();
        // SQL - SELECT * FROM Person
        String query = "SELECT * FROM Person";
        try {
            // Prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            // Execute
            ResultSet resultSet = statement.executeQuery();
            // Check
            while (resultSet.next()) {
                // Add
                people.add(extractPersonFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Collection<Person> findByFirstName(String firstName) {
        //Create a list
        List<Person> people = new ArrayList<>();
        // SQL - SELECT * FROM Person WHERE firstName = ?
        String query = "SELECT * FROM Person WHERE firstName = ?";
        // Prepare statement
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, firstName);
            // Execute
            ResultSet resultSet = statement.executeQuery();
            // Check
            while (resultSet.next()) {
                people.add(extractPersonFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Collection<Person> findByLastName(String lastName) {
        //Create a list
        List<Person> people = new ArrayList<>();
        // SQL - SELECT * FROM Person WHERE lastName = ?
        String query = "SELECT * FROM Person WHERE lastName = ?";
        // Prepare statement
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, lastName);
            // Execute
            ResultSet resultSet = statement.executeQuery();
            // Check
            while (resultSet.next()) {
                people.add(extractPersonFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return people;

    }

    @Override
    public Person update(Person person) {
        // SQL - UPDATE Person SET firstName = ?, lastName = ? WHERE id = ?
        String query = "UPDATE Person SET firstName = ?, lastName = ? WHERE id = ?";
        // Prepare statement
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getId());
            // Execute
            int affectedRows = statement.executeUpdate();
            // Check
            if (affectedRows == 0) {
                throw new SQLException("Updating person failed, no rows affected.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        // SQL - DELETE FROM Person WHERE id = ?
        String query = "DELETE FROM Person WHERE id = ?";
        // Prepare statement
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setInt(1, id);
            // Execute
            int affectedRows = statement.executeUpdate();
            // Check
            if (affectedRows == 0) {
                throw new SQLException("Deleting person failed, no rows affected.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
