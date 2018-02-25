package com.system.service;

import com.system.po.Status;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/9
 */
public interface StatusService {

    void save(Status status)throws Exception;

    void update(Status status)throws Exception;

    //找到当前status值
    Status find() throws Exception;
}
