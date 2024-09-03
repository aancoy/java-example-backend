package com.aegis.test.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

	// API Create
	@GetMapping("")
	public String showWellcome() {
		return "Wellcome To Test API";
	}
}
