package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoomMapper {
    public static Room of(RoomEntity room) {
        List<WindowEntity> windows = room.getWindows().stream().toList();
        ArrayList<Window> wind = new ArrayList<Window>();
        for(WindowEntity w : windows){
            wind.add(WindowMapper.of(w));
        }
        List<HeaterEntity> heaters = room.getHeaters().stream().toList();
        ArrayList<Heater> heats = new ArrayList<Heater>();
        for(HeaterEntity h : heaters){
            heats.add(HeaterMapper.of(h));
        }
        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperature().getValue(),
                room.getTargetTemperature(),
                wind.stream().toList(),
                heats.stream().toList()
        );
    }
}
