package com.ll.auth2024.standard.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KwTypeV1 {
    ALL("all"),
    TITLE("title"),
    NAME("name");

    private final String value;
}