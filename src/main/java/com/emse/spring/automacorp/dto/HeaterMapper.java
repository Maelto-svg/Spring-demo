package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;

public class HeaterMapper {
    public static Heater of(HeaterEntity heater){
        return new Heater(
                heater.getId(),
                heater.getName(),
                heater.getSensor().getValue(),
                heater.getRoom().getId()
        );
    }
}
