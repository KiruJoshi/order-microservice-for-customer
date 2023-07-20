package com.example.order.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.config.IdNotFoundException;
import com.example.order.entity.Orders;
import com.example.order.repo.OrderRepository;
import com.example.order.response.OrderResponse;
import com.example.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	
 /*
  * getting orders details based on customer id
  */
	@GetMapping("/orders/{customer_id}")
	public OrderResponse getOrderDetails(@PathVariable int customer_id)  {
		 OrderResponse ordersById = orderService.getOrdersById(customer_id);
		return ordersById;

	}

	/*
	 * fetching all orders
	 */
	//pageNumber will start from 0 but we hv changed from 1
	@GetMapping("/getAllOrders/{customer_id}")
	public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable int customer_id,@RequestParam int pageNumber
			,@RequestParam int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		List<OrderResponse> allOrdersInfo = orderService.getAllOrdersInfo(customer_id,pageRequest);
		return ResponseEntity.status(HttpStatus.OK).body(allOrdersInfo);

	}
	/*
	 * saving new orders in db
	 */
	@PostMapping("/saveOrders")
	public ResponseEntity<OrderResponse> addOrders(@RequestBody OrderResponse orders) {
		OrderResponse addOrders = orderService.addOrders(orders);
		return ResponseEntity.status(HttpStatus.OK).body(addOrders);
	}

	/*
	 * updating existing order
	 */
	@PutMapping("/updateOrders/{id}")
	public ResponseEntity<OrderResponse> updateOrders(@PathVariable int id, @RequestBody OrderResponse orders) {
		OrderResponse updateOrder = orderService.updateOrder(id, orders);
		return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
	}

	/*
	 * deleting order based on order id
	 */
	@DeleteMapping("/deleteOrder/{id}")
	public void deleteOrders(@PathVariable int id) {
		orderService.deleteOrder(id);
	}
	

	/*
	 * deleting order based on customer id
	 */
	// select * from `agiliad-project`.orders where customer_id=7
	@DeleteMapping("/deleteOrderTest/{customer_id}")
	public void deleteOrdersForController(@PathVariable int customer_id) {
		Orders findOrdersByCustomerId = orderRepository.findOrdersByCustomerId(customer_id);
		System.out.println("tetsing "+customer_id);
		orderRepository.deleteById(findOrdersByCustomerId.getId());


	}
	
	@GetMapping("/test123/{id}")
	public Orders getOrderDetailsTest(@PathVariable int id)  {
		Optional<Orders> findById = orderRepository.findById(id);
		return findById.get();

	}
} 
