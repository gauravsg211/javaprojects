package com.example.Ecommerce.repository;
import com.example.Ecommerce.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.Ecommerce.dao.OrderResponse;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	   @Query("SELECT new com.example.Ecommerce.dao.OrderResponse(c.name, p.productName,p.qty) FROM Customer c JOIN c.products p")
	    public List<OrderResponse> getJoinInformation();
	}