package com.bruno.test.adapter.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class StandError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
