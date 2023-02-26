package ru.stud.auc.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;

    private String description;

    private byte[] image;

    private Tag tag;

    private SubTag subTag;

    private Integer cost;
}
