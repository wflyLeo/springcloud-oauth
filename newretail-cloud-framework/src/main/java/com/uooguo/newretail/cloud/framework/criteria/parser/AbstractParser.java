package com.uooguo.newretail.cloud.framework.criteria.parser;

import com.uooguo.newretail.cloud.framework.criteria.CriteriaContext;
import com.uooguo.newretail.cloud.framework.criteria.annotation.Parser;
import com.uooguo.newretail.cloud.framework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @author Tiangel
 * @date 2018-4-18
 **/
@Parser
public abstract class AbstractParser<S extends Annotation> implements ConditionParser<S> {

    /**
     * 获取默认的列,自动将驼峰表示转换成下划线格式
     *
     * @param criteriaContext
     * @return
     */
    protected String getDefaultColumn(CriteriaContext criteriaContext) {
        return StringUtils.camelCaseToUnderline(criteriaContext.getProperty());
    }
}
