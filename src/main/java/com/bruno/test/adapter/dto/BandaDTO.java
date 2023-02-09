package com.bruno.test.adapter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BandaDTO {

    @JsonIgnore
    private Integer id;
    private String name;
    private String genero;
}
