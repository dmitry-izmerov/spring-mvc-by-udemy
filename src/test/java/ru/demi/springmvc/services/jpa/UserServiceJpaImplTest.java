package ru.demi.springmvc.services.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.demi.springmvc.config.JpaIntegrationConfig;
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
}