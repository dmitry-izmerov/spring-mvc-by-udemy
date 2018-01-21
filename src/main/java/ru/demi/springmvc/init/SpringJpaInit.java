package ru.demi.springmvc.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.demi.springmvc.models.Address;
import ru.demi.springmvc.models.Customer;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.CustomerService;
import ru.demi.springmvc.services.ProductService;

import java.math.BigDecimal;

@Component
public class SpringJpaInit implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadProducts();
		loadCustomers();
	}

	private void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("Ivan");
		customer1.setLastName("Ivanov");
		customer1.setBillingAddress(new Address());
		customer1.getBillingAddress().setCity("Moscow");
		customer1.getBillingAddress().setStreet("Moscowskaya");
		customer1.getBillingAddress().setHouse("105");
		customer1.getBillingAddress().setFlat("1");
		customer1.getBillingAddress().setPostalCode("100500");
		customerService.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Petr");
		customer2.setLastName("Petrov");
		customer2.setBillingAddress(new Address());
		customer2.getBillingAddress().setCity("Saint-Petersburg");
		customer2.getBillingAddress().setStreet("Peterburgskaya");
		customer2.getBillingAddress().setHouse("100");
		customer2.getBillingAddress().setFlat("1");
		customer2.getBillingAddress().setPostalCode("200500");
		customerService.save(customer2);

		Customer customer3 = new Customer();
		customer3.setFirstName("Sidr");
		customer3.setLastName("Sidrov");
		customer3.setBillingAddress(new Address());
		customer3.getBillingAddress().setRegion("Moscow Region");
		customer3.getBillingAddress().setCity("Vidnoe");
		customer3.getBillingAddress().setStreet("Agapovo");
		customer3.getBillingAddress().setHouse("10");
		customer3.getBillingAddress().setFlat("1");
		customer3.getBillingAddress().setPostalCode("300500");
		customerService.save(customer3);
	}

	private void loadProducts() {
		Product product1 = new Product();
		product1.setDescription("Product 1");
		product1.setPrice(new BigDecimal("11.11"));
		product1.setImageUrl("http://image.com/product1");
		productService.save(product1);

		Product product2 = new Product();
		product2.setDescription("Product 2");
		product2.setPrice(new BigDecimal("12.12"));
		product2.setImageUrl("http://image.com/product2");
		productService.save(product2);

		Product product3 = new Product();
		product3.setDescription("Product 3");
		product3.setPrice(new BigDecimal("13.13"));
		product3.setImageUrl("http://image.com/product3");
		productService.save(product3);
	}
}
