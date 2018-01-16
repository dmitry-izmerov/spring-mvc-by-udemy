package ru.demi.springmvc.services.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.demi.springmvc.config.JpaIntegrationConfig;
import ru.demi.springmvc.models.Customer;
import ru.demi.springmvc.models.User;
import ru.demi.springmvc.services.UserService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpa"})
public class UserServiceJpaImplTest {

	@Autowired
	private UserService userService;

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
}