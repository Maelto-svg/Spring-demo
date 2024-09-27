package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WindowEntity> findRoomsWithOpenWindows(Long id) {
        String jpql = "select w from WindowEntity w inner join w.windowStatus s " +
                "where w.room.id = :id and s.value > 0.0 order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<WindowEntity> findWindowByRoomName(String name) {
        String jpql = "select w from WindowEntity w where w.room.name = :name order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void deleteByRoom(Long id) {
        String jpql = "delete from WindowEntity w where w.room.id = :id";

        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void setAllWindow(Boolean open, Long id) {
        double val = open ? 1.0 : 0.0;

        String jpql = "UPDATE SensorEntity s " +
                "SET s.value = :val " +
                "WHERE s IN (SELECT w.windowStatus FROM WindowEntity w WHERE w.room.id = :id)";

        em.createQuery(jpql)
                .setParameter("val", val)
                .setParameter("id", id)
                .executeUpdate();
    }


}