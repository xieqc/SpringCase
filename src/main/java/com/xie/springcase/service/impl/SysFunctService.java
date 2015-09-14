package com.xie.springcase.service.impl;

import com.xie.springcase.hibernate.entity.SysFunct;
import com.xie.springcase.jpa.dao.SysFunctDAO;
import com.xie.springcase.service.ISysFunctService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
@Service
public class SysFunctService implements ISysFunctService {
    @Resource(name="JSysFunctDAO")
    private SysFunctDAO j_sysFunctDAO;

    @Transactional(readOnly = false)
    public SysFunct save(SysFunct funct) {
        return j_sysFunctDAO.save(funct);
    }

    @Transactional(readOnly = true)
    public List<String> getFunctNameListBySysUserId(Integer userId) {
        return j_sysFunctDAO.getFunctNameListBySysUserId(userId);
    }
}
