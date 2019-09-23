package cn.edu.cqupt.mis.colorfullcloud;

import cn.edu.cqupt.mis.colorfullcloud.dao.AdminDao;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 22:40
 * @desc :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private AdminDao adminDao;

    @org.junit.Test
    public void test(){
        System.out.println(adminDao.selectUserByAdminId("admin"));
    }

}
