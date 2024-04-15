package com.maria.spring.mvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller //spring MVC controller
@RequestMapping("/") //url path
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Logic for displaying the homepage
        return "index"; 
    }

   
    }

