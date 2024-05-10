package se.lexicon.dao.daoimpl;


import se.lexicon.db.MySQLConnection;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemDaoImpl implements TodoItemDao{

    private Connection connection;

    //Extract TodoItem from ResultSet
    private TodoItem extractTodoItemFromResultSet(ResultSet resultSet) throws SQLException {
        // get - todo_id, title, description, deadline, done, assignee_id - return TodoItem

        int todo_id = resultSet.getInt("todo_id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
        boolean done = resultSet.getBoolean("done");
        int assignee_id = resultSet.getInt("assignee_id");
        return new TodoItem(todo_id, title, description, deadline, done, assignee_id);
    }

    //Constructor
    public TodoItemDaoImpl() {
        this.connection = MySQLConnection.getConnection();
    }

    @Override
    public TodoItem create(TodoItem todoItem) {
        //SQL - INSERT INTO TodoItem (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)
        String query = "INSERT INTO TodoItem (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        //Prepare Statement
        try(PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS))
        {;
            //set parameters
            statement.setString(1, todoItem.getTitle());
            statement.setString(2, todoItem.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(todoItem.getDeadline()));
            statement.setBoolean(4, todoItem.isDone());
            statement.setInt(5, todoItem.getAssignee_id());
            //Execute
            int affectedRows = statement.executeUpdate();
            //Check
            if (affectedRows == 0) {
                throw new SQLException("Creating todoItem failed, no rows affected.");
            }
            try
                    (ResultSet generatedKeys = statement.getGeneratedKeys()){;
                if (generatedKeys.next()) {
                    todoItem.setTodo_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating todoItem failed, no ID obtained.");
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return todoItem;
    }

    @Override
    public Collection<TodoItem> findAll() {
        //Create a list
        List<TodoItem> todoItems = new ArrayList<>();
        //SQL - SELECT * FROM TodoItem
        String query = "SELECT * FROM TodoItem";
        //Prepare Statement
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            //Execute
            ResultSet resultSet = statement.executeQuery();
            //Check
            if (resultSet.next()) {
                return extractTodoItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public TodoItem findById(int todo_id) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean done) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByAssignee(int assignee_id) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person assignee) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoitems() {
        return List.of();
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        return null;
    }

    @Override
    public boolean deleteById(int todo_id) {
        return false;
    }
}
