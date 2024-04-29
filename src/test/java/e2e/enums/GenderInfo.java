package e2e.enums;


import lombok.Getter;

@Getter
public enum GenderInfo {
    MALE("MALE"),
    FEMALE("FEMALE");

    public final String value;

    GenderInfo(String value) {
        this.value = value;
    }
}