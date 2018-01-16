package ru.demi.springmvc.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.demi.springmvc.models.User;
import ru.demi.springmvc.services.UserService;
import ru.demi.springmvc.services.security.EncryptionService;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpa")
public class UserServiceJpaImpl extends AbstractJpaService implements UserService {

	@Autowired
	private EncryptionService encryptionService;

	@Override
	public List<User> getAll() {
		return getEntityManager()
			.createQuery("from User", User.class)
			.getResultList();
	}

	@Override
	public User getById(Integer id) {
		return getEntityManager()
			.find(User.class, id);
	}

	@Override
	public User save(User user) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();

		String password = user.getPassword();
		if (password != null) {
			user.setEncryptedPassword(encryptionService.encryptPassword(password));
		}

		User saved = entityManager.merge(user);
		entityManager.getTransaction().commit();

		return saved;
	}

	@Override
	public void delete(Integer id) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(User.class, id));
		entityManager.getTransaction().commit();
	}
}
