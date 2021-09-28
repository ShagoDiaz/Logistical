package com.edutecno.Logistiqal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edutecno.Logistiqal.LogistiqalApplication;
import com.edutecno.Logistiqal.dto.ProductDto;
import com.edutecno.Logistiqal.entity.Product;
import com.edutecno.Logistiqal.service.IProductService;

@Controller
@RequestMapping(value = "/logistiqal")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(LogistiqalApplication.class);

	@Autowired
	private IProductService service;
	private ProductDto dto;
	private int items = 10;
	private int page = 0;

	@GetMapping(value = "/")
	public String getAll(ModelMap model, @RequestParam(required = false) String text) {
		if (text != null) {
			dto = service.findByName(text, page, items);
		} else {
			dto = service.getPage(page, items);
		}
		model.addAttribute("products", dto.getProductsList());
		model.addAttribute("pages", service.getPageCount(items, text));
		return "Logistiqal";
	}

	@GetMapping(value = "/page")
	public String getPage(ModelMap model, @ModelAttribute("page") String page) {
		this.page = Integer.parseInt(page);
		return "redirect:/logistiqal/";
	}

	@PostMapping(value = "/add")
	public String add(ModelMap model, @ModelAttribute("product") Product product) {
		service.add(product);
		return "redirect:/logistiqal/";
	}

	@PostMapping(value = "/del")
	public String del(ModelMap model, @ModelAttribute("product") Product product) {
		service.del(product);
		return "redirect:/logistiqal/";
	}

	@PostMapping(value = "/edit")
	public String edit(ModelMap model, @ModelAttribute("product") Product product) {
		service.edit(product);
		return "redirect:/logistiqal/";
	}

	@GetMapping(value = "/items")
	public String setItems(@ModelAttribute("items") String items) {
		this.items = Integer.parseInt(items);
		this.page = 0;
		return "redirect:/logistiqal/";
	}
}
