package ru.demi.springmvc.ctrls;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ProductCtrlTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductCtrl productCtrl;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productCtrl).build();
	}

	@Test
	public void getAllProducts() throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		when(productService.getAll()).thenReturn(products);

		mockMvc.perform(get("/products"))
			.andExpect(status().isOk())
			.andExpect(view().name("product-list"))
			.andExpect(model().attribute("products", hasSize(2)));
	}

	@Test
	public void getProduct() throws Exception {
		int productId = 1;
		Product product = new Product();
		when(productService.getById(productId)).thenReturn(product);

		mockMvc.perform(get("/product/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("product"))
			.andExpect(model().attribute("product", is(product)));
	}

	@Test
	public void getEmptyProduct() throws Exception {
		mockMvc.perform(get("/product/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("product"))
			.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void saveProduct() throws Exception {
		Integer id = 1;
		String description = "test desc";
		BigDecimal price = new BigDecimal("12.12");
		String imageUrl = "https://some.url";

		Product product = new Product(id, description, price, imageUrl);
		when(productService.save(Matchers.any(Product.class))).thenReturn(product);

		mockMvc.perform(
			post("/product")
			.param("id", String.valueOf(id))
			.param("description", description)
			.param("price", price.toString())
			.param("imageUrl", imageUrl)
		)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/products"));

		ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
		verify(productService).save(captor.capture());

		Product captured = captor.getValue();
		assertEquals(id, captured.getId());
		assertEquals(description, captured.getDescription());
		assertEquals(price, captured.getPrice());
		assertEquals(imageUrl, captured.getImageUrl());
	}

	@Test
	public void deleteProduct() throws Exception {
		int productId = 1;
		doNothing().when(productService).delete(productId);

		mockMvc.perform(get("/product/delete/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/products"));

		verify(productService).delete(productId);
	}
}