package com.bookstore.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2018/9/21.
 */

@JsonIgnoreProperties(value = {"handler"})
public class PropertyValue {
	private Long id;
	private String value;
	private Long productId;
	private Long propertyId;
	private Property property;

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

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
