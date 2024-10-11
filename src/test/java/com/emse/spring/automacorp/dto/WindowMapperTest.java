package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class WindowMapperTest {

    @Test
    void ShouldMapWindows(){
        RoomEntity roomEntity = FakeEntityBuilder.createBuildingEntity(1L, "Building")
                .getRooms()
                .stream()
                .min(Comparator.comparing(RoomEntity::getName))
                .orElseThrow(IllegalArgumentException::new);
        WindowEntity windowEntity = roomEntity.getWindows()
                .stream()
                .min(Comparator.comparing((WindowEntity::getName)))
                .orElseThrow(IllegalArgumentException::new);

        Window window = WindowMapper.of(windowEntity);

        Window expectedWindow = new Window(
                111L,
                "Window1Room1Building",
                0.0,
                11L
        );

        Assertions.assertThat(window).usingRecursiveAssertion().isEqualTo(expectedWindow);
    }
}
