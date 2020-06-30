package com.example.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.crud.repository.ProductRepository;
import com.example.crud.model.Product;


@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
public class SpringDataTestCases {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateProduct() {
		Product product = new Product("Potatos", 110);

		Product savedProduct = productRepository.save(product);
		
		assertNotNull(savedProduct);
		
	}
	
	@Test
	@Rollback(false)
	@Order(6)
	public void testDeleteProduct() {
		Integer id = 1;
		
		boolean isExistBeforeDelete = productRepository.findById(id).isPresent();
		
		productRepository.deleteById(id);
		
		boolean notExistAfterDelete = productRepository.findById(id).isPresent();
		
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
			
	}
	
	//26
	 @Test
	 @Order(2)
	public void testFindProductByNameExist() {
		String name = "Potatos";
	//	String name = "Riddler";
		Product product = productRepository.findByName(name);
		
		System.out.println(product.getName());
		System.out.println(name);
		assertThat(product.getName()).isEqualTo(name);
		System.out.println(product.getName());
		System.out.println(name);
	}
	
	@Test
	@Order(3)
	public void testFindProductByNameNotExist() {
		String name = "Pepsi";
		Product product = productRepository.findByName(name);
		
		assertNull(product);
	}
	
	@Test
	@Rollback(false)
	 @Order(4)
	public void testUpdateProduct() {
		String productName = "Riddler";
		Product product = new Product(productName, 25);
		
		
		productRepository.save(product);
		
		Product updateProduct = productRepository.findByName(productName);
		
		assertThat(updateProduct.getName()).isEqualTo(productName);
		
	}
	
	@Test
	@Order(5)
	public void testListProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		
		for(Product product : products) {
			System.out.println(product);
		}
		
		assertThat(products).size().isGreaterThan(0);
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
