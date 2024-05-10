package se.lexicon.dao.daoimpl;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.Collection;

public interface TodoItemDao {

    TodoItem create(TodoItem todoItem);

    Collection<TodoItem> findAll();

    TodoItem findById(int todo_id);

    Collection<TodoItem> findByDoneStatus(boolean done);

    Collection<TodoItem> findByAssignee(int assignee_id);

    Collection<TodoItem> findByAssignee(Person assignee);

    Collection<TodoItem> findByUnassignedTodoitems();

    TodoItem update(TodoItem todoItem);

    boolean deleteById(int todo_id);
}
