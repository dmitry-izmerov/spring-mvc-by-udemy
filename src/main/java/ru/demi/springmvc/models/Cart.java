package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends AbstractEntity {
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
