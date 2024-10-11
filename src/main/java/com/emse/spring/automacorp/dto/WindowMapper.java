package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;

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
