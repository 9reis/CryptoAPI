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
		
	
	public List<Coin> getByName(String name){
		String jpql = "SELECT c FROM Coin c WHERE c.name LIKE :name";
		
		TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}
	
	/*
	public int remove(int id) {
		return jdbcTemplate.update(DELETE,id);
	}
	*/
	
	
}
