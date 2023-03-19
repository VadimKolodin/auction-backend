package ru.stud.auc.product.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.cart.CartEntity;
import ru.stud.auc.flowdata.product.cart.CartEntityId;
import ru.stud.auc.flowdata.product.cart.CartsRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartsInserter {

    private final CartsRepository cartsRepository;

    @Transactional
    public void addCart(UUID productId, UUID userId) {
        CartEntity cart = new CartEntity();
        CartEntityId cartEntityId = new CartEntityId();
        cartEntityId.setProductId(productId);
        cartEntityId.setClientId(userId);
        cart.setId(cartEntityId);
        cart.setAmount(1);
        cartsRepository.persist(cart);

    }
}
