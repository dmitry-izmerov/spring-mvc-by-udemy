package ru.demi.springmvc.services;

import ru.demi.springmvc.models.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Integer id);

	Product saveProduct(Product product);

	void deleteProduct(Integer id);
}
