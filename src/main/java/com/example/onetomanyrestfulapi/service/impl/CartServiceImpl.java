package com.example.onetomanyrestfulapi.service.impl;

import com.example.onetomanyrestfulapi.entity.Cart;
import com.example.onetomanyrestfulapi.repository.CartRepository;
import com.example.onetomanyrestfulapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }


}

