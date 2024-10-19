package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.dto.WindowMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/windows") // (2)
@Transactional // (3)
public class WindowController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final SensorDao sensorDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao, SensorDao sensorDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.sensorDao =sensorDao;
    }

    @GetMapping // (5)
    public List<Window> findAll() {
        return windowDao.findAll()
                .stream()
                .map(WindowMapper::of)
                .sorted(Comparator.comparing(Window::name))
                .collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public Window findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowMapper::of).orElse(null); // (7)
    }

    @PostMapping // (8)
    public ResponseEntity<Window> create(@RequestBody WindowCommand window) { // (9)
        RoomEntity room = roomDao.findById(window.room_id()).orElse(null);
        if (room == null){
            return ResponseEntity.badRequest().build();
        }
        SensorEntity sensor = new SensorEntity(SensorType.STATUS, "Sensor: "+window.name());
        sensor.setValue(window.windowStatus());
        sensorDao.save(sensor);
        WindowEntity entity = new WindowEntity(window.name(), sensor, room);
        WindowEntity saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowMapper.of(saved));
    }

    @PutMapping(path = "/{id}") // (10)
    public ResponseEntity<Window> update(@PathVariable Long id, @RequestBody WindowCommand window) {
        WindowEntity entity = windowDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        RoomEntity room = roomDao.findById(window.room_id()).orElse(null);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setRoom(room);
        entity.setName(window.name());
        entity.setWindowStatus(window.windowStatus());
        WindowEntity saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowMapper.of(saved));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}