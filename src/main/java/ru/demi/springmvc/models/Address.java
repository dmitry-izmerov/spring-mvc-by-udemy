package ru.demi.springmvc.models;


import lombok.Data;

@Data
public class Address {
	private String region;
	private String city;
	private String street;
	private String house;
	private String flat;
	private String postalCode;
}
