package ru.demi.springmvc.services.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.demi.springmvc.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
