package com.springboot.controller;

import com.springboot.exception.ProductException;
import com.springboot.exception.ProductNotAvailableException;
import com.springboot.exception.ProductNotFoundException;
import com.springboot.service.ProductService;
import com.springboot.util.Constants;
import com.springboot.vo.ErrorResponse;
import com.springboot.vo.PagedResponse;
import com.springboot.vo.ResponseMessage;
import com.springboot.vo.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@Api(value = "Product", description = "REST API for Products", tags={"Product"})
public class ProductRestController {

	private static Logger logger = LoggerFactory.getLogger(ProductRestController.class);

	ProductService productService;

	Constants messages;

	@Autowired
	public ProductRestController(ProductService productService, Constants messages) {
		this.productService = productService;
		this.messages = messages;
	}
	/**
	 * retrieves single product
	 * 
	 **/
	@ApiOperation(value = "gets a single product")
	@RequestMapping(value = "/product/{uid}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("uid") long id) throws ProductException {
		Product product = productService.findById(id);
		if (product == null) {
			throw new ProductNotFoundException(messages.getMessage("Product_NOT_FOUND"));
		}
		if (product.getQuantity() == 0) {
			throw new ProductNotAvailableException(messages.getMessage("Product_NOT_AVAILABLE"));
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	//http://localhost:8009/swagger-ui.html#/
	/**
	 *  Get product by using pagination, if no parameters are provided, the first page with 5 records will be returned
	 *
	 **/
	@ApiOperation(value = "get products accordingly")
	@RequestMapping(value = "/product",  method = RequestMethod.GET)
	public ResponseEntity<PagedResponse<Product>> getProductPagenation(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
																	@RequestParam(required = false, defaultValue = "5") Integer rows,
																	@RequestParam(required = false, defaultValue = "name") String orderBy) {

		PagedResponse<Product> Products = productService.findPaginated(pageNo, rows, orderBy);
		if (Products.isEmpty()) {
			throw new ProductNotFoundException(messages.getMessage("Product_NOT_FOUND"));
		}

		return new ResponseEntity<PagedResponse<Product>>(Products, HttpStatus.OK);

	}

	/** create a Product **/
	@ApiOperation(value = "create a product")
	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessage> createProduct(@Validated @RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Product savedProduct = productService.saveProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("Product_CREATED"), savedProduct), headers, HttpStatus.CREATED);
	}

	/**
	 * update a Product
	 * 
	 **/
	@ApiOperation(value = "update a product")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product){
		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			throw new ProductNotFoundException(messages.getMessage("Product_NOT_FOUND"));
		}

		currentProduct.setName(product.getName());
		currentProduct.setQuantity(product.getQuantity());
		currentProduct.setPrice(product.getPrice());

		productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}

	/**
	 * delete a Product
	 * 
	 * @throws ProductException
	 **/
	@ApiOperation(value = "delete a product")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable("id") long id) {

		Product product = productService.findById(id);
		if (product == null) {
			throw new ProductNotFoundException(messages.getMessage("Product_NOT_FOUND"));
		}
		productService.deleteProductById(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("Product_DELETED"), product), HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		logger.error("Controller Error",ex);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerProductNotFound(Exception ex) {
		logger.error("Cannot find Product");
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
