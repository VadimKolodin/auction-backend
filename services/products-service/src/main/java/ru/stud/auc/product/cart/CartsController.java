package ru.stud.auc.product.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.product.auction.model.AuctionDto;
import ru.stud.auc.product.cart.model.AddCartDto;
import ru.stud.auc.product.cart.model.CartRequestDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CartsController implements CartsApi {

    private final CartsService cartsService;

    @Override
    @HasRole("CLIENT")
    public CartRequestDto getCart() {
        return cartsService.getClientCart();
    }

    @Override
    @HasRole("CLIENT")
    public void add(AddCartDto addCartDto) {
        cartsService.addToCart(addCartDto.getProductId());
    }

    @Override
    @HasRole("CLIENT")
    public void setAmount(UUID productId, int amount) {
        cartsService.setCartAmount(productId, amount);
    }

    @Override
    @HasRole("CLIENT")
    public void remove(UUID productId) {
        cartsService.deleteFromCart(productId);
    }

    @Override
    @HasRole("CLIENT")
    public void clear() {
        cartsService.clearCart();
    }

    @Override
    @HasRole("CLIENT")
    public void buyAll() {
        //TODO: сделать (все в одной транзакции)
    }

    @Override
    @HasRole("CLIENT")
    public AuctionDto buy(UUID productId) {
        //TODO: сделать
        return null;
    }
}
