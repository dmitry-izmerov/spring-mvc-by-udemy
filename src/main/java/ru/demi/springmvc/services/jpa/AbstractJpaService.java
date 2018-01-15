package ru.demi.springmvc.services.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AbstractJpaService {
	@PersistenceUnit
	private EntityManagerFactory emf;

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
