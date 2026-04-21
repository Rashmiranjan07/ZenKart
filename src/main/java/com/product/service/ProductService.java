package com.product.service;

import java.util.List;

import com.product.dto.AddProductDto;
import com.product.entity.Product;

public interface ProductService {

	public String addNewProductService(AddProductDto dto);
	
	public Product getProductByIdService(Long id);
	
	public Product deleteProductByIdService(Long id);
	
	public Product updateProductByIdService(Long id,AddProductDto dto);
	
	public List<Product> getProductByCatagory(String catagory,String sorting);
	
	public List<Product> getProductByPageService(Integer pageNo,Integer pageSize,String sorting);
	public List<Product> getProductByPageService(Integer pageNo,Integer pageSize);

	List<Product> getProductByPriceRange(Double min, Double max, String sort);
	

	public String incProductStock(Long id, Integer stockAmount);
	public String decProductStock(Long id, Integer stockAmount);


}
