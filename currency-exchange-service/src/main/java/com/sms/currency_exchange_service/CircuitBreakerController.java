package com.sms.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/circuitbreaker")
//	@Retry(name = "circuitbreaker", fallbackMethod = "defaultFallback")
	@CircuitBreaker(name = "circuitbreaker", fallbackMethod = "defaultFallback")
	public String defaultFallback() {
		logger.info("API received");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/service", String.class);
		return response.getBody();
	}
	
	public String defaultFallback(Exception e) {
		return "Service is down";
	}
}
