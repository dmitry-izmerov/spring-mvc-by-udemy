package ru.demi.springmvc.services;

import org.springframework.stereotype.Service;
import ru.demi.springmvc.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Map<Integer, Product> products = new HashMap<>();

	static {
		Product product1 = new Product();
		product1.setId(1);
		product1.setDescription("Product 1");
		product1.setPrice(new BigDecimal("11.11"));
		product1.setImageUrl("http://image.com/product1");
		products.put(1, product1);

		Product product2 = new Product();
		product2.setId(2);
		product2.setDescription("Product 2");
		product2.setPrice(new BigDecimal("12.12"));
		product2.setImageUrl("http://image.com/product2");
		products.put(2, product2);

		Product product3 = new Product();
		product3.setId(3);
		product3.setDescription("Product 3");
		product3.setPrice(new BigDecimal("13.13"));
		product3.setImageUrl("http://image.com/product3");
		products.put(3, product3);
	}

	@Override
	public List<Product> getAllProducts() {
		return new ArrayList<>(products.values());
	}

	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveProduct(Product product) {
		if (Objects.isNull(product)) {
			throw new RuntimeException("Product shouldn't be null.");
		}

		if (product.isNew()) {
			int id = getNextId();
			product.setId(id);
			products.put(id, product);
		} else if (products.containsKey(product.getId())) {
			products.put(product.getId(), product);
		} else {
			throw new RuntimeException("Product with passed id doesn't exist.");
		}
		return product;
	}

	private Integer getNextId() {
		return Collections.max(products.keySet()) + 1;
	}
}
