package com.example.crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.crud.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	public Product findByName(String name);
}
