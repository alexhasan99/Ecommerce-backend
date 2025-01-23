package com.kurdistan.service.interfaces;

import com.kurdistan.dto.CartDTO;

public interface CartService {
    CartDTO getCartByUserId(String userId);

    CartDTO addItemToCart(String userId, String productId, int quantity, double unitPrice);

    CartDTO removeItemFromCart(String userId, String productId);
}
