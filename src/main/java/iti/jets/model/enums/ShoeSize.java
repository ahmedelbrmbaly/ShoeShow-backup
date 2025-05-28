package iti.jets.model.enums;

public enum ShoeSize {
    SIZE_35(35L), SIZE_36(36L), SIZE_37(37L), SIZE_38(38L), SIZE_39(39L),
    SIZE_40(40L), SIZE_41(41L), SIZE_42(42L), SIZE_43(43L), SIZE_44(44L),
    SIZE_45(45L), SIZE_46(46L), SIZE_47(47L), SIZE_48(48L);

    private final Long value;

    ShoeSize(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static ShoeSize fromValue(Long value) {
        for (ShoeSize size : ShoeSize.values()) {
            if (size.value == value) {
                return size;
            }
        }
        throw new IllegalArgumentException("Invalid shoe size: " + value);
    }
}
