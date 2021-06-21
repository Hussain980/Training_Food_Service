/**
 * 
 */
package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FoodOrderDto;
import com.example.demo.entity.FoodOrder;

/**
 * @author mohd.hussain
 *
 */

@Service
public interface FoodOrderService {

	String createOrder(FoodOrderDto order);

	List<FoodOrder> getOrderDetails(LocalDate currentDate, LocalDate returnedDate);

	List<FoodOrder> getAll();

}
