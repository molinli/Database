package com.system.service;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/7
 */
public interface ScoreService {

    //根据学号找成绩
    Integer findScoreById(Integer studentId) throws Exception;
}
