/**
 * 
 */
package com.example.demo.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FoodOrderDto;
import com.example.demo.entity.FoodOrder;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.feign.VendorClient;
import com.example.demo.feign.Vendors;
import com.example.demo.repository.FoodOrderRepository;
import com.example.demo.service.FoodOrderService;

/**
 * @author mohd.hussain
 *
 */
@Service
public class FoodOrderServiceImpl implements FoodOrderService {

	private static final Logger logger = LoggerFactory.getLogger(FoodOrderServiceImpl.class);

	@Autowired
	FoodOrderRepository orderRepository;

	@Autowired
	VendorClient vendorClient;
	

	@Override
	public String createOrder(FoodOrderDto orderdto) {
		logger.info("inside createOrder method {}  {}  {} ");
		Vendors vendors = vendorClient.getItems(orderdto.getItem(), orderdto.getVendor());
		if (vendors == null) {
			throw new ItemNotFoundException("item is not available");
		} else if (orderdto.getQuantity() > vendors.getQuantity()) {
			throw new ItemNotFoundException("quantity is not available");
		}
		vendors.setQuantity(vendors.getQuantity() - orderdto.getQuantity());
		logger.info("going to update quantity in vendor table {}  {}  {} ");
		vendorClient.updateQuantity(vendors);
		FoodOrder order = new FoodOrder();
		order.setItem(orderdto.getItem());
		order.setQuantity(orderdto.getQuantity());
		order.setVendor(orderdto.getVendor());
		order.setOrderDate(LocalDate.now());
		double itemPrice = vendors.getPrice();
		int itemQuantity = order.getQuantity();
		order.setPrice(itemQuantity * itemPrice);
		orderRepository.save(order);
		return "Order placed successfully";
	}

	@Override
	public List<FoodOrder> getOrderDetails(LocalDate currentDate, LocalDate returnedDate) {
		logger.info("inside getOrderDetails method {}  {}  {} ");
		List<FoodOrder> list =  orderRepository.findByOrderDateBetween(currentDate,returnedDate);
		return list;		
	}

	@Override
	public List<FoodOrder> getAll() {
		logger.info("inside getAll method {}  {}  {} ");
		List<FoodOrder> list =  orderRepository.findAll();
		if(list.isEmpty()) {
			throw new ItemNotFoundException("Orders are not present , First place some orders");
		}
		return list;
	}

}
