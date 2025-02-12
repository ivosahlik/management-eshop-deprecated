package com.eshopweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@Entity
public class ShippingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ShippingAddressName;
	private String ShippingAddressStreet1;
	private String ShippingAddressStreet2;
	private String ShippingAddressCity;
	private String ShippingAddressState;
	private String ShippingAddressCountry;
	private String ShippingAddressZipcode;
	private String ShippingAddressVat;
	private String shippingAddressPhone;
	private String shippingAddressEmail;

	@OneToOne
	private Order order;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getShippingAddressName() {
		return ShippingAddressName;
	}

	public void setShippingAddressName(String shippingAddressName) {
		ShippingAddressName = shippingAddressName;
	}

	public String getShippingAddressStreet1() {
		return ShippingAddressStreet1;
	}

	public void setShippingAddressStreet1(String shippingAddressStreet1) {
		ShippingAddressStreet1 = shippingAddressStreet1;
	}

	public String getShippingAddressStreet2() {
		return ShippingAddressStreet2;
	}

	public void setShippingAddressStreet2(String shippingAddressStreet2) {
		ShippingAddressStreet2 = shippingAddressStreet2;
	}

	public String getShippingAddressCity() {
		return ShippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		ShippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressState() {
		return ShippingAddressState;
	}

	public void setShippingAddressState(String shippingAddressState) {
		ShippingAddressState = shippingAddressState;
	}

	public String getShippingAddressCountry() {
		return ShippingAddressCountry;
	}

	public void setShippingAddressCountry(String shippingAddressCountry) {
		ShippingAddressCountry = shippingAddressCountry;
	}

	public String getShippingAddressZipcode() {
		return ShippingAddressZipcode;
	}

	public void setShippingAddressZipcode(String shippingAddressZipcode) {
		ShippingAddressZipcode = shippingAddressZipcode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getShippingAddressVat() {
		return ShippingAddressVat;
	}

	public void setShippingAddressVat(String shippingAddressVat) {
		ShippingAddressVat = shippingAddressVat;
	}

	public String getShippingAddressPhone() {
		return shippingAddressPhone;
	}

	public void setShippingAddressPhone(String shippingAddressPhone) {
		this.shippingAddressPhone = shippingAddressPhone;
	}

	public String getShippingAddressEmail() {
		return shippingAddressEmail;
	}

	public void setShippingAddressEmail(String shippingAddressEmail) {
		this.shippingAddressEmail = shippingAddressEmail;
	}
}
