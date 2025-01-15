package com.dw.dynamic.repository;

import com.dw.dynamic.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
