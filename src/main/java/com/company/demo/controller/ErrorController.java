package com.company.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("403")
    public String getBlock()
    {
        return "Error not allowed to see this page :)";
    }
    
    @RequestMapping("/")
    public String getError()
    {
        return "Error en servidor .l.";
    }
}
