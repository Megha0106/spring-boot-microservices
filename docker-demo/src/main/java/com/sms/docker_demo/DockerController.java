package com.sms.docker_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello Docker! v2";
	}
}
