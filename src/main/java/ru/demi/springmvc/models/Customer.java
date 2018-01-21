package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Customer extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String city;
	private String state;
	private String zip;

	@OneToOne
	private User user;

	@Embedded
	private Address billingAddress;
	@Embedded
	private Address shippingAddress;
}
