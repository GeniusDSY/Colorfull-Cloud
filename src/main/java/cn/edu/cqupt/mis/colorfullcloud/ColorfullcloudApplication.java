package cn.edu.cqupt.mis.colorfullcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.edu.cqupt.mis.colorfullcloud.dao")
@SpringBootApplication
public class ColorfullcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColorfullcloudApplication.class, args);
    }

}
