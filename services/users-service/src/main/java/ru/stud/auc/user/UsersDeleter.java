package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.user.UsersRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsersDeleter {
}
