package com.m3bi.hotelbooking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class LoginController {
	
	@RequestMapping
    public String helloAdmin() {
        return "hello admin";
    }

}
