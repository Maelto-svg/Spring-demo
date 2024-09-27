package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest // (1)
class RoomDaoTest {
    @Autowired // (2)
    private RoomDao roomDao;

    @Test
    public void shouldFindAWindowById() {
        RoomEntity room = roomDao.getReferenceById(-10L); // (3)
        Assertions.assertThat(room.getFloor()).isEqualTo(1);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
    }
}
