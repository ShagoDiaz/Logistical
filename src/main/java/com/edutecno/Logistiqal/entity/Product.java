package com.edutecno.Logistiqal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@SequenceGenerator(name = "SQ_PRODUCT", initialValue = 1, allocationSize = 1, sequenceName = "SQ_PRODUCT")
public class Product {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUCT")
	@Id
	private Integer id_product;
	@Column(unique = true)
	private String name;
	private Integer price;
	private Integer stock;

}
