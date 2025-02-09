package com.melita.ots.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    PENDING(1),
    ACCEPTED(2),
    REJECTED(3);

    private final int id;
}
