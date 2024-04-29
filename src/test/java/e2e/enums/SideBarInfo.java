package e2e.enums;

import lombok.Getter;

@Getter
public enum SideBarInfo {
    USERPROFILE("/userprofile"),
    DRAFT("/drift"),
    USERS("/users"),
    LOGIN("/login");

    public final String value;

    SideBarInfo(String value) {
        this.value = value;
    }
}
