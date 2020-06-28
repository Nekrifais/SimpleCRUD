package com.example.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.domain.Product;
import com.example.crud.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> listAll() {
		return (List<Product>) productRepository.findAll();
	}
	
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public Product get(long id) {
		return productRepository.findById(id).get();
	}
	
	public void delete(long id) {
		productRepository.deleteById(id);
	}


}
