package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class RoomMapper {
    public static Room of(RoomEntity room) {
        List<Window> wind = new ArrayList<Window>().stream().toList();
        List<Heater> heat = new ArrayList<Heater>().stream().toList();
        try {
            wind = room.getWindows().stream()
                    .map(WindowMapper::of)
                    .sorted(Comparator.comparing(Window::id))
                    .toList();
        }
        catch (Exception ignored){

        }
        try {
            heat = room.getHeaters().stream()
                    .map(HeaterMapper::of)
                    .sorted(Comparator.comparing(Heater::id))
                    .toList();
        }
        catch (Exception ignored){

        }
        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperature().getValue(),
                room.getTargetTemperature(),
                wind,
                heat
        );
    }
}
