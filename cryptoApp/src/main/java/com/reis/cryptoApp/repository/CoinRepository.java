package com.reis.cryptoApp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import com.reis.cryptoApp.dto.CoinTransationDTO;
import com.reis.cryptoApp.entity.Coin;

@Repository
@EnableAutoConfiguration 
public class CoinRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional 
	public Coin insert(Coin coin) {
		entityManager.persist(coin);
		return coin; 
	}
	
	@Transactional
	public Coin update(Coin coin) {
		entityManager.merge(coin);
		return coin;
	}
	
	public List<CoinTransationDTO> getAll(){
		String jpql = "SELECT new com.reis.cryptoApp.dto.CoinTransationDTO(c.name, SUM(c.quantity)) FROM Coin c GROUP BY c.name";
		TypedQuery<CoinTransationDTO> query = entityManager.createQuery(jpql, CoinTransationDTO.class);
		return query.getResultList();
	}
		
	/*
	public List<Coin> getByName(String name){
		
		Object[] attr = new Object[] {name};
		
		return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
			@Override
			public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Coin coin = new Coin();
				coin.setId(rs.getInt("id"));
				coin.setName(rs.getString("name"));
				coin.setPrice(rs.getBigDecimal("price"));
				coin.setQuantity(rs.getBigDecimal("quantity"));
				coin.setDateTime(rs.getTimestamp("datetime"));
				
				return coin; 
			}
		}, attr);
	}
	
	public int remove(int id) {
		return jdbcTemplate.update(DELETE,id);
	}
	*/
	
	
}
