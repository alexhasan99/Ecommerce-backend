package com.kurdistan.service.impl;

import com.kurdistan.db.dao.CartDao;
import com.kurdistan.dto.CartDTO;
import com.kurdistan.mapper.CartMapper;
import com.kurdistan.model.Cart;
import com.kurdistan.model.CartItem;
import com.kurdistan.service.interfaces.CartService;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public CartDTO getCartByUserId(String userId) {
        Cart cart = cartDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        return CartMapper.INSTANCE.cartToCartDTO(cart);
    }

    @Override
    public CartDTO addItemToCart(String userId, String productId, int quantity, double unitPrice) {
        Cart cart = cartDao.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return newCart;
        });

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setUnitPrice(unitPrice);

        cart.getItems().add(item);
        cart.setTotalPrice(cart.getTotalPrice() + (quantity * unitPrice));

        return CartMapper.INSTANCE.cartToCartDTO(cartDao.save(cart));
    }

    @Override
    public CartDTO removeItemFromCart(String userId, String productId) {
        Cart cart = cartDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        cart.getItems().removeIf(item -> item.getProductId().equals(productId));

        cart.setTotalPrice(cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice()).sum());

        return CartMapper.INSTANCE.cartToCartDTO(cartDao.save(cart));
    }
}
