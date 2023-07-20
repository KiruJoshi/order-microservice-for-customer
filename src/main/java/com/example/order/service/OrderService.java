package com.example.order.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.example.order.config.IdNotFoundException;
import com.example.order.response.OrderResponse;

public interface OrderService {
	
	public  OrderResponse  getOrdersById(int customer_id) throws IdNotFoundException;
	public List<OrderResponse> getAllOrdersInfo(int customer_id,PageRequest pageable) ;
	
	public OrderResponse addOrders(OrderResponse orderResponse) ;
	
	public OrderResponse updateOrder(int id, OrderResponse orderResponse);
	public void deleteOrder(int id);
}
