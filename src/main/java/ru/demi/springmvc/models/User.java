package ru.demi.springmvc.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Version
	private Integer version;
	private String username;
	@Transient
	private String password;
	private String encryptedPassword;
	private Boolean enabled = true;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "user")
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private Cart cart;

	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}
}
