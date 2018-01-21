package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {
	private String description;
	private BigDecimal price;
	private String imageUrl;

	public Product(Integer id, String description, BigDecimal price, String imageUrl) {
		super(id);
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
	}
}
