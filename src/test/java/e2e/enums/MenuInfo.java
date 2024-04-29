package e2e.enums;

import lombok.Getter;

@Getter
public enum MenuInfo {
    NEWSFEED("/homeblog"),
    MYDRAFTS("/draft");

    public final String value;

    MenuInfo(String value) {
        this.value = value;
    }
}