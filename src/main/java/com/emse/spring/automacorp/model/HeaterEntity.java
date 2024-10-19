package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_HEATER")
public class HeaterEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne
    private SensorEntity sensor;

    @ManyToOne
    private RoomEntity room;

    public HeaterEntity(){
    }

    public HeaterEntity(String name, SensorEntity sensorEntity, RoomEntity roomEntity) {
        this.name = name;
        this.sensor = sensorEntity;
        this.room =roomEntity;
    }

    public SensorEntity getSensor() {
        return sensor;
    }

    public String getName() {
        return name;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSensor(SensorEntity sensor) {
        this.sensor = sensor;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
