package com.edutecno.Logistiqal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edutecno.Logistiqal.entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

	Page<Product> findByNameContaining(String name, Pageable pageable);

	List<Product> findByNameLike(String name);

}
