package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "SP_ROOM")
public class RoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private SensorEntity currentTemperature;

    @Column
    private Double targetTemperature;

    @OneToMany(mappedBy = "room")
    private Set<WindowEntity> windows = Set.of();

    public RoomEntity() {
    }

    public RoomEntity(Integer floor, String name) {
        this.floor = floor;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public Set<WindowEntity> getWindows() {
        return windows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWindows(Set<WindowEntity> windows) {
        this.windows = windows;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }
}
