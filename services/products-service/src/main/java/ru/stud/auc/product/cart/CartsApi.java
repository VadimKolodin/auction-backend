package ru.stud.auc.product.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.product.cart.model.AddCartDto;
import ru.stud.auc.product.cart.model.CartRequestDto;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

import static ru.stud.auc.product.cart.CartsApi.CARTS_API_PATH;

@RequestMapping(CARTS_API_PATH)
@Tag(name = "Методы для работы с корзиной пользователя", description = CARTS_API_PATH)
public interface CartsApi {
    String CARTS_API_PATH = "/api/cart";

    @GetMapping
    @Operation(summary = "Получение товаров в корзине")
    CartRequestDto getCart();

    @PostMapping
    @Operation(summary = "Добавление товара в корзину")
    void add(@RequestBody AddCartDto addCartDto);

    @PutMapping("/product/{productId}")
    @Operation(summary = "Изменение количества товара в корзине")
    void setAmount(@PathVariable UUID productId, @Min(1) int amount);

    @DeleteMapping("/product/{productId}")
    @Operation(summary = "Удаление товара из корзины")
    void remove(@PathVariable UUID productId);

    @DeleteMapping
    @Operation(summary = "Очистить корзину")
    void clear();

}
