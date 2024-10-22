package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class HeaterMapperTest {

    @Test
    void ShouldMapHeater(){
        RoomEntity roomEntity = FakeEntityBuilder.createBuildingEntity(1L, "Building")
                .getRooms()
                .stream()
                .min(Comparator.comparing(RoomEntity::getName))
                .orElseThrow(IllegalArgumentException::new);
        HeaterEntity heaterEntity = roomEntity.getHeaters()
                .stream()
                .min(Comparator.comparing((HeaterEntity::getName)))
                .orElseThrow(IllegalArgumentException::new);

        Heater heater = HeaterMapper.of(heaterEntity);

        Heater expectedHeater = new Heater(
                111L,
                "Heater1Room1Building",
                0.0,
                11L
        );

        Assertions.assertThat(heater).usingRecursiveAssertion().isEqualTo(expectedHeater);
    }
}
