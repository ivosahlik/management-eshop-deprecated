package com.eshopweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private BigDecimal GrandTotal;
	private BigDecimal grandWidthTotal = BigDecimal.ZERO;
	private BigDecimal sumTotalOrderWithShipping = BigDecimal.ZERO;
	private BigDecimal shipping = BigDecimal.ZERO;
	private BigDecimal GrandTotalVat = BigDecimal.ZERO;
	private BigDecimal sumTotalOrderWithShippingAndIncludeVat = BigDecimal.ZERO;


	@OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItem> cartItemList;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getGrandTotal() {
		return GrandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		GrandTotal = grandTotal;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getGrandWidthTotal() {
		return grandWidthTotal;
	}

	public void setGrandWidthTotal(BigDecimal grandWidthTotal) {
		this.grandWidthTotal = grandWidthTotal;
	}

	public BigDecimal getSumTotalOrderWithShipping() {
		return sumTotalOrderWithShipping;
	}

	public void setSumTotalOrderWithShipping(BigDecimal sumTotalOrderWithShipping) {
		this.sumTotalOrderWithShipping = sumTotalOrderWithShipping;
	}

	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public BigDecimal getGrandTotalVat() {
		return GrandTotalVat;
	}

	public void setGrandTotalVat(BigDecimal grandTotalVat) {
		GrandTotalVat = grandTotalVat;
	}

	public BigDecimal getSumTotalOrderWithShippingAndIncludeVat() {
		return sumTotalOrderWithShippingAndIncludeVat;
	}

	public void setSumTotalOrderWithShippingAndIncludeVat(BigDecimal sumTotalOrderWithShippingAndIncludeVat) {
		this.sumTotalOrderWithShippingAndIncludeVat = sumTotalOrderWithShippingAndIncludeVat;
	}
}
