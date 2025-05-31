package iti.jets.model.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Category {
    SNEAKERS,
    CLASSIC,
    CASUAL;


    public static Category parseCategory(String category) {
        try {
            return category == null ? null : Category.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.warn("Invalid category: {}", category);
            return null;
        }
    }
}