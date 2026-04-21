package com.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDto {
	@NotBlank(message = "Product name can't be null or empty")
	@Size(min = 3, max = 20, message = "Name length should be between 3 and 20")
	private String name;

	@NotBlank(message = "Product description can't be null or empty")
	@Size(min = 10, max = 100, message = "Name description should be between 10 and 100")
	private String description;

	@NotBlank(message = "Product brand can't be null or empty")
	private String brand;

	@NotBlank(message = "Product catagory can't be null or empty")
	private String catagory;

	@PositiveOrZero(message = "Product stock can't be in minus")
	private Integer stock;

	@PositiveOrZero(message = "Product price can't be in minus")
	private BigDecimal price;

}
