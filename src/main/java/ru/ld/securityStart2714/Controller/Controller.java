package ru.ld.securityStart2714.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/private-data")
    public String privateData(){
        return "private-data.html";
    }

    @GetMapping("/public-data")
    public String publicData(){
        return "public-data.html";
    }

    @GetMapping("/")
    public String login(){
        return "index.html";
    }
}
