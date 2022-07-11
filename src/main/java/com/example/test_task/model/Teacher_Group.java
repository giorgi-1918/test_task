package com.example.test_task.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Table(name = "Teacher_Group" )
public class Teacher_Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name ="teacher_id" , nullable = false)
    private Teacher teacher;

    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "group_id" , nullable = false)
    private Group group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
