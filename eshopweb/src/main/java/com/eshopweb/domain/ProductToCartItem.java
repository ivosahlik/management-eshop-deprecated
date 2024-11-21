package com.eshopweb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Data
@Entity
public class ProductToCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name="cart_item_id")
	private CartItem cartItem;


}
