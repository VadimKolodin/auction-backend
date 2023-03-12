package ru.stud.auc.common.enums;

import lombok.Getter;

@Getter
public enum SubTag {
    WITH_CLOTHES("С одеждой"),
    WITHOUT_CLOTHES("Без одежды"),
    SECRET("С подвохом");


    private final String description;

    SubTag(String description) {this.description = description;}
}
