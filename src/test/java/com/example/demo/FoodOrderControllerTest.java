/**
 * 
 */
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.FoodOrderController;
import com.example.demo.dto.FoodOrderDto;
import com.example.demo.entity.FoodOrder;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.feign.Vendors;
import com.example.demo.service.FoodOrderService;

/**
 * @author mohd.hussain
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FoodOrderControllerTest {

	@Mock
	FoodOrderService foodOrderService;

	@InjectMocks
	FoodOrderController foodOrderController;


	static FoodOrder foodOrder1;

	static FoodOrder foodOrder2;

	static FoodOrderDto foodOrderdto;

	static Vendors vendor1;

	static Vendors vendor2;

	@BeforeAll
	public static void setup() {
		foodOrder1 = new FoodOrder();
		foodOrder2 = new FoodOrder();
		foodOrderdto = new FoodOrderDto();
		vendor1 = new Vendors();
		vendor2 = new Vendors();
		vendor1.setItem("tea");
		vendor1.setPrice(20);
		vendor1.setQuantity(2);
		vendor1.setVendor("Vendor A");
		vendor2.setItem("Coffee");
		vendor2.setPrice(25);
		vendor2.setQuantity(4);
		vendor2.setVendor("Vendor B");
		foodOrderdto.setItem("tea");
		foodOrderdto.setQuantity(1);
		foodOrderdto.setVendor("Vendor A");
		foodOrder1.setItem("tea");
		foodOrder1.setOrderDate(LocalDate.now());
		foodOrder1.setPrice(20);
		foodOrder1.setQuantity(1);
		foodOrder1.setVendor("Vendor A");
		foodOrder2.setItem("Coffee");
		foodOrder2.setOrderDate(LocalDate.now());
		foodOrder2.setPrice(50);
		foodOrder2.setQuantity(2);
		foodOrder2.setVendor("Vendor B");

	}

	@Test
	@DisplayName("Find All Orders : positive scenario")
	public void testGetOrderList() {
		List<FoodOrder> foodOrderList = new ArrayList<>();
		foodOrderList.add(foodOrder1);
		foodOrderList.add(foodOrder2);
		when(foodOrderService.getAll()).thenReturn(foodOrderList);
		List<FoodOrder> foodList = foodOrderController.getOrderList();
		assertEquals(foodOrderList, foodList);

	}

	@Test
	@DisplayName("Find All Orders : negative scenario")
	public void testGetOrderList1() {
		List<FoodOrder> foodOrderList = new ArrayList<>();
		foodOrderList.add(foodOrder1);
		foodOrderList.add(foodOrder2);
		when(foodOrderService.getAll()).thenThrow(ItemNotFoundException.class);
		assertThrows(ItemNotFoundException.class, () -> foodOrderController.getOrderList());

	}

}
