package com.karan.demoapp.rest;

import com.karan.demoapp.store.StoreFacade;
import com.karan.demoapp.store.ToDo;
import com.karan.demoapp.store.ToDoDto;
import com.karan.demoapp.store.ToDoStatus;
import org.flux.store.api.Action;
import org.flux.store.main.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private StoreFacade store;

    @GetMapping("/")
    public List<ToDo> getAllToDo() {
        return store.getAll();
    }

    @GetMapping("/{id}")
    public ToDo getById(@PathVariable Integer id) {
        return store.getById(id);
    }

    @PostMapping("/add")
    public String getAllToDo(@RequestBody ToDoDto item) {
        ToDo entity = new ToDo(item.getId(), item.getValue(), ToDoStatus.valueOf(item.getStatus()));
        Action<ToDo> newTodo = Utilities.actionCreator(StoreFacade.ACTION_ADD_ITEM, entity);
        store.dispatch(newTodo);
        return "Item added successfully";
    }

    @PutMapping("/complete/{id}")
    public String completeToDo(@PathVariable Integer id) {
        Action<String> completeTodo = Utilities.actionCreator(StoreFacade.ACTION_COMPLETE_ITEM, String.valueOf(id));
        store.dispatch(completeTodo);
        return "Item completed successfully";
    }

}
