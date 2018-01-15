package ru.demi.springmvc.services.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.ProductService;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpa")
public class ProductServiceJpaImpl extends AbstractJpaService implements ProductService {

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
}
