package com.uooguo.newretail.cloud.framework.sms;

import com.uooguo.newretail.cloud.framework.sms.config.AliyunSmsProperties;
import com.uooguo.newretail.cloud.framework.sms.sender.AliyunSmsSender;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@EnableConfigurationProperties({AliyunSmsProperties.class})
public class SmsAutoConfiguration {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AliyunSmsProperties aliyunSmsProperties;

	@Bean
	@ConditionalOnClass({ AliyunSmsSender.class })
	@ConditionalOnProperty(name = "newretail.sms.type", havingValue = "aliyun")
	public SmsSender smsSender() {
        AliyunSmsSender sender = new AliyunSmsSender();
		BeanUtils.copyProperties(aliyunSmsProperties, sender);
		return sender;
	}

}
