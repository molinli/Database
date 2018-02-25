package com.system.service.impl;

import com.system.mapper.StatusMapper;
import com.system.po.Status;
import com.system.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对系统状态量的修改
 *
 * @Author: 莫林立
 * @Date: Created in 2018/1/9
 */
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    public void save(Status status) throws Exception {
        statusMapper.insert(status);

    }

    public void update(Status status) throws Exception {
        statusMapper.update(status);

    }

    public Status find() throws Exception {

        return statusMapper.select();
    }
}
