package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.FakeEntityBuilder;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static com.emse.spring.automacorp.model.SensorType.TEMPERATURE;


@WebMvcTest(WindowController.class)
public class WindowControllerTest {
    // Spring object to mock call to our app
    @Autowired
    private MockMvc mockMvc;

    // The serializer used by Spring to send and receive data to/from the REST controller
    @Autowired
    private ObjectMapper objectMapper;

    // We choose to mock the DAO used in the REST controller to limit the scope of our test
    @MockBean
    private WindowDao windowDao;

    @Test
    void shouldFindAll() throws Exception {
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "room", FakeEntityBuilder.createBuildingEntity(1, "name"));
        Mockito.when(windowDao.findAll()).thenReturn(List.of(
                FakeEntityBuilder.createWindowEntity(1L, "Window 1", room ),
                FakeEntityBuilder.createWindowEntity(2L, "Window 2", room)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows").accept(MediaType.APPLICATION_JSON))
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                // the content can be tested with Json path
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("[*].name")
                                .value(Matchers.containsInAnyOrder("Window 1", "Window 2"))
                );
    }

    @Test
    void shouldReturnNullWhenFindByUnknownId() throws Exception {
        Mockito.when(windowDao.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows/999").accept(MediaType.APPLICATION_JSON))
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                // the content can be tested with Json path
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void shouldFindById() throws Exception {
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "room", FakeEntityBuilder.createBuildingEntity(1, "name"));
        WindowEntity windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1",room);
        Mockito.when(windowDao.findById(999L)).thenReturn(Optional.of(windowEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows/999").accept(MediaType.APPLICATION_JSON))
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                // the content can be tested with Json path
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Window 1"));
    }

    @Test
    void shouldNotUpdateUnknownEntity() throws Exception {
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "room", FakeEntityBuilder.createBuildingEntity(1, "name"));
        WindowEntity windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1",room);
        WindowCommand expectedWindow = new WindowCommand(windowEntity.getName(), windowEntity.getWindowStatus().getValue(), windowEntity.getRoom().getId());
        String json = objectMapper.writeValueAsString(expectedWindow);

        Mockito.when(windowDao.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/windows/1")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldUpdate() throws Exception {
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "room", FakeEntityBuilder.createBuildingEntity(1, "name"));
        WindowEntity windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1",room);
        WindowCommand expectedWindow = new WindowCommand(windowEntity.getName(), windowEntity.getWindowStatus().getValue(), windowEntity.getRoom().getId());
        String json = objectMapper.writeValueAsString(expectedWindow);

        Mockito.when(windowDao.findById(1L)).thenReturn(Optional.of(windowEntity));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/windows/1")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Window 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    void shouldCreate() throws Exception {
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "room", FakeEntityBuilder.createBuildingEntity(1, "name"));
        WindowEntity windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1",room);
        WindowCommand expectedWindow = new WindowCommand(windowEntity.getName(), windowEntity.getWindowStatus().getValue(), windowEntity.getRoom().getId());
        String json = objectMapper.writeValueAsString(expectedWindow);

        Mockito.when(windowDao.existsById(1L)).thenReturn(false);
        Mockito.when(windowDao.save(Mockito.any(WindowEntity.class))).thenReturn(windowEntity);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/windows")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Window 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/windows/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}