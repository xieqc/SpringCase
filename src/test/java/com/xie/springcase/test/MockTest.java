package com.xie.springcase.test;

import com.xie.springcase.hibernate.entity.SysUser;
import com.xie.springcase.jpa.dao.SysRoleDAO;
import com.xie.springcase.jpa.dao.SysUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by xieqinchao on 16-1-21.
 */
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MockTest extends AbstractJUnit4SpringContextTests {
    @Mock
    public SysUserDAO j_sysUserDao;
    @Resource(name="JSysRoleDAO")
    public SysRoleDAO j_sysRoleDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(j_sysUserDao.findByName("zzz")).thenReturn(new SysUser() {
            public String getName() {
                return "456";
            }
        });
    }

    @Test
    public void testCase() {
        SysUser sysUser = j_sysUserDao.findByName("zzz");
        System.out.println(sysUser.getName());
        System.out.println("123");
        List<String> roleList = j_sysRoleDao.getRoleNameListBySysUserId(1);
        System.out.println(roleList.size());
    }
}