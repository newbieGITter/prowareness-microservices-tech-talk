package com.prowareness.example.controller;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowareness.example.domain.Customer;
import com.prowareness.example.domain.Order;
import com.prowareness.example.domain.Product;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	private static long id = 1;
	
	@Inject
	private CustomerController customerController;
	
	@Inject
	private ProductController productController;

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
	public Order submitOrder(@RequestParam("idCustomer") long idCustomer, @RequestParam("idProduct") long idProduct,
			@RequestParam("amount") long amount) {
		LOGGER.debug("Received request to create an order");

		Order order = new Order();
		Customer customer = customerController.getCustomerById(idCustomer);
		Product product = productController.getProductById(idProduct);

		order.setProduct(product);
		order.setCustomer(customer);
		order.setId(id);
		order.setAmount(amount);
		order.setDateOrder(new Date());
		id++;
		return order;
	}

}
