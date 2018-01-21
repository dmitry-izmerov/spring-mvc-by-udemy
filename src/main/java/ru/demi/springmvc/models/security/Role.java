package ru.demi.springmvc.models.security;

import lombok.Getter;
import lombok.Setter;
import ru.demi.springmvc.models.AbstractEntity;
import ru.demi.springmvc.models.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Role extends AbstractEntity {

	private String role;

	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	@ManyToMany
	private List<User> users = new ArrayList<>();

	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}

		if (!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}

	public void removeUser(User user) {
		users.remove(user);
		user.getRoles().remove(this);
	}
}
