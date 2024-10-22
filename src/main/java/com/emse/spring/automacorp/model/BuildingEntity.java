package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "SP_BUILDING")
public class BuildingEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "building")
    private Set<RoomEntity> rooms = Set.of();
    
    
    public BuildingEntity(){
        this.name = "Building";
    }
    public BuildingEntity(long l, String building) {
        this.id = l;
        this.name = building;
    }

    public Set<RoomEntity> getRooms() {
        return this.rooms;
    }

    public void addRoom(RoomEntity room) {
        this.rooms.add(room);
    }

}
