package com.UserDB.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Authcontroller {

@RequestMapping("/")
    public String showPage(){
        return "index";
    }

}
