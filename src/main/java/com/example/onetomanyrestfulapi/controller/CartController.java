package com.example.onetomanyrestfulapi.controller;

import com.example.onetomanyrestfulapi.entity.Cart;
import com.example.onetomanyrestfulapi.entity.Item;
import com.example.onetomanyrestfulapi.service.CartService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostConstruct
    public void init() {

        Item item1 = Item.builder().name("Item 1").build();
        Item item2 = Item.builder().name("Item 2").build();
        Set<Item> items = new HashSet<Item>();
        items.add(item1);
        items.add(item2);

        Cart cart = new Cart();
        cart.setItems(items);
        item1.setCart(cart);
        item2.setCart(cart);

        cartService.save(cart); // như vậy ta sẽ lưu dữ liệu xuống 2 bảng Cart và Items
    }

    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getCartList() {
        List<Cart> carts = cartService.findAll();
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/getCart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable(name = "id") Long id) {
        Cart cartData = cartService.findById(id);
        if (cartData != null) {
            return new ResponseEntity<>(cartData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("cart/{cartId}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable(name = "cartId") Long cartId) {
        cartService.remove(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<HttpStatus> deleteAllCarts() {
        cartService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart tempCart = new Cart();
        tempCart.setItems(cart.getItems());
        return new ResponseEntity<>(cartService.save(tempCart), HttpStatus.CREATED);
    }

    /*
    @RequestBody nói với Spring Boot rằng hãy chuyển Json trong request body
    thành đối tượng Cart
    */
    @PutMapping("/cart/{cartId}")
    public ResponseEntity<Cart> editCart(@PathVariable(name = "cartId") Long cartId,
                                         @RequestBody Cart cart){
        Cart tempcart = cartService.findById(cartId);
        if (tempcart != null) {
            tempcart.setItems(cart.getItems());
            return new ResponseEntity<>(cartService.save(tempcart), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
