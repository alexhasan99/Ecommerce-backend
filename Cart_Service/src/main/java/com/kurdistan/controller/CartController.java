package com.kurdistan.controller;

import com.kurdistan.dto.CartDTO;
import com.kurdistan.service.interfaces.CartService;
import com.kurdistan.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final JwtUtils jwtUtils;

    public CartController(CartService cartService, JwtUtils jwtUtils) {
        this.cartService = cartService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtils.getUserIdFromToken(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addItemToCart(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String productId,
            @RequestParam int quantity,
            @RequestParam double unitPrice) {
        String userId = jwtUtils.getUserIdFromToken(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(cartService.addItemToCart(userId, productId, quantity, unitPrice));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeItemFromCart(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String productId) {
        String userId = jwtUtils.getUserIdFromToken(authHeader.replace("Bearer ", ""));
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }
}
