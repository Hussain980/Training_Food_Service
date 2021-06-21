/**
 * 
 */
package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mohd.hussain
 *
 */
@FeignClient(url ="http://localhost:9098" , value="VendorService")
public interface VendorClient {

	@GetMapping("/searchItem")
	public Vendors getItems(@RequestParam String item , @RequestParam String vendor);
	
	@PutMapping("/updateQuantity")
	public Void updateQuantity(@RequestBody Vendors vendors);
}
