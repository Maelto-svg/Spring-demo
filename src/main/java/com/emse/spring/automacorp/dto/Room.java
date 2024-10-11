package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;

public record Room(Long id, String name, Integer floor, Double currentTemperature, Double targetTemperature, List<Window> window, List<Heater> heaters) {
}
