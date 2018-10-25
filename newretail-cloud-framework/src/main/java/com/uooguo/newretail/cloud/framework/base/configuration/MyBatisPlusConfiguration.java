package com.uooguo.newretail.cloud.framework.base.configuration;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.uooguo.newretail.cloud.framework.base.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author Tiangel
 * @Date 2017/5/20 21:58
 */
@Slf4j
@Configuration
@MapperScan(basePackages = { "com.uooguo.newretail.**.mapper" })
public class MyBatisPlusConfiguration {
    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean
    public CommonMapper commonMapper(SqlSessionFactory sqlSessionFactory){
        log.info("Init CommonMapper");
        return new CommonMapper(sqlSessionFactory);
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }
}
