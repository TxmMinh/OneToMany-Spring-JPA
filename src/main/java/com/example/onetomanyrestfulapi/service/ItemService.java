package com.example.onetomanyrestfulapi.service;

import com.example.onetomanyrestfulapi.entity.Cart;
import com.example.onetomanyrestfulapi.entity.Item;

import java.util.List;

public interface ItemService {
    Item save(Item item);
    Item findById(Long id);

    List<Item> findAll();

    void remove(Long id);

    void deleteAll();
}
