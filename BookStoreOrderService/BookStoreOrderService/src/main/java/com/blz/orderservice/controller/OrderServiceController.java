package com.blz.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blz.orderservice.model.OrderBookModel;
import com.blz.orderservice.service.IOrderService;
import com.blz.orderservice.util.OrderResponse;

/*
 * Purpose : OrderService Controller to process OrderService Data API's
 * @author : Sanddep Varma Dubyala
 * @version : 1.0
 * @date : 22-09-2022
 */

@RestController
@RequestMapping("/orderservie")
public class OrderServiceController {
	@Autowired
	IOrderService orderService;
	
	/*
     *@Purpose : Api place order of book
     *@Param : cartId ,token and addressId
     */
	
	@PostMapping("/placeorder")
	public ResponseEntity<OrderResponse> placeOrder(@RequestParam Long cartId, @RequestParam Long addressId, @RequestHeader String token){
		OrderBookModel order = orderService.placeOrder(cartId, addressId, token);
		OrderResponse orderResponse = new OrderResponse(200, "Successfull", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api cancel order of book
     *@Param : orderId ,token 
     */
	
	@DeleteMapping("/cancelorder/{orderId}")
	public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long orderId,@RequestHeader String token){
		OrderBookModel order = orderService.cancelOrder(orderId, token);
		OrderResponse orderResponse = new OrderResponse(200, "Successfull", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api getall orders
     *@Param : token
     */
	
	@GetMapping("/getallorders")
	public ResponseEntity<OrderResponse> getAllOrders(@RequestHeader String token){
		List<OrderBookModel> order = orderService.getAllOrders(token);
		OrderResponse orderResponse = new OrderResponse(200, "Successfull", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
	
	/*
     *@Purpose : Api get all orders of user
     *@Param : token
     */
	
	@GetMapping("/fetchuserorders")
	public ResponseEntity<OrderResponse> getUserOrders(@RequestHeader String token){
		List<OrderBookModel> order = orderService.getUserOrders(token);
		OrderResponse orderResponse = new OrderResponse(200, "Successfull", order);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
	
}
