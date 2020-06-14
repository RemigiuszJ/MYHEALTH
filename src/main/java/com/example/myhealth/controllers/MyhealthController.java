package com.example.myhealth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyhealthController {
    @RequestMapping(value = {"/"})
    public String getHospitals() {return  "index";}
}
