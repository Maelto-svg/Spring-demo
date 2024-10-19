package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dto.FakeEntityBuilder;
import com.emse.spring.automacorp.model.BuildingEntity;
import com.emse.spring.automacorp.model.RoomEntity;
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

@WebMvcTest(RoomControler.class)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomDao roomDao;

    @Test
    void shouldFindAll() throws Exception {
        Mockito.when(roomDao.findAll()).thenReturn(List.of(
                FakeEntityBuilder.createRoomEntity(-3L, "Living Room", new BuildingEntity(1, "Building")),
                FakeEntityBuilder.createRoomEntity(2L, "Bedroom", new BuildingEntity(2, "Building"))
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("[*].name")
                                .value(Matchers.containsInAnyOrder("Living Room", "Bedroom"))
                );
    }

    @Test
    void shouldReturnNullWhenFindByUnknownId() throws Exception {
        Mockito.when(roomDao.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/999").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void shouldFindById() throws Exception {
        RoomEntity roomEntity = FakeEntityBuilder.createRoomEntity(1L, "Living Room", new BuildingEntity(1, "Building"));
        Mockito.when(roomDao.findById(999L)).thenReturn(Optional.of(roomEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/999").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Living Room"));
    }

//    @Test
//    void shouldNotUpdateUnknownEntity() throws Exception {
//        RoomEntity roomEntity = FakeEntityBuilder.createRoomEntity(1L, "Living Room", new BuildingEntity(1, "Building"));
//        RoomCommand expectedRoom = new RoomCommand(roomEntity.getName(), roomEntity.getFloor());
//        String json = objectMapper.writeValueAsString(expectedRoom);
//
//        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.empty());
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .put("/api/rooms/1")
//                                .content(json)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                )
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//
//    @Test
//    void shouldUpdate() throws Exception {
//        RoomEntity roomEntity = FakeEntityBuilder.createRoomEntity(1L, "Living Room", 1);
//        RoomCommand expectedRoom = new RoomCommand(roomEntity.getName(), roomEntity.getFloor());
//        String json = objectMapper.writeValueAsString(expectedRoom);
//
//        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(roomEntity));
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .put("/api/rooms/1")
//                                .content(json)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Living Room"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
//    }
//
//    @Test
//    void shouldCreate() throws Exception {
//        RoomEntity roomEntity = FakeEntityBuilder.createRoomEntity(1L, "Living Room", 1);
//        RoomCommand expectedRoom = new RoomCommand(roomEntity.getName(), roomEntity.getFloor());
//        String json = objectMapper.writeValueAsString(expectedRoom);
//
//        Mockito.when(roomDao.existsById(1L)).thenReturn(false);
//        Mockito.when(roomDao.save(Mockito.any(RoomEntity.class))).thenReturn(roomEntity);
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/api/rooms")
//                                .content(json)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Living Room"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
//    }
//
//    @Test
//    void shouldDelete() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/999"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

}
