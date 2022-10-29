package com.reis.cryptoApp.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.reis.cryptoApp.entity.Coin;

public class CoinRepository {
	
	private static String INSERT = "INSERT INTO coin (nam,price,quantity,datetime) values(?,?,?,?)"; 
	
	private JdbcTemplate jdbcTemplate;
	
	public CoinRepository( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
	
	public Coin insert(Coin coin) {
		Object[] attr = new Object[] {
			coin.getName(),
			coin.getPrice(),
			coin.getQuantity(),
			coin.getDateTime()
		};
		
		jdbcTemplate.update(INSERT, attr);
		return coin; 
	}
	
	
}
