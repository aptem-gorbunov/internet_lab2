package com.example._2lab2_8.entity.dto;

import java.util.Objects;

public class MarkDto {

    private Long studentId;
    private Long teacherId;
    private Long subjectId;

    private Integer mark;

    public MarkDto() {
    }

    public MarkDto(Long studentId, Long teacherId, Long subjectId, Integer mark) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.mark = mark;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkDto markDto = (MarkDto) o;
        return Objects.equals(studentId, markDto.studentId) &&
                Objects.equals(teacherId, markDto.teacherId) &&
                Objects.equals(subjectId, markDto.subjectId) &&
                Objects.equals(mark, markDto.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, teacherId, subjectId, mark);
    }

    @Override
    public String toString() {
        return "MarkDto{" +
                "studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                ", mark=" + mark +
                '}';
    }
}
