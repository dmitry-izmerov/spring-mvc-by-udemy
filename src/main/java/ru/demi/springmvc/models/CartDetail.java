package ru.demi.springmvc.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Data
@Entity
public class CartDetail {
	@Id
	@GeneratedValue
	private Integer id;
	@Version
	private Integer version;
	@ManyToOne
	private Cart cart;
	@OneToOne
	private Product product;
}
