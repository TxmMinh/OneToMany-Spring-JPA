package com.example.onetomanyrestfulapi.service;

import com.example.onetomanyrestfulapi.entity.Cart;

import java.util.List;

public interface CartService {
    Cart save(Cart cart);
    Cart findById(Long id);

    List<Cart> findAll();

    void remove(Long id);

    void deleteAll();
}
