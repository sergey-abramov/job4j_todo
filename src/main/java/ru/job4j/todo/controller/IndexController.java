package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    public String getIndex() {
        return "/index";
    }
}