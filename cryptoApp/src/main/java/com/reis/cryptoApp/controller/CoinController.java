package com.reis.cryptoApp.controller;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reis.cryptoApp.entity.Coin;
import com.reis.cryptoApp.repository.CoinRepository;

@RestController
@RequestMapping("/coin")
public class CoinController {
	
	private CoinRepository coinRepository;
	
	public CoinController(CoinRepository coinRepository) {
		this.coinRepository = coinRepository;
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Coin coin) {
		
		try {
			coin.setDateTime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.insert(coin),HttpStatus.CREATED);
		}catch(Exception error) {
			return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
