package ru.demi.springmvc.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.demi.springmvc.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpa")
public class ProductServiceJpaImpl implements ProductService {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public List<Product> getAllProducts() {
		return getEntityManager()
			.createQuery("from Product", Product.class)
			.getResultList();
	}

	@Override
	public Product getProductById(Integer id) {
		return getEntityManager()
			.find(Product.class, id);
	}

	@Override
	public Product saveProduct(Product product) {
		EntityManager entityManager = getEntityManager();

		entityManager.getTransaction().begin();
		Product saved = entityManager.merge(product);
		entityManager.getTransaction().commit();

		return saved;
	}

	@Override
	public void deleteProduct(Integer id) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Product.class, id));
		entityManager.getTransaction().commit();
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
