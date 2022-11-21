package com.springboot.service;

import java.util.List;

import com.springboot.vo.PagedResponse;
import com.springboot.vo.Product;

public interface ProductService {

		Product findById(long id);

		Product saveProduct(Product product);

		Product updateProduct(Product product);

		void deleteProductById(long id);
	 
	    List<Product> findAllProducts();

		PagedResponse<Product> findPaginated(int page, int size, String orderBy);
	     
	    
}