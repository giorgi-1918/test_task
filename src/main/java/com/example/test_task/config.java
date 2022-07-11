package com.example.test_task;

import com.example.test_task.model.Group;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class config {

    @Bean
    public List<Group> groupList() {

        return new ArrayList<>();
    }
}