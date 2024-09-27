package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDao extends JpaRepository<SensorEntity, Long> {
}
