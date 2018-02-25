package com.system.service.impl;

import com.system.mapper.ScoreMapper;
import com.system.po.Score;
import com.system.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/7
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreMapper scoreMapper;

    public Integer findScoreById(Integer studentId) throws Exception {
        Score score=scoreMapper.findByStuId(studentId);
        if (score!=null){
            return score.getScore();
        }else {
            return null;
        }
    }
}
