package com.example.Ecommerce.dao;

import com.example.Ecommerce.model.Customer;


public class OrderRequest {

    private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}