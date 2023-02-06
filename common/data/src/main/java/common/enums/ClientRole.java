package common.enums;


import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

public enum ClientRole {
    ADMIN("Админ"),
    SELLER("Продавец"),
    USER("Покупатель");

    private final String description;


    ClientRole(String description) {this.description = description;}
}
