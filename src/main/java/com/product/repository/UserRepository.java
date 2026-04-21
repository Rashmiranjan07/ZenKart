package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
