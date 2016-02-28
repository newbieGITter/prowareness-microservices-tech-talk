package com.prowareness.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prowareness.example.domain.ProductDetail;
import com.prowareness.example.exception.ProductNotFoundException;
import com.prowareness.example.service.ProductDetailService;
import com.prowareness.example.validator.ProductDetailValidator;

@RestController
@RequestMapping("/products")
public class ProductDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailController.class);
	private final ProductDetailService productDetailService;
	private final ProductDetailValidator validator;
	private final ObjectMapper objectMapper;

	@Autowired
	public ProductDetailController(ProductDetailService productDetailService, ProductDetailValidator validator, ObjectMapper objectMapper) {
		this.productDetailService = productDetailService;
		this.validator = validator;
		this.objectMapper = objectMapper;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ProductDetail createPrductDetail(@RequestBody final ProductDetail productDetail) {
		LOGGER.debug("Received request to create the {}", productDetail);
		return productDetailService.save(productDetail);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ProductDetail> listAllProductDetailsWithPagination(
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "count", defaultValue = "10", required = false) int count,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "sort", defaultValue = "productId", required = false) String sortProperty) {
		LOGGER.debug("Received request to list all product details");
		Page<ProductDetail> productDetailsPage = productDetailService
				.getAllProductDetail(new PageRequest(page, count, new Sort(direction, sortProperty)));
		return productDetailsPage.getContent();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable String id) {
		try {
			ProductDetail detail = productDetailService.getProductDetail(id);
			return new ResponseEntity<ProductDetail>(detail, HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity<?> update(@PathVariable String id, HttpServletRequest request) throws IOException {
        ProductDetail existing = productDetailService.getProductDetail(id);
    	if (existing == null) {
			throw new ProductNotFoundException("Product with product id: " + id + " not found");
		}
        ProductDetail updated = objectMapper.readerForUpdating(existing).readValue(request.getReader());
        
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("productId", updated.getProductId());
        propertyValues.add("productName", updated.getProductName());
        propertyValues.add("shortDescription", updated.getShortDescription());
        propertyValues.add("longDescription", updated.getLongDescription());
        propertyValues.add("inventoryId", updated.getInventoryId());
        
        DataBinder binder = new DataBinder(updated);
        binder.addValidators(validator);
        binder.bind(propertyValues);
        binder.validate();
        
        if (binder.getBindingResult().hasErrors()) {
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
        	productDetailService.save(updated);
            return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
        }
    }
	
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity<?> delete(@PathVariable String id) {
        productDetailService.deleteProductDetail(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
