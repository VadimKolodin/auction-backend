package ru.stud.auc.product.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.flowdata.product.cart.model.CartProductView;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.cart.model.CartRequestDto;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartsService {

    private final CartsGetter cartsGetter;

    private final CartsUpdater cartsUpdater;

    private final CartsDeleter cartsDeleter;

    private final CartsInserter cartsInserter;

    private final ProductsGetter productsGetter;

    public CartRequestDto getClientCart() {
        UUID userId = AuthenticationUtils.getUserId();
        List<CartProductView> products = cartsGetter.getCart(userId);
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setUserId(userId);
        cartRequestDto.setProducts(products);
        int totalPrice = products.stream().map(p -> p.getPrice()*p.getAmount()).mapToInt(i -> i).sum();
        cartRequestDto.setTotalPrice(totalPrice);
        return cartRequestDto;
    }

    public void addToCart(UUID productId) {
        productsGetter.checkExistsAndNotDeleted(productId);
        UUID userId = AuthenticationUtils.getUserId();
        cartsInserter.addCart(productId, userId);
    }

    public void setCartAmount(UUID productId, int amount) {
        cartsUpdater.setAmount(productId, AuthenticationUtils.getUserId(), amount);
    }


    public void deleteFromCart(UUID productId) {
        cartsDeleter.deleteFromCart(productId, AuthenticationUtils.getUserId());
    }

    public void clearCart() {
        cartsDeleter.deleteAllFromCart(AuthenticationUtils.getUserId());
    }
}
