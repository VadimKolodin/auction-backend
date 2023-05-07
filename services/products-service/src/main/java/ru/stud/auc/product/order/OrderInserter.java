package ru.stud.auc.product.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.OrderStatus;
import ru.stud.auc.flowdata.product.order.OrderEntity;
import ru.stud.auc.product.order.model.OrderDto;
import ru.stud.auc.flowdata.product.order.OrderRepository;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderInserter {
    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    public OrderDto createOrder
            (UUID auctionId, UUID productId, UUID clientId,
             UUID sellerId, int price,
             OrderStatus status){

        OrderEntity order = new OrderEntity();
        order.setId(UUID.randomUUID());
        order.setAuctionId(auctionId);
        order.setProductId(productId);
        order.setClientId(clientId);
        order.setSellerId(sellerId);
        order.setPrice(price);
        order.setStatus(status);

        orderRepository.persist(order);

        return orderMapper.toSimpleDto(order);
    }

}
