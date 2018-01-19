package ru.demi.springmvc.services;

import java.util.List;

public interface CrudService<T> {
	List<T> getAll();

	T getById(Integer id);

	T save(T product);

	void delete(Integer id);
}
