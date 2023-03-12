package ru.stud.auc.common.enums;

import lombok.Getter;

@Getter
public enum Tag {
    HUMAN("Обычная"),
    ELF("Эльф"),
    NEKO("Кошкодевочка"),
    OTHERS("Другое");

    private final String description;

    Tag(String description) {this.description = description;}
}
