package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class CartDetail extends AbstractEntity {
	@ManyToOne
	private Cart cart;
	@OneToOne
	private Product product;
}
