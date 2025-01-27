package com.project.two.UserServiceServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/")
	public String greet(){
		return "hello form the user";
	}
}
