package com.pawan.spring.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.spring.microservices.currencyexchangeservice.bean.ExchangeValue;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment env;
	
	@Autowired
	ExchangeValueRepository evr;
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue reteriveCurrencyValue(@PathVariable String from,@PathVariable String to) {
		ExchangeValue exchangeValue =  evr.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info(" exchangeValue -> {}", exchangeValue);
		return exchangeValue;
	}

}
