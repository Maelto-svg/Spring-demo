package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "SP_BUILDING")
public class BuildingEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "building")
    private ArrayList<RoomEntity> rooms = new ArrayList<>();
    
    
    
    public BuildingEntity(long l, String building) {
        this.id = l;
        this.name = building;
    }

    public ArrayList<RoomEntity> getRooms() {
        return this.rooms;
    }

    public void addRoom(RoomEntity room) {
        this.rooms.add(room);
    }

}
