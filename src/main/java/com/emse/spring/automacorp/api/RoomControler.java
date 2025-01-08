package com.emse.spring.automacorp.api;


import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.RoomMapper;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional
public class RoomControler {

    private final RoomDao roomDao;

    public RoomControler(RoomDao roomDao){
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    @CrossOrigin(origins = { "http://localhost:8000" }, maxAge = 3600)
    public List<Room> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .sorted(Comparator.comparing(Room::name))
                .collect(Collectors.toList());  // (6)
    }

    @CrossOrigin(origins = { "http://localhost:8000" }, maxAge = 3600)
    @GetMapping(path = "/{id}")
    public Room findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null);
    }
}
