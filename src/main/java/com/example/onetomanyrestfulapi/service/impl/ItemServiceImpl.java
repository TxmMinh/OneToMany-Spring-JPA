package com.example.onetomanyrestfulapi.service.impl;

import com.example.onetomanyrestfulapi.entity.Item;
import com.example.onetomanyrestfulapi.repository.ItemRepository;
import com.example.onetomanyrestfulapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
