package com.edutecno.Logistiqal.dto;

import java.util.List;

import com.edutecno.Logistiqal.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends GenericDto {

	List<Product> productsList;

	public ProductDto(String mensaje, String codigo, List<Product> productsList) {
		super(mensaje, codigo);
		this.productsList = productsList;
	}

}
