package com.edutecno.Logistiqal.service;

import java.util.List;

import com.edutecno.Logistiqal.dto.ProductDto;
import com.edutecno.Logistiqal.entity.Product;

public interface IProductService {

	public ProductDto getAll();

	public ProductDto add(Product product);

	public ProductDto del(Product product);

	public ProductDto edit(Product product);

	public ProductDto findById(Integer idProduct);

	public ProductDto findByName(String name, Integer pagina, Integer cantidad);

	public ProductDto getPage(Integer pagina, Integer cantidad);

	public int getPageCount(long registrosPorPagina, String texto);
}
