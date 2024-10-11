package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static com.emse.spring.automacorp.model.SensorType.TEMPERATURE;


public class SensorMapperTest {

    @Test
    void ShouldMapSensors(){
        RoomEntity roomEntity = FakeEntityBuilder.createBuildingEntity(1L, "Building")
                .getRooms()
                .stream()
                .min(Comparator.comparing(RoomEntity::getName))
                .orElseThrow(IllegalArgumentException::new);
        SensorEntity sensorEntity = roomEntity.getCurrentTemperature();


        Sensor sensor = SensorMapper.of(sensorEntity);

        Sensor expectedSensor = new Sensor(
                1L,
                "Temp",
                23.2,
                TEMPERATURE
        );

        Assertions.assertThat(sensor).usingRecursiveAssertion().isEqualTo(expectedSensor);
    }
}
