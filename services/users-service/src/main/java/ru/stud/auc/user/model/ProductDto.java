package ru.stud.auc.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    @Pattern(regexp = "[^?!.,\\\\\"/';:&%$#@^*()-_]+")
    private String name;

    private String description;

    @NotBlank
    private Tag tag;

    private SubTag subTag;

    @Min(0)
    @NotBlank
    private Integer cost;
}
