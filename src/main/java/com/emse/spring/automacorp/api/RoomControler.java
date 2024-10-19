package com.emse.spring.automacorp.api;


import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.RoomMapper;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional
public class RoomControler {

    private final RoomDao roomDao;
    private final SensorDao sensorDao;

    public RoomControler(RoomDao roomDao, SensorDao sensorDao){
        this.roomDao = roomDao;
        this.sensorDao = sensorDao;
    }

    @GetMapping // (5)
    public List<Room> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .sorted(Comparator.comparing(Room::name))
                .collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public Room findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null);
    }
}
