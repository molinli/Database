package com.system.service;

import com.system.po.College;

import java.util.List;

/**
 * 院系service
 */
public interface CollegeService {

    List<College> finAll() throws Exception;

    String findById(Integer id)throws Exception;
}
