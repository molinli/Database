package com.system.mapper;

import com.system.po.Status;

/**
 * 系统状态量
 *
 * @Author: 莫林立
 * @Date: Created in 2018/1/9
 */
public interface StatusMapper {

    int insert(Status status);

    Status select();

    int update(Status status);

}
