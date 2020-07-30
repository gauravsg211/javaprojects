package com.example.Ecommerce.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderResponse {

    private String name;
    private String productName;
    private int qty;
    
    public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getName() {
  		return name;
  	}

  	public void setName(String name) {
  		this.name = name;
  	}

  	public String getProductName() {
  		return productName;
  	}

  	public void setProductName(String productName) {
  		this.productName = productName;
  	}

  	public int getPrice() {
  		return price;
  	}

  	public void setPrice(int price) {
  		this.price = price;
  	}

    public OrderResponse(String name, String productName,int qty) {
        this.name = name;
        this.productName = productName;
        this.qty=qty;
    }

    private int price;
    
    
}
