package com.system.mapper;

import com.system.po.Score;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/5
 */
public interface ScoreMapper {

    Score findByStuId(Integer stuid );

    int insert(Score score);
}
