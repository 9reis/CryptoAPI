package com.reis.cryptoApp.controller;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping
	public ResponseEntity get() {
		return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity get(@PathVariable String name) {
		try {
			return new ResponseEntity<>(coinRepository.getByName(name),HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
