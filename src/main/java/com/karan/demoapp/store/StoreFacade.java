package com.karan.demoapp.store;

import jakarta.annotation.PostConstruct;
import org.flux.store.api.Action;
import org.flux.store.api.Middleware;
import org.flux.store.api.Reducer;
import org.flux.store.main.DuxStore;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StoreFacade {

    public static final String ACTION_ADD_ITEM = "ADD_TODO";
    public static final String ACTION_COMPLETE_ITEM = "COMPLETE_TODO";

    private DuxStore<AppState> myStore;

    @PostConstruct
    public void init() {

        Reducer<AppState> reducer = (action, state) -> {
            switch (action.getType()) {
                case ACTION_ADD_ITEM:
                    ToDo newItem = (ToDo) action.getPayload();
                    state.getToDoList().add(newItem);
                    break;
                case ACTION_COMPLETE_ITEM:
                    String itemValue = action.getPayload().toString();
                    Optional<ToDo> item = state.getToDoList()
                            .stream()
                            .filter(x -> x.getId() == Integer.parseInt(itemValue))
                            .findFirst();
                    if(item.isPresent()) {
                        ToDo todo = item.get();
                        todo.setStatus(ToDoStatus.DONE);
                    }
                    break;
            }
            return state;
        };

        myStore = new DuxStore<>(new AppState(), reducer);
        myStore.subscribe((state) -> System.out.println(state));
    }

    public void dispatch(Action action) {
        myStore.dispatch(action);
    }

    public List<ToDo> getAll() {
        return myStore.getState().getToDoList();
    }

    public ToDo getById(Integer id) {
        Optional<ToDo> item = myStore.getState().getToDoList()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        if(item.isPresent()) {
            return item.get();
        }
        return null;
    }

}
