package com.karan.demoapp.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ToDo {

    private Integer id;
    private String value;
    private ToDoStatus status;

}
