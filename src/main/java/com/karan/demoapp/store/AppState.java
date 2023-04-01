package com.karan.demoapp.store;

import lombok.Getter;
import lombok.Setter;
import org.flux.store.api.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class AppState implements State {

    private List<ToDo> toDoList;

    public AppState() {
        toDoList = new ArrayList<>();
    }

    @Override
    public AppState clone() {
        try {
            return (AppState) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        String values = Arrays.toString(this.toDoList.toArray());
        return String.format("AppState{%s}", values);
    }
}
