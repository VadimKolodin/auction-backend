package ru.stud.auc.flowdata.product.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.OrderStatus;
import ru.stud.auc.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.UUID;
import ru.stud.auc.consts.StringConsts;

@Component
@RequiredArgsConstructor
public class OrderUpdater {

    private final OrderRepository orderRepository;

    @Transactional
    public void setStatus(UUID orderId, OrderStatus status){
        int result = orderRepository.setStatus(orderId, status);
        if (result < 1){
            throw new NotFoundException(StringConsts.Order.NOT_FOUND);
        }
    }
}
