package com.springboot.service;

import com.springboot.dao.ProductRepository;
import com.springboot.entity.ProductEntity;
import com.springboot.util.ProductEntityConverter;
import com.springboot.vo.PagedResponse;
import com.springboot.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository ProductRepo;

	public List<Product> findAllProducts() {
		List<ProductEntity> Products = ProductRepo.findAll();
		return Products.stream().map(e -> new Product(e.getId(), e.getName(), e.getQuantity(), e.getPrice()))
				.collect(Collectors.toList());
	}

	public Product findById(long id) {
		ProductEntity productEntity = ProductRepo.findById(id).orElse(null);
		return ProductEntityConverter.convertEntityToProduct(productEntity);
	}

	@Transactional
	public Product saveProduct(Product product) {
		ProductEntity productEntity = ProductRepo.save(new ProductEntity(product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
		return ProductEntityConverter.convertEntityToProduct(productEntity);
	}

	public Product updateProduct(Product product) {
		ProductEntity productEntity = ProductRepo.saveAndFlush(new ProductEntity(product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
		return ProductEntityConverter.convertEntityToProduct(productEntity);
	}

	public void deleteProductById(long id) {
		ProductRepo.deleteById(id);
	}

	public PagedResponse<Product> findPaginated(int page, int size, String orderBy) {

		Sort sort = null;
		if (orderBy != null) {
			sort = Sort.by(Sort.Direction.ASC, orderBy);
		}
		Page<ProductEntity> page1 = ProductRepo.findAll(PageRequest.of(page, size, sort));
		List<ProductEntity> list = page1.getContent();
		PagedResponse<Product> result = new PagedResponse<>();
		result.setPage(page1.getNumber());
		result.setRows(page1.getSize());
		result.setTotalPage(page1.getTotalPages());
		result.setTotalElement(page1.getTotalElements());
		result.setOrder(page1.getSort().toString());
		result.setBody(list.stream().map(e -> new Product(e.getId(), e.getName(), e.getQuantity(), e.getPrice()))
				.collect(Collectors.toList()));
		return result;
	}
}
