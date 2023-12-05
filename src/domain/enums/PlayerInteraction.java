package domain.enums;

public enum PlayerInteraction {
    HIT("Hit"),
    STAND("Stand");

    private final String value;

    PlayerInteraction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
