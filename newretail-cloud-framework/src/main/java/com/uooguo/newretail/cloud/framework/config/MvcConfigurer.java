package com.uooguo.newretail.cloud.framework.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.uooguo.newretail.cloud.framework.kit.BeanKit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * @author jiangskui
 * @date 2018/9/13  13:05
 * @description
 */
//@Component
public class MvcConfigurer implements WebMvcConfigurer {


    /**
     * 自动扫描相关的参数解析器并且注册
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.addAll(BeanKit.getListOfType(HandlerMethodArgumentResolver.class));
    }

    /**
     * 自动扫描相关组件并且注册
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        for (Converter<?, ?> converter : BeanKit.getListOfType(Converter.class)) {
            registry.addConverter(converter);
        }
        for (GenericConverter converter : BeanKit.getListOfType(GenericConverter.class)) {
            registry.addConverter(converter);
        }
        for (Formatter<?> formatter : BeanKit.getListOfType(Formatter.class)) {
            registry.addFormatter(formatter);
        }
    }

    /**
     * 消息类型转换
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }


}
