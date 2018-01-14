package ru.demi.springmvc.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.ProductService;

import java.math.BigDecimal;

@Component
public class SpringJpaInit implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ProductService productService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadProducts();
	}

	private void loadProducts() {
		Product product1 = new Product();
		product1.setDescription("Product 1");
		product1.setPrice(new BigDecimal("11.11"));
		product1.setImageUrl("http://image.com/product1");
		productService.saveProduct(product1);

		Product product2 = new Product();
		product2.setDescription("Product 2");
		product2.setPrice(new BigDecimal("12.12"));
		product2.setImageUrl("http://image.com/product2");
		productService.saveProduct(product2);

		Product product3 = new Product();
		product3.setDescription("Product 3");
		product3.setPrice(new BigDecimal("13.13"));
		product3.setImageUrl("http://image.com/product3");
		productService.saveProduct(product3);
	}
}
