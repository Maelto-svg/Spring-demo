package com.emse.spring.automacorp.model;

import jakarta.annotation.Nullable;
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

    @ManyToOne
    private BuildingEntity building;

    @OneToMany(mappedBy = "room")
    private Set<HeaterEntity> heaters = Set.of();

    public RoomEntity() {
    }

    public RoomEntity(Integer floor, String name, BuildingEntity b) {
        this.floor = floor;
        this.name = name;
        this.building =b;
    }

    public RoomEntity(String name, SensorEntity sensor, Integer floor) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = sensor;
    }

    public RoomEntity(Long id) {
        this.id = id;
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

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setHeaters(Set<HeaterEntity> heaterEntity) {
        this.heaters = heaterEntity;
    }

    public Set<HeaterEntity> getHeaters() {
        return heaters;
    }
}
