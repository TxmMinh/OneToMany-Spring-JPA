package com.example.onetomanyrestfulapi.controller;

import com.example.onetomanyrestfulapi.entity.Item;
import com.example.onetomanyrestfulapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getUserList() {
        List<Item> items = itemService.findAll();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/getItem/{id}")
    public ResponseEntity<Item> getItem(@PathVariable(name = "id") Long id) {
        Item itemData = itemService.findById(id);
        if (itemData != null) {
            return new ResponseEntity<>(itemData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("item/{itemId}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable(name = "itemId") Long itemId) {
        itemService.remove(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/items")
    public ResponseEntity<HttpStatus> deleteAllItems() {
        itemService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/item")
    public ResponseEntity<Item> addUser(@RequestBody Item item) {
        Item tempItem = new Item();
        tempItem.setName(item.getName());
        tempItem.setCart(item.getCart());
        return new ResponseEntity<>(itemService.save(tempItem), HttpStatus.CREATED);
    }

    /*
    @RequestBody nói với Spring Boot rằng hãy chuyển Json trong request body
    thành đối tượng Item
    */
    @PutMapping("/item/{item_id}")
    public ResponseEntity<Item> editTodo(@PathVariable(name = "item_id") Long item_id,
                                         @RequestBody Item item){
        Item tempItem = itemService.findById(item_id);
        if (tempItem != null) {
            tempItem.setName(item.getName());
            tempItem.setCart(item.getCart());
            return new ResponseEntity<>(itemService.save(tempItem), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
