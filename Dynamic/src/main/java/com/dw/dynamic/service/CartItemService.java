package com.dw.dynamic.service;

import com.dw.dynamic.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
}
