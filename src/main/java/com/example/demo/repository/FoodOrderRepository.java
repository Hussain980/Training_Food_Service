/**
 * 
 */
package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FoodOrder;

/**
 * @author mohd.hussain
 *
 */
@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

	/*
	 * @Query(value =
	 * "SELECT * from foodorderingapp.food_order where order_date between :currentDate and :returnedDate"
	 * ,nativeQuery = true) List<FoodOrder> getAllByDate(@Param("currentDate")
	 * LocalDate currentDate,@Param("returnedDate") LocalDate returnedDate);
	 */

	/*
	 * @Query("from FoodOrder o where  o.orderDate BETWEEN :currentDate AND :returnedDate"
	 * ) List<FoodOrder> getAllByDate(LocalDate currentDate, LocalDate
	 * returnedDate);
	 */
	
	List<FoodOrder> findByOrderDateBetween(LocalDate currentDate, LocalDate returnedDate);

}
