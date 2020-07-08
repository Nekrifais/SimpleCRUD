package com.example.crud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;

	
	public Page<Product> listAll( int pageNumber, String keyword) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		
		if(keyword != null) {
			return productRepository.findAll(keyword, pageable);
		}
		return productRepository.findAll(pageable);
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public Product get(int id) {
		return productRepository.findById(id).get();
	}
	
	public void delete(int id) {
		productRepository.deleteById(id);
		
	}


}
