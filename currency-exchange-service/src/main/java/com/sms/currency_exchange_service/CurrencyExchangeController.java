package com.sms.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    	logger.info("retrieveExchangeValue called with {} to {}", from,to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);
        System.out.println("currencyExchange1 = " + currencyExchange);
        if(currencyExchange == null)
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        String prop = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(prop);
        return currencyExchange;
    }

    @GetMapping("/currency-exchange")
    public List<CurrencyExchange> retrieveAllExchangeValues() {
        return currencyExchangeRepository.findAll();
    }
}
