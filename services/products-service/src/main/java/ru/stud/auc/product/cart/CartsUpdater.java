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
public class CartsUpdater {

    private final CartsRepository cartsRepository;

    @Transactional
    public void setAmount(UUID productId, UUID userId, int amount) {
        int result = cartsRepository.setAmount(productId, userId, amount);
        if (result < 1) {
            throw new NotFoundException(StringConsts.Cart.NOT_FOUND);
        }
    }
}
