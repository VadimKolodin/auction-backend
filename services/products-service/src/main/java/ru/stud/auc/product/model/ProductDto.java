package ru.stud.auc.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Tag tag;

    private SubTag subTag;

    @Min(0)
    @NotNull
    private Integer cost;

    @Min(0)
    @NotNull
    private Integer minCost;
}
