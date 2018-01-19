package ru.demi.springmvc.services.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.demi.springmvc.config.JpaIntegrationConfig;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.ProductService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpa"})
public class ProductServiceJpaImplTest {

	@Autowired
	private ProductService productService;

	@Test
	public void testGetAllProducts() {
		List<Product> allProducts = productService.getAll();

		assertEquals(3, allProducts.size());
	}
}