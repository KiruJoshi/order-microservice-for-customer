package com.example.order.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order.entity.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {


	@Query(nativeQuery = true, value="SELECT * FROM `agiliad-project`.orders where customer_id=:customer_id")
	public Orders findOrdersByCustomerId(int customer_id);
	

	@Query(nativeQuery = true, value="SELECT * FROM `agiliad-project`.orders where customer_id=:customer_id")
	public List<Orders> findAllOrdersByCustomerId(int customer_id,PageRequest pageable);
	
	
	
}
