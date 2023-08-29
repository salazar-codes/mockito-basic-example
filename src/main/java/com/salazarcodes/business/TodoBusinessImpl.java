package com.salazarcodes.business;

import com.salazarcodes.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
// TodoBusinessImpl  SUT (SystemUnderTest)
// TodoService  Dependency
public class TodoBusinessImpl {
    private Predicate<String> containsPredicate = (srt) -> srt.contains("Spring");
    private TodoService todoService;

    private Function<List<String>,List<String>> fetchTodos = (allTodos) -> {
        List<String> filteredTodos = new ArrayList<>();
        allTodos.forEach((todo)->{
            if (containsPredicate.test(todo)) {
                filteredTodos.add(todo);
            }
        });
        return filteredTodos;
    };

    TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> allTodos = todoService.retrieveTodos(user);
        filteredTodos = fetchTodos.apply(allTodos);
        return filteredTodos;
    }

    public void deleteTodosRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);

        for (String todo: todos){
            if (!containsPredicate.test(todo)) {
                todoService.deleteTodos(todo);
            }
        }
    }
}