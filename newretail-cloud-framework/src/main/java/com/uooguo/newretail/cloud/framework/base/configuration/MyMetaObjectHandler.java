package com.uooguo.newretail.cloud.framework.base.configuration;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 注入公共字段自动填充,任选注入方式即可
 */
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object gmtCreate = getFieldValByName("gmtCreate", metaObject);
        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        if (StringUtils.isEmpty(gmtCreate)) {
            setFieldValByName("gmtCreate", new Date(), metaObject);
        }
        if (StringUtils.isEmpty(gmtModified)) {
            setFieldValByName("gmtModified", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        if (StringUtils.isEmpty(gmtModified)) {
            setFieldValByName("gmtModified", new Date(), metaObject);
        }
    }
}
