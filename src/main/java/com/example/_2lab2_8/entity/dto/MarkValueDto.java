package com.example._2lab2_8.entity.dto;

import org.hibernate.validator.constraints.Range;

public class MarkValueDto {

    @Range(min = 2, max = 10, message = "Mark value should be between 2 and 10")
    private Integer mark;

    public MarkValueDto() {
    }

    public MarkValueDto(@Range(min = 2, max = 10, message = "Mark value should be between 2 and 10") Integer mark) {
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
