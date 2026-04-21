package com.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;

	// product
	private String name;
	private String description;
	private String brand;
	private String catagory;
	private Integer stock;
	private BigDecimal price;

	// Active status
	private Boolean isActive = true;

	// Tracking
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;

}
