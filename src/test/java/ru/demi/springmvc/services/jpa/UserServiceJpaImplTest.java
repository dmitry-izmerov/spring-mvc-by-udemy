package ru.demi.springmvc.services.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.demi.springmvc.config.JpaIntegrationConfig;
import ru.demi.springmvc.models.Cart;
import ru.demi.springmvc.models.CartDetail;
import ru.demi.springmvc.models.Customer;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.models.User;
import ru.demi.springmvc.services.ProductService;
import ru.demi.springmvc.services.UserService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpa"})
public class UserServiceJpaImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Test
	public void shouldSaveUser() {
		User user = new User();
		user.setUsername("test guy");
		user.setPassword("some pass");

		User saved = userService.save(user);

		assertNotNull(saved.getId());
		assertNotNull(saved.getEncryptedPassword());
	}

	@Test
	public void shouldSaveUserWithCustomer() {
		User user = new User();
		user.setUsername("user name");
		user.setPassword("pass");
		Customer customer = new Customer();
		customer.setFirstName("Test");
		customer.setLastName("Testov");
		user.setCustomer(customer);

		User saved = userService.save(user);

		assertNotNull(saved.getId());
		assertNotNull(saved.getEncryptedPassword());
		assertNotNull(saved.getCustomer());
		assertNotNull(saved.getCustomer().getId());
	}

	@Test
	public void shouldSaveUserWithCart() {
		User user = new User();
		user.setUsername("user name");
		user.setPassword("pass");
		user.setCart(new Cart());

		User saved = userService.save(user);

		assertNotNull(saved.getId());
		assertNotNull(saved.getCart());
		assertNotNull(saved.getCart().getId());
	}

	@Test
	public void shouldSaveUserWithCartAndDetails() {
		User user = new User();
		user.setUsername("user name");
		user.setPassword("pass");

		Cart cart = new Cart();
		user.setCart(cart);

		List<Product> products = productService.getAllProducts();
		CartDetail cartDetail1 = new CartDetail();
		cartDetail1.setProduct(products.get(0));
		cart.addCartDetail(cartDetail1);
		CartDetail cartDetail2 = new CartDetail();
		cartDetail2.setProduct(products.get(1));
		cart.addCartDetail(cartDetail2);

		User saved = userService.save(user);

		assertNotNull(saved.getId());
		assertNotNull(saved.getCart());
		assertNotNull(saved.getCart().getId());
		assertEquals(2, saved.getCart().getCartDetails().size());
	}

	@Test
	public void shouldRemoveCartDetailBySaving() {
		User user = new User();
		user.setUsername("user name");
		user.setPassword("pass");

		Cart cart = new Cart();
		List<Product> products = productService.getAllProducts();
		CartDetail cartDetail1 = new CartDetail();
		cartDetail1.setProduct(products.get(0));
		cart.addCartDetail(cartDetail1);
		CartDetail cartDetail2 = new CartDetail();
		cartDetail2.setProduct(products.get(1));
		cart.addCartDetail(cartDetail2);

		user.setCart(cart);

		User saved = userService.save(user);
		assertEquals(2, saved.getCart().getCartDetails().size());

		saved.getCart().removeCartDetail(saved.getCart().getCartDetails().get(0));

		saved = userService.save(saved);
		assertEquals(1, saved.getCart().getCartDetails().size());
	}
}