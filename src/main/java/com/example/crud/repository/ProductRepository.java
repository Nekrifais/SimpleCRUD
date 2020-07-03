package com.example.crud.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.crud.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	public Product findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE "
			+ "CONCAT(p.id, p.name, p.price)"
			+ " LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);	
	
	
	
	
}
