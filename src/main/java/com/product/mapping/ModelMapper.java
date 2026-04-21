package com.product.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.product.dto.AddProductDto;
import com.product.dto.ProductResponseDto;
import com.product.entity.Product;

@Component
public class ModelMapper {
	public Product getProductFromAddProductDtoMapper(AddProductDto dto) {
		Product product = Product.builder()
				.name(dto.getName())
				.description(dto.getDescription())
				.brand(dto.getBrand())
				.catagory(dto.getCatagory())
				.stock(dto.getStock())
				.price(dto.getPrice())
				.isActive(true)
				.createdAt(LocalDateTime.now())
				.updateAt(LocalDateTime.now()).build();
		return product;
	}
	
	public ProductResponseDto entityToProductResponseDtoMapper(Product product) {
		return ProductResponseDto.builder()
				.name(product.getName())
				.brand(product.getBrand())
				.description(product.getDescription())
				.catagory(product.getCatagory())
				.price(product.getPrice())
				.build();
	}

}
