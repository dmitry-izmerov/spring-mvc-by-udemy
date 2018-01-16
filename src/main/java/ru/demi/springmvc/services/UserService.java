package ru.demi.springmvc.services;

import ru.demi.springmvc.models.User;

import java.util.List;

public interface UserService {
	List<User> getAll();
	User getById(Integer id);
	User save(User user);
	void delete(Integer id);
}
