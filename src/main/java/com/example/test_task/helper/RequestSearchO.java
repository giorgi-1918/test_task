package com.example.test_task.helper;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RequestSearchO {
    private String name;
    private String last_name;
    private String private_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPrivate_id() {
        return private_id;
    }

    public void setPrivate_id(String private_id) {
        this.private_id = private_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
