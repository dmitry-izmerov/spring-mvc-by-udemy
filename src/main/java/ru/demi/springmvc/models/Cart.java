package ru.demi.springmvc.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Integer id;
	@Version
	private Integer version;
	@OneToOne
	private User user;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
	private List<CartDetail> cartDetails = new ArrayList<>();

	public void addCartDetail(CartDetail cartDetail) {
		cartDetail.setCart(this);
		cartDetails.add(cartDetail);
	}

	public void removeCartDetail(CartDetail cartDetail) {
		cartDetail.setCart(null);
		cartDetails.remove(cartDetail);
	}
}
