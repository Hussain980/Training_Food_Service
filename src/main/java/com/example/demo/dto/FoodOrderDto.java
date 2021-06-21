/**
 * 
 */
package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mohd.hussain
 *
 */
public class FoodOrderDto {

	@NotEmpty(message = "vendor name is required")
	private String vendor;

	@NotEmpty(message = "item is required")
	private String item;

	@NotNull(message = "quantity is required")
	@Min(1)
	private int quantity;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
