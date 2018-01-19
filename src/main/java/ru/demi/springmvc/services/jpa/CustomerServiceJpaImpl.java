package ru.demi.springmvc.services.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demi.springmvc.models.Customer;
import ru.demi.springmvc.services.CustomerService;

import java.util.List;

@Service
public class CustomerServiceJpaImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAll() {
		return Lists.newArrayList(customerRepository.findAll());
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer save(Customer product) {
		return customerRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		customerRepository.delete(id);
	}
}
