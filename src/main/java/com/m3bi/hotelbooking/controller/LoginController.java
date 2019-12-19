package com.m3bi.hotelbooking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class LoginController {
	


		@GetMapping("/")
		   public String index() {
		     return"Hello World";
		   }

}
