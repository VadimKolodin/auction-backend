package ru.stud.auc.common.enums;


import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

@Getter
public enum ClientRole {
    ADMIN("Админ"),
    SELLER("Продавец"),
    CLIENT("Покупатель");

    private final String description;



    ClientRole(String description) {this.description = description;}
}
