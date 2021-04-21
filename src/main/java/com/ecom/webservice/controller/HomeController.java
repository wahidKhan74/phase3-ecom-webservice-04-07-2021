package com.ecom.webservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String serverUp() {
		return "Server is running !";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1> Welcome Admin</h1>";
	}
}
