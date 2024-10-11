package com.emse.spring.automacorp.dto;


import com.emse.spring.automacorp.model.HeaterStatus;
import com.emse.spring.automacorp.model.RoomEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

class RoomMapperTest {

    @Test
    void shouldMapRoom() {
        // Arrange
        RoomEntity roomEntity = FakeEntityBuilder.createBuildingEntity(1L, "Building")
                .getRooms()
                .stream()
                .min(Comparator.comparing(RoomEntity::getName))
                .orElseThrow(IllegalArgumentException::new);

        // Act
        Room room = RoomMapper.of(roomEntity);

        // Assert
        Room expectedRoom = new Room(
                11L,
                "Room1Building",
                1,
                23.2,
                26.4,
                List.of(
                        new Window(
                                111L,
                                "Window1Room1Building",
                                0.0,
                                11L
                        ),
                        new Window(
                                112L,
                                "Window2Room1Building",
                                0.0,
                                11L
                        )
                ),
                List.of(
                        new Heater(
                                111L,
                                "Heater1Room1Building",
                                HeaterStatus.OFF,
                                11L
                        ),
                        new Heater(
                                112L,
                                "Heater2Room1Building",
                                HeaterStatus.OFF,
                                11L
                        )
                )
        );
        Assertions.assertThat(room).usingRecursiveAssertion().isEqualTo(expectedRoom);
    }
}
