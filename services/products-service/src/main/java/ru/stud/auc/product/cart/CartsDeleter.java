package ru.stud.auc.product.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.cart.CartsRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartsDeleter {

    private final CartsRepository cartsRepository;

    @Transactional
    public void deleteFromCart(UUID productId, UUID userId) {
        int result = cartsRepository.deleteFromCart(productId, userId);
        if (result < 1) {
            throw new NotFoundException(StringConsts.Cart.NOT_FOUND);
        }
    }

    @Transactional
    public void deleteAllFromCart(UUID userId) {
        cartsRepository.deleteAllFromCart(userId);
    }

}
