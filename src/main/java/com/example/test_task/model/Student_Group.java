package com.example.test_task.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Table(name = "Student_Group" )
public class Student_Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id" , nullable = false)
    private Student student;

    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "group_id" , nullable = false)
    private Group group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
