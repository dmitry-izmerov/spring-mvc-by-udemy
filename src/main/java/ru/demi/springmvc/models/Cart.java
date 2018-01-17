package ru.demi.springmvc.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Data
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Integer id;
	@Version
	private Integer version;
	@OneToOne
	private User user;
}
