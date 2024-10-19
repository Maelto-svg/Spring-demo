package com.emse.spring.automacorp.dto;

public class WindowDto {
    private Long id;
    private String name;
    private Long room_id;
    private Double sensor_value;

    // Constructor
    public WindowDto(Long id, String name, Long room_id, Double sensor_value) {
        this.id = id;
        this.name = name;
        this.room_id = room_id;
        this.sensor_value = sensor_value;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Double getSensor_value() {
        return sensor_value;
    }

    public void setSensor_value(Double sensor_value) {
        this.sensor_value = sensor_value;
    }
}

