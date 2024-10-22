package com.emse.spring.automacorp.dao;


import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;

public interface WindowDaoCustom {
    List<WindowEntity> findRoomsWithOpenWindows(Long id);
    List<WindowEntity> findWindowByRoomName(String name);
    void deleteByRoom(Long Id);
    void setAllWindow(Boolean open, Long id);
}
