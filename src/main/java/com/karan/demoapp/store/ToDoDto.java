package com.karan.demoapp.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoDto {
    private Integer id;
    private String value;
    private String status;

}
