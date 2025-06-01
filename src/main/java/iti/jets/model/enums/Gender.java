package iti.jets.model.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Gender {
    MALE,
    FEMALE;

    public static Gender parseGender(String gender) {
        try {
            return gender == null ? null : Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.warn("Invalid gender: {}", gender);
            return null;
        }
    }
}