package com.example.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.crud.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
