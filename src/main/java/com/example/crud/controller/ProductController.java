package com.example.crud.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;

@Controller
public class ProductController {
	
	private static final Logger LOGGER = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductService service; 
	
	public void AppController(ProductService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		String keyword = "";
		
		return listByPage(model, 1, keyword);
	}
	
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, 
			@PathVariable("pageNumber") int currentPage, 
			@Param("keyword") String keyword) {
		
		Page<Product> page = service.listAll(currentPage, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("keyword", keyword);
		
		return "index";
	}
	
	
	@RequestMapping("/new")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		
		
		return "new_product";

	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		LOGGER.info("Product successfully saved. Product details: " + product);
		
		return "redirect:/";
		
	}
	

	@RequestMapping("/edit/{id}")
	public ModelAndView editProduct(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		
		
		return mav;
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		
		
		return "redirect:/";		
	}
}
