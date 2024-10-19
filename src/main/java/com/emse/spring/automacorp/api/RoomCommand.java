package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.SensorEntity;

public record RoomCommand (String name, SensorEntity sensor, Integer floor){
}
