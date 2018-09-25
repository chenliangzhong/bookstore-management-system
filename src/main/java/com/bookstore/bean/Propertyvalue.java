package com.bookstore.bean;

/**
 * Created by Administrator on 2018/9/21.
 */
public class Propertyvalue {
	private Long id;
	private String value;
	private Long productId;
	private Long propertyId;

	public void setId(Long id) {
		this.id = id;
		
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getPropertyId() {
		return propertyId;
	}
}
