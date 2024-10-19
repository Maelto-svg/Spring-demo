package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.WindowEntity;

public class WindowMapper {
    public static Window of(WindowEntity window) {
        return new Window(
                window.getId(),
                window.getName(),
                window.getWindowStatus().getValue(),
                window.getRoom().getId()
        );
    }
}
