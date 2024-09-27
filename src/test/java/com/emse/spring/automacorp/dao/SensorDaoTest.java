package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.SensorEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest // (1)
class SensorDaoTest {
    @Autowired // (2)
    private SensorDao sensorDao;

    @Test
    public void shouldFindAWindowById() {
        SensorEntity sensor = sensorDao.getReferenceById(-10L); // (3)
        Assertions.assertThat(sensor.getName()).isEqualTo("Temperature room 2");
        Assertions.assertThat(sensor.getValue()).isEqualTo(21.3);
    }
}
