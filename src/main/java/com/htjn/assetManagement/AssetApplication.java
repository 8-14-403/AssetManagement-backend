package com.htjn.assetManagement;

import com.htjn.assetManagement.entity.Users;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 系统入口类
 *
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan(basePackageClasses = Users.class)
public class AssetApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AssetApplication.class).run(args);
    }
}