package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description;
	private BigDecimal price;
	private String imageUrl;

	@Version
	private Integer version;

	public Product(Integer id, String description, BigDecimal price, String imageUrl) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public boolean isNew() {
		return id == null;
	}
}
