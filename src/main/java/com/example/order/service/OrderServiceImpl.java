package com.example.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.order.config.IdNotFoundException;
import com.example.order.entity.Orders;
import com.example.order.exception.BadRequestException;
import com.example.order.exception.SqlProblemException;
import com.example.order.repo.OrderRepository;
import com.example.order.response.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderResponse getOrdersById(int customer_id) throws IdNotFoundException {

		Orders findOrderByCustomerId = orderRepository.findOrdersByCustomerId(customer_id);

		if (findOrderByCustomerId.getCustomer_id() == customer_id) {
			OrderResponse orderResponse = modelMapper.map(findOrderByCustomerId, OrderResponse.class);

			return orderResponse;
		} else {
			throw new IdNotFoundException("id not found");
		}

	}

	@Override
	public List<OrderResponse> getAllOrdersInfo(int customer_id,PageRequest pageable) {
		
		List<Orders> findAllOrdersByCustomerIdTest = orderRepository.findAllOrdersByCustomerId(customer_id,pageable);
		System.out.println("Testing pagination.." + findAllOrdersByCustomerIdTest.size());
		List<OrderResponse> ordersList = findAllOrdersByCustomerIdTest.stream()
				.map(orders -> modelMapper.map(orders, OrderResponse.class)).collect(Collectors.toList());
		return ordersList;
	}

	@Override
	public OrderResponse addOrders(OrderResponse ordersResponse) {
		Orders convertOrder = modelMapper.map(ordersResponse, Orders.class);

		Orders save = orderRepository.save(convertOrder);
		return ordersResponse;
	}

	@Override
	public OrderResponse updateOrder(int id, OrderResponse orderResponse) {
		// TODO Auto-generated method stub
		Optional<Orders> findById = orderRepository.findById(id);

		if (findById.isPresent()) {

			Orders orders = modelMapper.map(orderResponse, Orders.class);
			orders.setCustomer_id(orderResponse.getCustomer_id());
			Orders save = orderRepository.save(orders);
			return orderResponse;
		} else {
			return null;
		}

	}

	@Override
	public void deleteOrder(int id) {
		Optional<Orders> findById = orderRepository.findById(id);
		if (findById.isPresent()) {
			orderRepository.deleteById(id);
		} else {
			throw new IdNotFoundException("Id is not in database");
		}

	}

}
