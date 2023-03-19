package ru.stud.auc.product.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.cart.CartsRepository;
import ru.stud.auc.flowdata.product.cart.model.CartProductView;
import ru.stud.auc.product.cart.model.CartRequestDto;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartsGetter {

    private final CartsRepository cartsRepository;

    public List<CartProductView> getCart(UUID userId) {
        return cartsRepository.getCart(userId);
    }

}
