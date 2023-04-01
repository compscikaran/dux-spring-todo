package com.karan.demoapp.store;

public enum ToDoStatus {
    TO_DO("To Do"),DONE("Completed");

    private String value;

    ToDoStatus(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
