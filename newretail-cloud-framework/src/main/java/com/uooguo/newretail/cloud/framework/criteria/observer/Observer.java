package com.uooguo.newretail.cloud.framework.criteria.observer;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.uooguo.newretail.cloud.framework.criteria.CriteriaContext;

/**
 * @author Tiangel
 * @date 2018-4-18
 **/
public interface Observer<T> {

    /**
     * 解析之前
     * @param wrapper
     */
    public void beforeParse(EntityWrapper<T> wrapper);

    /**
     * 解析之后
     * @param wrapper
     */
    public void afterParse(EntityWrapper<T> wrapper);

    /**
     * 正在解析
     * @param criteriaContext
     * @param wrapper
     * @return
     */
    public boolean onParse(CriteriaContext<?> criteriaContext, EntityWrapper<T> wrapper);
}
