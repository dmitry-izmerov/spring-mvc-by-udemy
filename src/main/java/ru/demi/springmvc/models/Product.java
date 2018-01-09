package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
	private Integer id;
	private String description;
	private BigDecimal price;
	private String imageUrl;
}
