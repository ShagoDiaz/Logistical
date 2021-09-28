package com.edutecno.Logistiqal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.Logistiqal.LogistiqalApplication;
import com.edutecno.Logistiqal.dao.ProductDao;
import com.edutecno.Logistiqal.dto.ProductDto;
import com.edutecno.Logistiqal.entity.Product;

@Service
public class ProductServiceImpl implements IProductService {

	private static final Logger log = LoggerFactory.getLogger(LogistiqalApplication.class);

	@Autowired
	ProductDao dao;
	ProductDto respuesta;

	@Override
	@Transactional(readOnly = true)
	public ProductDto getAll() {
		respuesta = new ProductDto("Error while try to get data from DB", "102", new ArrayList<Product>());
		try {
			respuesta.setProductsList((List<Product>) dao.findAll());
			respuesta.setCodigo("000");
			respuesta.setMensaje("Products load");
		} catch (Exception e) {
			log.error("ProductService -> getAll : Error " + e);
		}

		return respuesta;
	}

	@Override
	public ProductDto add(Product product) {
		respuesta = new ProductDto("Error while try save data into DB", "103", new ArrayList<Product>());
		try {
			List<Product> productList = new ArrayList<Product>();
			productList.add(dao.save(product));
			respuesta.setProductsList(productList);
			respuesta.setCodigo("000");
			respuesta.setMensaje("Product saved");
		} catch (Exception e) {
			log.error("ProductService -> add : Error " + e);
		}
		return respuesta;
	}

	@Override
	public ProductDto del(Product product) {
		respuesta = new ProductDto("Error while try del data from DB", "104", new ArrayList<Product>());
		try {
			dao.delete(product);
			List<Product> productList = new ArrayList<Product>();
			productList.add(product);
			respuesta.setProductsList(productList);
			respuesta.setCodigo("000");
			respuesta.setMensaje("Product deleted");
		} catch (Exception e) {
			log.error("ProductService -> del : Error " + e);
		}
		return respuesta;
	}

	@Override
	public ProductDto edit(Product product) {
		respuesta = new ProductDto("Error while try update data into DB", "105", new ArrayList<Product>());
		try {
			List<Product> productList = new ArrayList<Product>();
			productList.add(dao.save(product));
			respuesta.setProductsList(productList);
			respuesta.setCodigo("000");
			respuesta.setMensaje("Product edited");
		} catch (Exception e) {
			log.error("ProductService -> edit : Error " + e);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDto findById(Integer idProduct) {
		respuesta = new ProductDto("Error while try to get data from DB", "106", new ArrayList<Product>());
		try {
			Optional<Product> optionalProductList = dao.findById(idProduct);
			List<Product> productList = new ArrayList<Product>();
			productList.add(optionalProductList.get());
			respuesta.setProductsList(productList);
			respuesta.setCodigo("000");
			respuesta.setMensaje("Product loaded");
		} catch (Exception e) {
			log.error("ProductService -> findById : Error " + e);
		}

		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDto findByName(String name, Integer pagina, Integer cantidad) {
		respuesta = new ProductDto("Error while try to get data from DB", "107", new ArrayList<Product>());
		try {
			Pageable pageable = PageRequest.of(pagina, cantidad);
			Page<Product> responsePage = dao.findByNameContaining(name, pageable);
			respuesta.setProductsList(responsePage.getContent());
			respuesta.setCodigo("000");
			respuesta.setMensaje("Products loaded");
		} catch (Exception e) {
			log.error("ProductService -> findByName : Error " + e);
		}

		return respuesta;
	}

	@Transactional(readOnly = true)
	public ProductDto getPage(Integer pagina, Integer cantidad) {
		respuesta = new ProductDto("Ha ocurrido un error", "108", new ArrayList<Product>());
		try {
			Pageable pageable = PageRequest.of(pagina, cantidad);
			Page<Product> responsePage = dao.findAll(pageable);
			respuesta.setProductsList(responsePage.getContent());
			respuesta.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuesta.getProductsList().size()));
			respuesta.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en getPage", e);
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageCount(long registrosPorPagina, String texto) {
		int registros;
		List<Product> lista;
		if (texto != null) {
			lista = (List<Product>) dao.findByNameLike(texto);
		} else {
			lista = (List<Product>) dao.findAll();
		}
		registros = lista.size();
		try {
			if (registrosPorPagina == 0 && registros == 0) {
				return 1;
			} else {
				return (int) ((registros / registrosPorPagina) + (registros % registrosPorPagina == 0 ? 0 : 1));
			}

		} catch (Exception e) {
			log.trace("Usuario Service: Error en getPageCount", e);
		}
		return 1;
	}
}
