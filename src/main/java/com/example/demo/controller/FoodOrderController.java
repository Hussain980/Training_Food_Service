/**
 * 
 */
package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FoodOrderDto;
import com.example.demo.entity.FoodOrder;
import com.example.demo.service.FoodOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * @author mohd.hussain
 *
 */
@Api(value = "FoodOrderController", tags = { "FoodOrder Controller" })
@SwaggerDefinition(tags = {
		@Tag(name = "FoodOrder Controller", description = "FoodOrder Controller contains some end points related to Food Order operation") })
@RestController
public class FoodOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(FoodOrderController.class);
	
	@Autowired
	FoodOrderService orderService;

	@ApiOperation(value = "Place Order")
	@PostMapping("/createOrder")
	public String createOrder(@Valid @RequestBody FoodOrderDto orderdto) {
		logger.info("inside create order method {}  {}");
		return orderService.createOrder(orderdto);
	}
	
	@ApiOperation(value = "Retrieve Order Details based on the date")
	@GetMapping("/orderHistory")
	public List<FoodOrder> getOrderList(@RequestParam String input){
		logger.info("inside getOrderList method {}  {}");
		LocalDate currentDate = LocalDate.now();
		if(input.equalsIgnoreCase("week")) {
			LocalDate returnedDate = currentDate.minusWeeks(1);
			return orderService.getOrderDetails(currentDate,returnedDate);
		}else if(input.equalsIgnoreCase("month")) {
			LocalDate returnedDate = currentDate.minusMonths(1);
			return orderService.getOrderDetails(currentDate,returnedDate);
		}
		return null;
		
	}
	
	
	@ApiOperation(value = "Retrieve all the orders")
	@GetMapping("/getAllOrders")
	public List<FoodOrder> getOrderList(){
		logger.info("inside create order method {}  {}");
		return orderService.getAll();
	}
	
	
}
