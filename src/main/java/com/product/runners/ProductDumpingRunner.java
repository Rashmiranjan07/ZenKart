package com.product.runners;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.product.entity.Product;
import com.product.enums.ProductTypes;
import com.product.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDumpingRunner implements CommandLineRunner {

	private final ProductRepository productRepo;

	private final Random random;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		ProductTypes[] catagories = ProductTypes.values();
		long presentNumber = productRepo.count();
		long required = 200 - presentNumber;
		List<Product> products = new ArrayList<Product>();
		while (required > 0) {
			Product product = Product.builder().name(UUID.randomUUID().toString().substring(0, 10))
					.description(UUID.randomUUID().toString().substring(0, 23))
					.brand(UUID.randomUUID().toString().substring(0, 10))
					.catagory(catagories[random.nextInt(0, catagories.length)].name()).stock(random.nextInt(1, 1000))
					.price(new BigDecimal(random.nextDouble(1, 100000))).isActive(true).createdAt(LocalDateTime.now())
					.updateAt(LocalDateTime.now()).build();
			products.add(product);
			required--;

		}

		productRepo.saveAll(products);

	}

}
