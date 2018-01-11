package ru.demi.springmvc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private Integer id;
	private String description;
	private BigDecimal price;
	private String imageUrl;

	public boolean isNew() {
		return id == null;
	}
}
