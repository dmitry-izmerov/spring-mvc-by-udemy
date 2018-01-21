package ru.demi.springmvc.models;

import lombok.Data;
import ru.demi.springmvc.models.security.Role;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

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

	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@ManyToMany
	private List<Role> roles = new ArrayList<>();

	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}

	public void addRole(Role role) {
		if (!roles.contains(role)) {
			roles.add(role);
		}

		if (!role.getUsers().contains(this)) {
			role.getUsers().add(this);
		}
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
}
